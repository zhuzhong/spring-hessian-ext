/**
 * 
 */
package org.springframework.remoting.hessian.ext;

/**
 * @author sunff
 *
 */
public interface ServiceProxy {

	
	
	Object call(String methodName,Object[] args,TraceContext context);
}
