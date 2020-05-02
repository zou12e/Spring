package com.sc.proxy;


import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Client {

    public static void main(String[] args) {


//        producer.sale(100);
//        producer.sale(200);

        /**
         动态代理
         特点：字节码随用随创建，随用随加载
         作用：不修改源码的基础上对方法增强
         分类：
         基于接口的动态代理
         涉及的类：  Proxy
         提供者：   JDK官方
         要求：     被代理的类最少实现一个接口
         newProxyInstance方法参数：
         ClassLoader： 类加载器
         它是用于加载代理对象字节码，和被代理对象使用相同的类加载器，固定写法
         Class[]： 字节码数组
         它是用于让代理对象和被代理对象有相同方法，固定写法
         InvocationHandler： 增强的代码
         它是让我们写如何代理，通常都是匿名内部类


         基于子类的动态代理
         涉及的类：  Enhancer
         提供者：   cglib库
         要求：     被代理的类不能是最终类
         create方法参数：
         Class：字节码
         它是用于指定代理对象的字节码
         Callback： 增强的代码
         它是让我们写如何代理，我们一般都是该接口的子接口实现类 MethodInterceptor
         */

        final Producer producer = new Producer();
        IProducer iProducer = (IProducer) Proxy.newProxyInstance(Producer.class.getClassLoader(), Producer.class.getInterfaces(), new InvocationHandler() {
            /**
             * 执行被代理对象的任何接口方法都经过该方法
             * @param proxy     代理对象的引用
             * @param method    当前执行的方法
             * @param args      当前执行方法的参数
             * @return 被代理对象有相同的返回值
             * @throws Throwable
             */
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                int money = Integer.valueOf(args[0].toString());
                System.out.println("before:" + money);
                Object object = method.invoke(producer, args);
                return object;
            }
        });
        iProducer.sale(9999);


        Producer cglibProducer = (Producer) Enhancer.create(producer.getClass(), new MethodInterceptor() {
            /**
             * 执行被代理对象的任何方法都会经过该方法
             * @param proxy     代理对象的引用
             * @param method    当前执行的方法
             * @param args      当前执行方法的参数
             * @param methodProxy 当前执行方法的代理对象
             * @return 被代理对象有相同的返回值
             * @throws Throwable
             */
            public Object intercept(Object proxy, Method method, Object[] args, MethodProxy methodProxy) throws Throwable {
                int money = Integer.valueOf(args[0].toString());
                Object object = method.invoke(producer, args);
                System.out.println("after: " + money);
                return object;
            }
        });
        cglibProducer.sale(200);

    }
}
