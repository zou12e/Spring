package com.sc.filter;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.extern.slf4j.Slf4j;
import org.reactivestreams.Publisher;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.util.Map;
import java.util.function.Consumer;

/**
 * 请求url权限校验
 */
@Configuration
@Slf4j
public class AccessGatewayFilter implements GlobalFilter {

    private static final String X_CLIENT_TOKEN_USER = "x-client-token-user";
    private static final String X_CLIENT_TOKEN = "x-client-token";



    /**
     * 1.首先网关检查token是否有效，无效直接返回401，不调用签权服务
     * 2.调用签权服务器看是否对该请求有权限，有权限进入下一个filter，没有权限返回401
     *
     * @param exchange
     * @param chain
     * @return
     */
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        ServerHttpResponse originalResponse = exchange.getResponse();
        DataBufferFactory bufferFactory = originalResponse.bufferFactory();

        ServerHttpRequest request = exchange.getRequest();
        String authentication = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String method = request.getMethodValue();
        String url = request.getPath().value();
        log.debug("url:{},method:{},headers:{}", url, method, request.getHeaders());
        //不需要网关签权的url
//        if (authService.ignoreAuthentication(url)) {
//            return chain.filter(exchange);
//        }

        //调用签权服务看用户是否有权限，若有权限进入下一个filter
//        if (permissionService.permission(authentication, url, method)) {
            ServerHttpRequest.Builder builder = request.mutate();
//            //TODO 转发的请求都加上服务间认证token
//            builder.header(X_CLIENT_TOKEN, "TODO zhoutaoo添加服务间简单认证");
//            //将jwt token中的用户信息传给服务
//            builder.header(X_CLIENT_TOKEN_USER, getUserToken(authentication));

//            modifyResponseFilter(exchange, chain);
            // return chain.filter(exchange.mutate().request(builder.build()).build());
//            return chain.filter(exchange.mutate().request(builder.build()).build()).then(Mono.fromRunnable(() -> {

//                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                exchange.getAttributes().put("ResponseData", "originalBody");
//                DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
//                byte[] uppedContent = new String("POST DATA".getBytes(), Charset.forName("UTF-8")).getBytes();
//                bufferFactory.wrap(uppedContent);
//                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
//                DataBuffer buffer = exchange.getResponse()
//                        .bufferFactory().wrap(HttpStatus.UNAUTHORIZED.getReasonPhrase().getBytes());
//                String rbody = "err";
//                exchange.getResponse().writeWith(bufferFactory.wrap(rbody));
//                    return;
//            }));
//        }
//        return unauthorized(exchange);

        return chain.filter(exchange).then(Mono.fromRunnable(() -> {
            log.info("second post filter");
        }));
    }

    /**
     * 提取jwt token中的数据，转为json
     *
     * @param authentication
     * @return
     */
    private String getUserToken(String authentication) {
        String token = "{}";
//        try {
//            token = new ObjectMapper().writeValueAsString(authService.getJwt(authentication).getBody());
//            return token;
//        } catch (JsonProcessingException e) {
//            log.error("token json error:{}", e.getMessage());
//        }
        return token;
    }

    /**
     * 网关拒绝，返回401
     *
     * @param
     */
    private Mono<Void> unauthorized(ServerWebExchange serverWebExchange) {
        serverWebExchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
        DataBuffer buffer = serverWebExchange.getResponse()
                .bufferFactory().wrap(HttpStatus.UNAUTHORIZED.getReasonPhrase().getBytes());
        return serverWebExchange.getResponse().writeWith(Flux.just(buffer));
    }

    /**
     * 重写http请求的应答数据
     */
    private Mono<Void> modifyResponseFilter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String path = exchange.getRequest().getURI().getRawPath();
        ServerHttpRequest request = setRequestHeader(exchange);
        if ("/login".equalsIgnoreCase(path)) {
            ServerHttpResponseDecorator responseDecorator = new ServerHttpResponseDecorator(exchange.getResponse()) {
                @Override
                public Mono<Void> writeWith(Publisher<? extends DataBuffer> body) {
                    DataBufferFactory bufferFactory = exchange.getResponse().bufferFactory();
                    if (getStatusCode().equals(HttpStatus.OK) && body instanceof Flux) {
                        Flux<? extends DataBuffer> fluxBody = Flux.from(body);
                        return super.writeWith(fluxBody.map(dataBuffer -> {
                            byte[] contentByts = new byte[dataBuffer.readableByteCount()];
                            dataBuffer.read(contentByts);
                            //释放掉内存
                            DataBufferUtils.release(dataBuffer);
                            // originalBody就是下游系统返回的内容,可以进行修改
                            String strContent = new String(contentByts, Charset.forName("UTF-8"));
                            String originalBody = exchange.getAttribute("ResponseData");
                            if (originalBody == null) {
                                originalBody = strContent;
                            } else {
                                originalBody += strContent;
                            }
                            exchange.getAttributes().put("ResponseData", originalBody);



                            byte[] uppedContent = new String(originalBody.getBytes(),
                                    Charset.forName("UTF-8")).getBytes();
                            return bufferFactory.wrap(uppedContent);

//                                byte[] uppedContent = new String(" ".getBytes(),
//                                        Charset.forName("UTF-8")).getBytes();
//                                return bufferFactory.wrap(uppedContent);
                        }));
                    }
                    return super.writeWith(body);
                }

                public Mono<Void> writeAndFlushWith(Publisher<? extends Publisher<? extends DataBuffer>> body) {
                    return this.writeWith(Flux.from(body).flatMapSequential((p) -> {
                        return p;
                    }));
                }
            };
            return chain.filter(exchange.mutate().request(request).response(responseDecorator).build());
        } else {
            return chain.filter(exchange.mutate().request(request).build());
        }
    }

    private ServerHttpRequest setRequestHeader(ServerWebExchange exchange) {
        Long userid = 0L;
        ServerHttpRequest.Builder builder = exchange.getRequest().mutate().headers(new Consumer<HttpHeaders>() {
            @Override
            public void accept(HttpHeaders httpHeaders) { }
        });
        builder.header("userid", userid.toString());
        return builder.build();
    }
}
