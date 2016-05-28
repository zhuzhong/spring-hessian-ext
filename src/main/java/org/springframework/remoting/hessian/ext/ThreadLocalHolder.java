/**
 * 
 */
package org.springframework.remoting.hessian.ext;


/**
 * @author sunff
 *
 */
public class ThreadLocalHolder {

	//private static ThreadLocal<Map<String, Object>> currentTheadLocal = new ThreadLocal<Map<String, Object>>();
	private static ThreadLocal<Object[]> currentTheadLocal = new ThreadLocal<Object[]>();
	private static ThreadLocalHolder ins = new ThreadLocalHolder();

	private ThreadLocalHolder() {

	}

	public static ThreadLocalHolder instance() {
		return ins;
	}

	
	  public Object[] getAttachments() { return currentTheadLocal.get(); }
	 
	  public void setAttachments(Object[] id) { currentTheadLocal.set(id); }
	 

	/*public void setUserAttribute(String key, Object value) {
		Map<String, Object> result = currentTheadLocal.get();
		if (result == null) {
			result = new HashMap<String, Object>();
			currentTheadLocal.set(result);
		}
		result.put(key, value);
	}

	public Object getUserAttribute(String key) {
		if (currentTheadLocal.get() != null) {
			return currentTheadLocal.get().get(key);
		}
		return null;
	}

	public Map<String, Object> getUserAttributes() {
		return currentTheadLocal.get();
	}
*/
	public void clear() {
		currentTheadLocal.remove();
	}

}
