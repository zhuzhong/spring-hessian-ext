# spring-hessian-ext
扩展spring集成hessian模块，进行非业务参数传递



##功能
扩展spring集成hessian模块，进行非业务参数传递

适用场景：
在进行远程调中，为了跟踪一次调用整个过程，需要传递唯一的traceid等参数，而这些参数本不是业务参数，
为了对于业务无侵入，进行网络调用链路参数传递;

##用法：
1.原始spring集成hessian 服务端的示例:
```xml
<!--  server 端    -->
  <bean name="/helloService" class="org.springframework.remoting.caucho.HessianServiceExporter">  
    <property name="service" ref="helloServiceImpl" />  
    <property name="serviceInterface" value="com.test.hello.service.HelloService" />  
  </bean>  
<!-- 替换为 -->
 <bean name="/helloService" class="org.springframework.remoting.hessian.ext.HessianServerServiceExporter">  
    <property name="service" ref="helloServiceImpl" />  
    <property name="serviceInterface" value="com.test.hello.service.HelloService" />  
  </bean>  
```

2.原始spring 集成hessian 客户端的示例
```xml
  <!-- client 端  -->
    <bean id="helloService" class="org.springframework.remoting.caucho.HessianProxyFactoryBean">  
    <property name="serviceUrl" value="http://localhost:8000/exam_3/hessian/helloService" />  
    <property name="serviceInterface" value="com.test.hello.service.HelloService" />
  </bean>  
  <!-- 替换为-->
  
    <bean id="helloService" class="org.springframework.remoting.hessian.ext.HessianClientFactoryBean">  
    <property name="serviceUrl" value="http://localhost:8000/exam_3/hessian/helloService" />  
    <property name="serviceInterface" value="com.test.hello.service.HelloService" />     
  </bean>
```


3.客户端调用 
org.springframework.remoting.hessian.ext.ThreadLocalHolder.setAttachments(Object[] id) 
方法设置需要传的参数

4.服务端调用
org.springframework.remoting.hessian.ext.ThreadLocalHolder.getAttachments()
获取相应的参数 

5.对于只传输一个string类型的参数可使用
org.springframework.remoting.hessian.ext.Tracer中的方法，这个相对比较简单

