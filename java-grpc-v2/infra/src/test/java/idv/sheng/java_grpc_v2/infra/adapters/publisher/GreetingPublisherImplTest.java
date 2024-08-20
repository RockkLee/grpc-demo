package idv.sheng.java_grpc_v2.infra.adapters.publisher;

import idv.sheng.java_grpc_v2.GreetingGrpc;
import idv.sheng.java_grpc_v2.app.dto.GreetingDto;
import idv.sheng.java_grpc_v2.app.ports.publisher.GreetingPublisher;
import idv.sheng.java_grpc_v2.domain.valueobj.ServerType;
import idv.sheng.java_grpc_v2.infra.config.ApplicationContextProvider;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class GreetingPublisherImplTest {
    @Autowired
    private ApplicationContext context;
    @Autowired
    private GreetingPublisher greetingPublisher;
    @Test
    public void GreetingPublisherMapperTest() {
        GreetingDto dto = new GreetingDto(
                "user_name",
                ServerType.JAVA.getCode(),
                ServerType.PYTHON.getCode(),
                "msg"
        );
        GreetingGrpc.Req req = GreetingPublisherImpl.Mapper.toGrpcReq(dto);
        System.out.println(req);
        assertEquals(req.getSender(), GreetingGrpc.ServerType.JAVA);
        assertEquals(req.getRecipient(), GreetingGrpc.ServerType.PYTHON);
    }
}