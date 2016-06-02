/**
 * 
 */
package com.zz.hessian.ext;

import java.io.Serializable;
import java.net.InetAddress;
import java.net.UnknownHostException;

import com.alibaba.fastjson.JSON;

/**
 * @author Administrator
 *
 */
public class LogBean implements Serializable {

    private final Long traceId; // 跟踪id
    private final String method; // 接口方法
    private final String clazz;// 接口类

    private final Object[] args;

    private final long timeStamp;
    private String ip;

    /**
     * @param traceId
     * @param method
     * @param clazz
     * @param args
     */
    public LogBean(Long traceId, String clazz, String method, Object[] args) {
        if (traceId == null) {
            throw new RuntimeException("create logBean,traceId is null");
        }
        this.traceId = traceId;
        this.method = method;
        this.clazz = clazz;
        this.args = args;
        this.timeStamp = System.currentTimeMillis();
        try {
            this.ip = InetAddress.getLocalHost().toString();
        } catch (UnknownHostException e) {
            this.ip = null;
            // e.printStackTrace();
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("traceId=");
        sb.append(traceId);
        sb.append(",");
        sb.append("className=");
        sb.append(clazz);
        sb.append(",");
        sb.append("methodName=");

        sb.append(method);
        sb.append(",");
        sb.append("args=");
        sb.append(genArgsString());
        sb.append(",");
        sb.append("timeStamp=");
        sb.append(timeStamp);
        sb.append(",");
        sb.append("machineIp=");
        sb.append(ip);

        return sb.toString();
    }

    private String genArgsString() {
        if (args != null) {
            StringBuffer sb = new StringBuffer();
            sb.append("$begin$");
            for (int i = 0; i < args.length; i++) {
                sb.append(JSON.toJSONString(args[i]));
                sb.append(",");
            }
            sb.append("$end$");
            return sb.toString();
        }
        return null;
    }
}
