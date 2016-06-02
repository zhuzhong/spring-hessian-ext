/**
 * 
 */
package com.zz.hessian.ext;

import java.io.Serializable;

/**日志追踪上下文,现在只需要一个traceId,后续增加其他参数
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
    
    
    
   /* private String className; //接口类名称
    private String methodName; //方法名称
    private String args; //多个参数组合成 一个string
    */
}
