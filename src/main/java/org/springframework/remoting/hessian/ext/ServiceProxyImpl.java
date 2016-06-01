package org.springframework.remoting.hessian.ext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.jmx.access.InvocationFailureException;

public class ServiceProxyImpl implements ServiceProxy {

	private static Log log = LogFactory.getLog(ServiceProxyImpl.class);
	private Object target;

	public ServiceProxyImpl(Object target) {
		this.target = target;
	}

	@Override
	public Object call(String methodName, Object[] args, TraceContext context) {

		if (context != null) {
			log.info("tracecontext参数传过来了，可以取了...");
			ThreadLocalHolder.instance().setTraceContext(context);
		}

		Class<?>[] argClasses = new Class[args.length];
		for (int i = 0; i < argClasses.length; i++) {
			argClasses[i] = args[i].getClass();
		}
		Method method = null;
		try {
			method = target.getClass().getMethod(methodName, argClasses);
		} catch (Exception e) {
			throw new InvocationFailureException(e.getMessage(), e);
		}

		Object ret = null;
		try {
			ret = method.invoke(target, args);
		} catch (IllegalArgumentException e) {
			throw new InvocationFailureException(e.getMessage(), e);
		} catch (IllegalAccessException e) {
			throw new InvocationFailureException(e.getMessage(), e);
		} catch (InvocationTargetException e) {
			throw new InvocationFailureException(e.getMessage(), e);
		}

		try {
			return ret;
		} finally {
			ThreadLocalHolder.instance().clear();
		}
	}

}
