/**
 * 
 */
package com.zz.hessian.ext.util;

import com.sohu.idcenter.IdWorker;

/**
 * @author Administrator
 *
 */
public class TraceIdUtil {

    //private final static long l=System.currentTimeMillis()-1000;
    private static IdWorker worker = new IdWorker();

    public static Long getId() {
        return worker.getId();
    }
    
    
    public static void main(String args[] ){
        System.out.println(getId());
    }
}
