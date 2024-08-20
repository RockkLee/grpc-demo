package idv.sheng.java_grpc_v2.infra.config;

import idv.sheng.java_grpc_v2.infra.grpc.client.GrpcClientType;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class GrpcClientTypeConfig {
    @Value("${grpc.client.golang.host}")
    private String golangHost;
    @Value("${grpc.client.golang.port}")
    private int golangPort;
    @Value("${grpc.client.python.host}")
    private String pythonHost;
    @Value("${grpc.client.python.port}")
    private int pythonPort;

    @PostConstruct
    public void init() {
        GrpcClientType clientType = GrpcClientType.valueOf("TO_GOLANG");
        clientType.setHost(golangHost);
        clientType.setPort(golangPort);
        clientType = GrpcClientType.valueOf("TO_PYTHON");
        clientType.setHost(pythonHost);
        clientType.setPort(pythonPort);
    }
}
