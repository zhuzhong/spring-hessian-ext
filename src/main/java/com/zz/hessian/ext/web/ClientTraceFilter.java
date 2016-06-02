/**
 * 
 */
package com.zz.hessian.ext.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import com.zz.hessian.ext.Tracer;

/**
 * filter 作为tracer的入口，开启tracer
 * 
 * @author Administrator
 *
 */
public class ClientTraceFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // TODO Auto-generated method stub

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        Tracer.beginTracer();
        chain.doFilter(request, response);
        Tracer.endTracer();

    }

    @Override
    public void destroy() {
        // TODO Auto-generated method stub

    }

}
