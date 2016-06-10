package com.test.common;


import java.lang.reflect.Method;

import org.springframework.cglib.proxy.Callback;
import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;


public class CGLibProxyFactory {
 
    private Object target;
 
    public CGLibProxyFactory(Object target) {
        this.target = target;
    }
 
    private Callback callback = new MethodInterceptor() {
 
        /**
         *
         * @param obj   代理对象
         * @param method    当期调用方法
         * @param args  方法参数
         * @param proxy 被调用方法的代理对象(用于执行父类的方法)
         * @return
         * @throws Throwable
         */
        @Override
        public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
 
            // 前置增强
            System.out.println("+ Before Advice ...");
 
            // 执行目标方法
            //Object result = method.invoke(target, args);
            Object result = proxy.invoke(target, args);
 
            // 后置增强
            System.out.println("+ After Advice ...");
 
            return result;
        }
    };
 
    public Object createProxy() {
 
        // 1. 创建Enhancer对象
        Enhancer enhancer = new Enhancer();
 
        // 2. cglib创建代理, 对目标对象创建子对象
        enhancer.setSuperclass(target.getClass());
 
        // 3. 传入回调接口, 对目标增强
        enhancer.setCallback(callback);
 
        return enhancer.create();
    }
 
    public static void main(String[] args) {
        UserDAO proxy = (UserDAO) new CGLibProxyFactory(new UserDAO()).createProxy();
        proxy.get("hello");
        proxy.add("world");
    }
}