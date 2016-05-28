package org.springframework.remoting.hessian.ext;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.springframework.jmx.access.InvocationFailureException;

public class ServiceProxyImpl implements ServiceProxy {

	private Object target;

	public ServiceProxyImpl(Object target) {
		this.target = target;
	}

	@Override
	public Object call(String methodName, Object[] args, Object[] attements) {

		if (attements != null) {
			
			ThreadLocalHolder.instance().setAttachments(attements);
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

		return ret;
	}

}
