/**
 * 
 */
package org.springframework.remoting.hessian.ext;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;

/**
 * 
 * <!-- client 端 --> <bean id="helloService"
 * class="org.springframework.remoting.caucho.HessianProxyFactoryBean">
 * <property name="serviceUrl"
 * value="http://localhost:8000/exam_3/hessian/helloService" /> <property
 * name="serviceInterface" value="com.j1.crcare.hello.service.HelloService" />
 * <!-- By default the value is false. As a matter of fact, it doesn't influence
 * the test result, the overloaded method still be supported --> <property
 * name="overloadEnabled" value="false" /> </bean>
 * 
 * @author sunff
 *
 */
public class HessianClientFactoryBean implements MethodInterceptor,
		FactoryBean<Object>, InitializingBean {

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
		TraceContext attachments = null;
		if (ThreadLocalHolder.instance().getTraceContext() != null) {
			attachments = ThreadLocalHolder.instance().getTraceContext();
		}
		return serviceProxyProxy.call(invocation.getMethod().getName(),
				invocation.getArguments(), attachments);
	}

	@Override
	public void afterPropertiesSet() throws Exception {
		org.springframework.remoting.caucho.HessianProxyFactoryBean h = new org.springframework.remoting.caucho.HessianProxyFactoryBean();
		h.setServiceUrl(serviceUrl);
		h.setServiceInterface(ServiceProxy.class);
		h.afterPropertiesSet();
		this.serviceProxyProxy = (ServiceProxy) h.getObject();
		this.serviceProxy = ProxyFactory.getProxy(serviceInterface, this);
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
