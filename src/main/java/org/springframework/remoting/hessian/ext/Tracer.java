/**
 * 
 */
package org.springframework.remoting.hessian.ext;

/**
 * @author sunff
 *
 */
public class Tracer {

	private Tracer() {

	}

	public static Tracer instance = new Tracer();

	public void setTracerId(String tracerId) {
		ThreadLocalHolder.instance().setAttachments(new Object[] { tracerId });
	}

	public String getTracerId() {
		if (ThreadLocalHolder.instance().getAttachments() != null) {
			return (String) ThreadLocalHolder.instance().getAttachments()[0];
		} else {
			return null;
		}
	}

	public void clear() {
		ThreadLocalHolder.instance().clear();
	}
}
