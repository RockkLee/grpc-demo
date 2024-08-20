package idv.sheng.java_grpc_v2.infra.grpc.server;

import idv.sheng.java_grpc_v2.GreetingServiceGrpc;
import idv.sheng.java_grpc_v2.app.dto.GreetingDto;
import idv.sheng.java_grpc_v2.app.service.GreetingService;
import idv.sheng.java_grpc_v2.infra.config.ApplicationContextProvider;
import org.springframework.context.ApplicationContext;

public class GreetingGrpcServer extends GreetingServiceGrpc.GreetingServiceImplBase {
    private final ApplicationContext context = ApplicationContextProvider.getApplicationContext();
    private final GreetingService greetingService = context.getBean(GreetingService.class);

    @Override
    public void greet(idv.sheng.java_grpc_v2.GreetingGrpc.Req request,
                      io.grpc.stub.StreamObserver<idv.sheng.java_grpc_v2.GreetingGrpc.Resp> responseObserver) {
        GreetingDto dto = Mapper.toDto(request);
        String respMsg = greetingService.beGreeted(dto);

        responseObserver.onNext(
                idv.sheng.java_grpc_v2.GreetingGrpc.Resp.newBuilder()
                        .setMsg(respMsg)
                        .build()
        );
        responseObserver.onCompleted();
    }


    public static class Mapper {
        public static GreetingDto toDto(idv.sheng.java_grpc_v2.GreetingGrpc.Req req) {
            return new GreetingDto(
                    req.getUserName(),
                    req.getSender().getNumber(),
                    req.getRecipient().getNumber(),
                    req.getMsg()
            );
        }
    }
}
