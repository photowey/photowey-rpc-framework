package com.photowey.rpc.core.exception;

import com.photowey.rpc.core.enums.RpcStatus;

/**
 * RpcException
 *
 * @author WcJun
 * @date 2020/02/21
 * @since v1.0.0
 */
public class RpcException extends RuntimeException {

    public int status;

    private static final long serialVersionUID = -2267091897489169518L;

    public RpcException() {
        super();
    }

    public RpcException(String message) {
        super(message);
        this.status = RpcStatus.FAILURE.toValue();
    }

    public RpcException(int status, String message) {
        super(message);
        this.status = status;
    }

    public RpcException(String message, Throwable cause) {
        super(message, cause);
        this.status = RpcStatus.FAILURE.toValue();
    }

    public RpcException(int status, String message, Throwable cause) {
        super(message, cause);
        this.status = status;
    }

    public RpcException(Throwable cause) {
        super(cause);
        this.status = RpcStatus.FAILURE.toValue();
    }

    public RpcException(int status, Throwable cause) {
        super(cause);
        this.status = status;
    }

    public RpcException(String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = RpcStatus.FAILURE.toValue();
    }

    public RpcException(int status, String message, Throwable cause,
                        boolean enableSuppression,
                        boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.status = status;
    }
}
