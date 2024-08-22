package idv.sheng.java_grpc_v2.infra.adapters.repo;

import idv.sheng.java_grpc_v2.app.ports.repo.GreetingRepo;
import idv.sheng.java_grpc_v2.domain.entity.Greeting;
import idv.sheng.java_grpc_v2.domain.valueobj.ServerType;
import idv.sheng.java_grpc_v2.infra.model.jparepo.GreetingJpaRepo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@Slf4j
@SpringBootTest
@ActiveProfiles("h2")
class GreetingRepoTest {
    @Autowired
    private GreetingRepo greetingRepo;
    @Autowired
    private GreetingJpaRepo greetingJpaRepo;

    @Test
    public void save() {
        Greeting greeting = Greeting.builder()
                .id(UUID.randomUUID())
                .userName("java")
                .sender(ServerType.JAVA)
                .recipient(ServerType.PYTHON)
                .msg("testing")
                .build();
        greetingRepo.save(greeting);

        greetingJpaRepo.findById(greeting.getId()).ifPresentOrElse(
                (greetingPo) -> {
                    log.info("GreetingPo: {}", greetingPo);
                    assertEquals(greetingPo.getUserName(), greeting.getUserName());
                    assertEquals(greetingPo.getSender(), greeting.getSender().getCode());
                    assertEquals(greetingPo.getRecipient(), greeting.getRecipient().getCode());
                    assertEquals(greetingPo.getMsg(), greeting.getMsg());
                },
                () -> fail("No GreetingPo found")
        );
    }

}