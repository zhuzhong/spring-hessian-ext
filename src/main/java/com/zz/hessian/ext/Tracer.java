/**
 * 
 */
package com.zz.hessian.ext;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.zz.hessian.ext.util.TraceIdUtil;

/**
 * @author sunff
 *
 */
public class Tracer {

    private static final Log log = LogFactory.getLog(Tracer.class);

    private Tracer() {

    }

    /*
     * public static Tracer instance = new Tracer();
     * 
     * public void setTraceId(Long traceId) {
     * ThreadLocalHolder.instance().setTraceContext(new TraceContext(traceId));
     * }
     * 
     * public Long getTracerId() { if
     * (ThreadLocalHolder.instance().getTraceContext() != null) { return
     * ThreadLocalHolder.instance().getTraceContext().getTraceId(); } else {
     * return null; } }
     * 
     * public void clear() { ThreadLocalHolder.instance().clear(); }
     */

    private static ThreadLocal<TraceContext> local = new ThreadLocal<TraceContext>();

    public static void beginTracer() {
        Long traceId = TraceIdUtil.getId();
        local.set(new TraceContext(traceId));
        log.trace(new LogBean(traceId, null, null, null));
    }

    public static void beginTracer(TraceContext context) {
        local.set(context);
        Long traceId = local.get().getTraceId();
        log.trace(new LogBean(traceId, null, null, null));
    }

    public static void endTracer() {
        local.remove();
    }

    public static TraceContext getTraceContext() {
        return local.get();
    }

    public static void trace(String clazz, String method, Object[] args) {
        log.trace(new LogBean(local.get().getTraceId(), clazz, method, args));
    }
}
