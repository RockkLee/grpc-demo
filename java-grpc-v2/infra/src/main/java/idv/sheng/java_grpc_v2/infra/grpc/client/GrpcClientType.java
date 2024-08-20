package idv.sheng.java_grpc_v2.infra.grpc.client;

import lombok.Getter;

@Getter
public enum GrpcClientType {
    // get values from GrpcClientTypeConfig
    TO_GOLANG,
    TO_PYTHON;

    private String host;
    private int port;

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
