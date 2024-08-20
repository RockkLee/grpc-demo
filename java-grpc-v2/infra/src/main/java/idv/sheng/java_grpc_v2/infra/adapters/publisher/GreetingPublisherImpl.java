package idv.sheng.java_grpc_v2.infra.adapters.publisher;

import idv.sheng.java_grpc_v2.GreetingGrpc;
import idv.sheng.java_grpc_v2.app.dto.GreetingDto;
import idv.sheng.java_grpc_v2.app.ports.publisher.GreetingPublisher;
import idv.sheng.java_grpc_v2.infra.grpc.client.GrpcClientType;
import idv.sheng.java_grpc_v2.infra.grpc.client.GreetingGrpcClient;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeoutException;

@Service
public class GreetingPublisherImpl implements GreetingPublisher {
    @Override
    public String publish(GreetingDto greetingDto) throws InterruptedException, TimeoutException {
        GrpcClientType grpcClientType = switch (greetingDto.recipientCode()) {
            case 0 -> GrpcClientType.TO_GOLANG;
            case 1 -> GrpcClientType.TO_PYTHON;
            default -> throw new IllegalStateException("Unexpected value: " + greetingDto.recipientCode());
        };
        GreetingGrpcClient client = new GreetingGrpcClient(grpcClientType);
        GreetingGrpc.Resp resp = client.runStub(Mapper.toGrpcReq(greetingDto));

        return resp.getMsg();
    }

    static class Mapper {
        public static GreetingGrpc.Req toGrpcReq(GreetingDto dto) {
            return GreetingGrpc.Req.newBuilder()
                .setUserName(dto.userName())
                .setSender(
                        GreetingGrpc.ServerType.forNumber(dto.senderCode())
                )
                .setRecipient(
                        GreetingGrpc.ServerType.forNumber(dto.recipientCode())
                )
                .setMsg(dto.msg())
                .build();
        }
    }
}
