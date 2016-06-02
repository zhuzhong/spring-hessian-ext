/**
 * 
 */
package com.zz.hessian.ext.web;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import com.zz.hessian.ext.Tracer;

/**
 * 业务环绕通知
 * 
 * @author Administrator
 *
 */
public class BusinessMethodTracer implements MethodInterceptor {

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        Tracer.trace(invocation.getClass().getName(), invocation.getMethod().getName(), invocation.getArguments());
        Object ret = invocation.proceed();
        Tracer.trace(invocation.getClass().getName(), invocation.getMethod().getName(), invocation.getArguments());
        return ret;
    }

}
