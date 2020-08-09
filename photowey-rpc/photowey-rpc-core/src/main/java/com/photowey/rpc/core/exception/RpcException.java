/*
 * Copyright © 2020 photowey (photowey@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
