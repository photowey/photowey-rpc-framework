package com.photowey.rpc.client.properties;

import com.photowey.rpc.core.util.RpcUtils;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * the client config properties
 * as use the {@literal @}ConfigurationProperties
 *
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
@ConfigurationProperties(prefix = "rpc", ignoreUnknownFields = true)
public class RpcClientProperties {

    private RpcClient client = new RpcClient();
    private ClusterStrategy cluster = new ClusterStrategy();

    public static class RpcClient {

        private RpcZookeeper zk = new RpcZookeeper();
        private Api api = new Api();

        public static class RpcZookeeper {
            /**
             * the zk root
             */
            private String root;
            /**
             * the zk connect address
             */
            private String connectString;
            /**
             * the session timeout time(ms)
             */
            private int sessionTimeout = 6000;
            /**
             * the connection timeout time(ms)
             */
            private int connectionTimeout = 6000;

            public RpcZookeeper() {
            }

            public RpcZookeeper(String root, String connectString) {
                this.root = root;
                this.connectString = connectString;
            }

            public String getRoot() {
                return root;
            }

            public void setRoot(String root) {
                this.root = root;
            }

            public String getConnectString() {
                return connectString;
            }

            public void setConnectString(String connectString) {
                this.connectString = connectString;
            }

            public int getSessionTimeout() {
                return sessionTimeout;
            }

            public void setSessionTimeout(int sessionTimeout) {
                this.sessionTimeout = sessionTimeout;
            }

            public int getConnectionTimeout() {
                return connectionTimeout;
            }

            public void setConnectionTimeout(int connectionTimeout) {
                this.connectionTimeout = connectionTimeout;
            }
        }

        public static class Api {
            private String packages;

            public String[] getPackages() {
                if (RpcUtils.isEmpty(this.packages)) {
                    return new String[0];
                }
                return this.packages.split(",");
            }

            public void setPackages(String packages) {
                this.packages = packages;
            }
        }

        public RpcZookeeper getZookeeper() {
            return zk;
        }

        public Api getApi() {
            return api;
        }
    }

    public static class ClusterStrategy {
        /**
         * the selector execute select the target service invoke strategy
         */
        private String strategy;

        public ClusterStrategy() {
        }

        public ClusterStrategy(String strategy) {
            this.strategy = strategy;
        }

        public String getStrategy() {
            return strategy;
        }

        public void setStrategy(String strategy) {
            this.strategy = strategy;
        }
    }

    // ===================================================================

    public RpcClient getClient() {
        return client;
    }

    public ClusterStrategy getCluster() {
        return cluster;
    }
}
