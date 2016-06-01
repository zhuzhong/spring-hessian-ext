/**
 * 
 */
package org.springframework.remoting.hessian.ext;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 
 client 端<br>
 * <bean id="helloService"
 * class="org.springframework.remoting.caucho.HessianProxyFactoryBean">
 * <property name="serviceUrl"
 * value="http://localhost:8000/exam_3/hessian/helloService" /> <br>
 * <property name="serviceInterface" value="com.test.hello.service.HelloService"
 * /> <br>
 * </bean>
 * 
 * @author sunff
 *
 */
public class HessianClientFactoryBean implements MethodInterceptor, FactoryBean<Object>, InitializingBean {

    private static Log log = LogFactory.getLog(HessianClientFactoryBean.class);
    private Class serviceInterface;
    private String serviceUrl;
    private Object serviceProxy;

    private ServiceProxy serviceProxyProxy;

    /**
     * @param serviceInterface
     *            the serviceInterface to set
     */
    public void setServiceInterface(Class serviceInterface) {
        this.serviceInterface = serviceInterface;
    }

    /**
     * @param serviceUrl
     *            the serviceUrl to set
     */
    public void setServiceUrl(String serviceUrl) {
        this.serviceUrl = serviceUrl;
    }

    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        TraceContext traceContext = null;
        if (ThreadLocalHolder.instance().getTraceContext() != null) {
            traceContext = ThreadLocalHolder.instance().getTraceContext();

        }
        Long traceId = traceContext == null ? null : traceContext.getTraceId();
        if (traceId != null) {
            log.info(new LogBean(traceId, invocation.getClass().getName(), invocation.getMethod().getName(), invocation
                    .getArguments()));
        }
        Object ret = serviceProxyProxy.call(invocation.getMethod().getName(), invocation.getArguments(), traceContext);
        if (traceId != null) {
            log.info(new LogBean(traceId, invocation.getClass().getName(), invocation.getMethod().getName(), invocation
                    .getArguments()));
        }
        return ret;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        org.springframework.remoting.caucho.HessianProxyFactoryBean h = new org.springframework.remoting.caucho.HessianProxyFactoryBean();
        h.setServiceUrl(serviceUrl);
        h.setServiceInterface(ServiceProxy.class);
        h.afterPropertiesSet();
        this.serviceProxyProxy = (ServiceProxy) h.getObject();
        this.serviceProxy = ProxyFactory.getProxy(serviceInterface, this);
        log.info("hessian 客户端启动...");
    }

    @Override
    public Object getObject() throws Exception {
        return serviceProxy;
    }

    @Override
    public Class<?> getObjectType() {
        return serviceInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

}
