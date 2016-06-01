/**
 * 
 */
package org.springframework.remoting.hessian.ext;

import com.sohu.idcenter.IdWorker;

/**
 * @author Administrator
 *
 */
public class TraceIdUtil {

    private static IdWorker worker = new IdWorker(System.currentTimeMillis());

    public static Long getId() {
        return worker.getId();
    }
}
