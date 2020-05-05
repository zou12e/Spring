package com.sc.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

/**
 * 切入点表达式
 * 关键字：execution(表达式)
 * 表达式：
 * 访问修饰符 返回值 包名.类名.方法名(参数列表)
 * public void com.sc.service.impl.MyServiceImpl.save()
 * <p>
 * 访问修饰符可以省约
 * void com.sc.service.impl.MyServiceImpl.save()
 * <p>
 * 返回值可以使用统配符，表示任意返回值
 * com.sc.service.impl.MyServiceImpl.save()
 * <p>
 * 包名可以使用统配符，表示任意包名，但是有几级包，就需要几个*
 * *.*.*.*.MyServiceImpl.save()
 * 包名可以使用*.. 表示当前包及其子包
 * *..MyServiceImpl.save()
 * <p>
 * 类名和方法都可以使用统配符，表示任意类名和方法名称
 * *..*.*()
 * <p>
 * 参数列表：
 * 可以直接写数据类型
 * 基本类型直接写名称   int
 * 引用类型写包名加类名 java.lang.String
 * 可以使用统配符，但是必须有参数
 * 可以使用.. 表示有无参数均可
 * <p>
 * 实际开发切入点通用写法：
 * com.sc.service.impl.*.*(..)
 * <p>
 * 全通配写法：
 * *..*.*(..)
 */

@Component
/**
 * 表示切面类
 */
@Aspect
public class Logger {

    @Pointcut("execution(* com.sc.service.impl.MyServiceImpl.*(..))")
    private void pt(){}

    /**
     * 前置通知
     */
    @Before("pt()")
    public void beforePrintLog() {
        System.out.println("beforePrintLog");
    }

    /**
     * 后置通知
     */
    @AfterReturning("pt()")
    public void afterReturnPrintLog() {
        System.out.println("afterReturnPrintLog");
    }

    /**
     * 错误通知
     */
    @AfterThrowing("pt()")
    public void afterThrowingPrintLog() {
        System.out.println("afterThrowingPrintLog");
    }

    /**
     * 最终通知
     */
    @After("pt()")
    public void afterPrintLog() {
        System.out.println("afterPrintLog");
    }

    /**
     * 环绕通知
        问题： 配置环绕通知之后，切入点方法没有执行，而通知方法执行了
        分析： 通过对比动态代理的环绕代码，发现动态代理的环绕通知有明确的切入点方法调用。
        解决： Spring框架为我们提供了一个接口，ProceedingJoinPoint, 该接口proceed()，此方法明确调用切入方法。
     */
    @Around("pt()")
    public Object aroundPrintLog(ProceedingJoinPoint pjp) {
        Object rtValue = null;
        try {

            System.out.println("before");
            rtValue = pjp.proceed(pjp.getArgs());
            System.out.println("return");
            return rtValue;
        } catch (Throwable t) {
            System.out.println("throwing");
            throw new RuntimeException();
        } finally {
            System.out.println("after");
        }
    }
}
