/**
 * 
 */
package org.springframework.remoting.hessian.ext;

import java.io.Serializable;

/**日志追踪上下文,现在只需要一个traceId,后
 * @author Administrator
 *
 */
public class TraceContext implements Serializable {

    private final Long traceId;

    public TraceContext(Long traceId) {
        this.traceId = traceId;
    }

    public Long getTraceId() {
        return this.traceId;
    }
    
    
    
}
