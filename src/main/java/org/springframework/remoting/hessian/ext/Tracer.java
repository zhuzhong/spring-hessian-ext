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

    public void setTraceId(Long traceId) {
        ThreadLocalHolder.instance().setTraceContext(new TraceContext(traceId));
    }

    public Long getTracerId() {
        if (ThreadLocalHolder.instance().getTraceContext() != null) {
            return ThreadLocalHolder.instance().getTraceContext().getTraceId();
        } else {
            return null;
        }
    }

    public void clear() {
        ThreadLocalHolder.instance().clear();
    }
}
