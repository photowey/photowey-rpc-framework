package com.photowey.rpc.client.pull;

import java.io.IOException;

/**
 * the pull service from zk Executor
 *
 * @author WcJun
 * @date 2020/02/23
 * @since v1.0.0
 */
public interface ServiceExecutor {
    void pullService() throws IOException;
}
