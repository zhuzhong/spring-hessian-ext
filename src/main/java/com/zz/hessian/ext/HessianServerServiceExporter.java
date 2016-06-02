/**
 * 
 */
package com.zz.hessian.ext;

import org.springframework.remoting.caucho.HessianServiceExporter;

import com.zz.hessian.ext.impl.ServiceProxyImpl;

/**
 * server 端 <bean name="/helloService"
 * class="org.springframework.remoting.caucho.HessianServiceExporter"> <br>
 * <property name="service" ref="helloServiceImpl" /> <br>
 * <property name="serviceInterface"
 * value="com.test.hello.service.HelloService" />
 * 
 * </bean>
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
