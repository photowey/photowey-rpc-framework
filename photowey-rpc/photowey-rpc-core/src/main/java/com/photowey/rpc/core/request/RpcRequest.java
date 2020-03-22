package com.photowey.rpc.core.request;

import com.photowey.rpc.core.context.RpcContext;

import java.io.Serializable;
import java.net.URL;

/**
 * RpcRequest
 *
 * @author WcJun
 * @date 2020/02/21
 * @since v1.0.0
 */
public interface RpcRequest extends Serializable {

    /**
     * get the current request  session Id
     *
     * @return
     */
    String getSessionId();

    /**
     * get the remote class name
     *
     * @return the remote class name
     */
    String getClassName();

    /**
     * get the remote execute method name
     *
     * @return the remote execute method name
     */
    String getMethodName();

    /**
     * get the Types of method parameters
     *
     * @return the Types of method parameters
     */
    Class<?>[] getParameterTypes();

    /**
     * get the method parameters
     *
     * @return the method parameters
     */
    Object[] getParameters();

    /**
     * get the remote dubbo
     *
     * @return the remote dubbo
     */
    URL getUrl();

    /**
     * set the remote dubbo
     *
     * @param url the remote dubbo
     */
    void setUrl(URL url);

    /**
     * get the rpc request context
     *
     * @return RpcContext
     * @author WcJun
     * @date 2020/02/21
     */
    RpcContext getRpcContext();
}
