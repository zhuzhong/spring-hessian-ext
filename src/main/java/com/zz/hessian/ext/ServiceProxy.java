/**
 * 
 */
package com.zz.hessian.ext;

/**
 * @author sunff
 *
 */
public interface ServiceProxy {

	Object call(String methodName,Object[] args,TraceContext context);
}
