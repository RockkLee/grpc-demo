package idv.sheng.java_grpc_v2.infra.adapters.repo;

import idv.sheng.java_grpc_v2.app.ports.repo.GreetingRepo;
import idv.sheng.java_grpc_v2.domain.entity.Greeting;
import idv.sheng.java_grpc_v2.infra.model.jparepo.GreetingJpaRepo;
import idv.sheng.java_grpc_v2.infra.model.po.GreetingPo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GreetingRepoImpl implements GreetingRepo {
    @Autowired
    private GreetingJpaRepo greetingJpaRepo;

    @Override
    public void save(Greeting greeting) {
        GreetingPo greetingPo = Mapper.toPo(greeting);
        greetingJpaRepo.save(greetingPo);
    }

    static class Mapper {
        public static GreetingPo toPo(Greeting greeting) {
            return GreetingPo
                .builder()
                .id(greeting.getId())
                .userName(greeting.getUserName())
                .sender(greeting.getSender().getCode())
                .recipient(greeting.getRecipient().getCode())
                .msg(greeting.getMsg())
                .build();
        }
    }
}
