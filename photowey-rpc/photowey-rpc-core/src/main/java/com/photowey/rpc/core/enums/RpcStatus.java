package com.photowey.rpc.core.enums;

/**
 * RpcStatus
 *
 * @author WcJun
 * @date 2020/02/21
 * @since v1.0.0
 */
public enum RpcStatus {

    /**
     * the request execute success
     */
    SUCCESS("SUCCESS", 1),

    /**
     * the request execute failure
     */
    FAILURE("FAILURE", 0),
    /**
     * the service not available
     */
    NO_AVAILABLE("NO_AVAILABLE", -1);

    private String name;

    private int value;

    RpcStatus(String name, int value) {
        this.name = name;
        this.value = value;
    }

    public String toName() {
        return name;
    }

    public int toValue() {
        return value;
    }

    public static RpcStatus fromName(String target) {
        for (RpcStatus value : values()) {
            String toName = value.toName();
            if (target.equals(toName)) {
                return value;
            }
        }

        return null;
    }

    public static RpcStatus fromValue(int target) {
        for (RpcStatus value : values()) {
            int toValue = value.toValue();
            if (target == toValue) {
                return value;
            }
        }

        return null;
    }
}
