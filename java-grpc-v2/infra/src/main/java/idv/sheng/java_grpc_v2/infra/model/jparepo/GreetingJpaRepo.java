package idv.sheng.java_grpc_v2.infra.model.jparepo;

import idv.sheng.java_grpc_v2.infra.model.po.GreetingPo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface GreetingJpaRepo extends JpaRepository<GreetingPo, UUID> {
}
