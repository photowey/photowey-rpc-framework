package com.photowey.rpc.core.provider;

/**
 * the Provider Service info
 *
 * @author WcJun
 * @date 2020/02/22
 * @since v1.0.0
 */
public class ProviderServiceInfo {
    /**
     * the protocol
     */
    private String protocol;
    /**
     * the server network IP
     */
    private String serverIp;
    /**
     * the server port
     */
    private int serverPort;
    /**
     * the server network port
     */
    private int networkPort;
    /**
     * connect time out time
     */
    private long timeout;
    /**
     * the weight of service provider
     */
    private int weight;
    /**
     * the version of service
     */
    private String version;
    /**
     * the server app name
     */
    private String appName;

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    public String getServerIp() {
        return serverIp;
    }

    public void setServerIp(String serverIp) {
        this.serverIp = serverIp;
    }

    public int getServerPort() {
        return serverPort;
    }

    public void setServerPort(int serverPort) {
        this.serverPort = serverPort;
    }

    public int getNetworkPort() {
        return networkPort;
    }

    public void setNetworkPort(int networkPort) {
        this.networkPort = networkPort;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    // =====================================================

    public ProviderServiceInfo(
            String protocol, String serverIp,
            int serverPort, int networkPort,
            long timeout, int weight,
            String version, String appName) {
        this.protocol = protocol;
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.networkPort = networkPort;
        this.timeout = timeout;
        this.weight = weight;
        this.version = version;
        this.appName = appName;
    }

    // =====================================================

    public static ProviderServiceInfo.ProviderServiceInfoBuilder builder() {
        return new ProviderServiceInfo.ProviderServiceInfoBuilder();
    }

    // =====================================================

    public static class ProviderServiceInfoBuilder {
        private String protocol;
        private String serverIp;
        private int serverPort;
        private int networkPort;
        private long timeout;
        private int weight;
        private String version;
        private String appName;

        ProviderServiceInfoBuilder() {
        }

        public ProviderServiceInfo.ProviderServiceInfoBuilder protocol(String protocol) {
            this.protocol = protocol;
            return this;
        }

        public ProviderServiceInfo.ProviderServiceInfoBuilder serverIp(String serverIp) {
            this.serverIp = serverIp;
            return this;
        }

        public ProviderServiceInfo.ProviderServiceInfoBuilder serverPort(int serverPort) {
            this.serverPort = serverPort;
            return this;
        }

        public ProviderServiceInfo.ProviderServiceInfoBuilder networkPort(int networkPort) {
            this.networkPort = networkPort;
            return this;
        }

        public ProviderServiceInfo.ProviderServiceInfoBuilder timeout(long timeout) {
            this.timeout = timeout;
            return this;
        }

        public ProviderServiceInfo.ProviderServiceInfoBuilder weight(int weight) {
            this.weight = weight;
            return this;
        }

        public ProviderServiceInfo.ProviderServiceInfoBuilder version(String version) {
            this.version = version;
            return this;
        }

        public ProviderServiceInfo.ProviderServiceInfoBuilder appName(String appName) {
            this.appName = appName;
            return this;
        }

        public ProviderServiceInfo build() {
            return new ProviderServiceInfo(this.protocol, this.serverIp, this.serverPort, this.networkPort, this.timeout, this.weight, this.version, this.appName);
        }
    }
}
