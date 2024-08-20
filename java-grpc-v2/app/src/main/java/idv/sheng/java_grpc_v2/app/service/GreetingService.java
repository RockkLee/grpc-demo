package idv.sheng.java_grpc_v2.app.service;

import idv.sheng.java_grpc_v2.app.dto.GreetingDto;
import idv.sheng.java_grpc_v2.app.ports.publisher.GreetingPublisher;
import idv.sheng.java_grpc_v2.app.ports.repo.GreetingRepo;
import idv.sheng.java_grpc_v2.domain.entity.Greeting;
import idv.sheng.java_grpc_v2.domain.valueobj.ServerType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeoutException;

@Service
public class GreetingService {
    @Autowired
    private GreetingPublisher greetingPublisher;
    @Autowired
    private GreetingRepo greetingRepo;

    public String greet(GreetingDto dto) throws InterruptedException, TimeoutException {
        return greetingPublisher.publish(dto);
    }

    public String beGreeted(GreetingDto dto) {
        Greeting greeting = Mapper.toEntity(dto);
        greetingRepo.save(greeting);
        return "Hello " + dto.userName();
    }

    static class Mapper {
        public static Greeting toEntity(GreetingDto dto) {
            return Greeting.builder()
                    .id(UUID.randomUUID())
                    .userName(dto.userName())
                    .sender(ServerType.of(dto.senderCode()))
                    .recipient(ServerType.of(dto.recipientCode()))
                    .msg(dto.msg())
                    .build();
        }
    }
}
