/**
 * 
 */
package org.springframework.remoting.hessian.ext;

import org.springframework.remoting.caucho.HessianServiceExporter;

/**
 * server 端 <bean name="/helloService"
 * class="org.springframework.remoting.caucho.HessianServiceExporter"> <property
 * name="service" ref="helloServiceImpl" /> <property name="serviceInterface"
 * value="com.j1.crcare.hello.service.HelloService" /> </bean>
 * 
 * @author sunff
 *
 */
public class HessianServerServiceExporter extends HessianServiceExporter {

	private Class realServiceInterface;

	private ServiceProxy serviceProxy;

	public void setService(Object service) {
		this.serviceProxy = new ServiceProxyImpl(service);
		super.setService(serviceProxy);
	}

	/**
	 * Set the interface of the service to export. The interface must be
	 * suitable for the particular service and remoting strategy.
	 */
	public void setServiceInterface(Class serviceInterface) {
		this.realServiceInterface = serviceInterface;
		super.setServiceInterface(ServiceProxy.class);
	}
}
