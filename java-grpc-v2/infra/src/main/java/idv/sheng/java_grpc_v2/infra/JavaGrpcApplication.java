package idv.sheng.java_grpc_v2.infra;

import idv.sheng.java_grpc_v2.infra.grpc.server.GreetingGrpcServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.IOException;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@ComponentScan("idv.sheng.*")
//@ImportAutoConfiguration(GrpcClientAutoConfiguration.class)
public class JavaGrpcApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(JavaGrpcApplication.class, args);

        //start gRPC server
        io.grpc.Server server = io.grpc.ServerBuilder.forPort(50053)
                .addService(new GreetingGrpcServer())
                .build();
        server.start();
        server.awaitTermination();
    }

}
