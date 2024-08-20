package idv.sheng.java_grpc_v2.app.ports.repo;

import idv.sheng.java_grpc_v2.domain.entity.Greeting;

public interface GreetingRepo {
    public void save(Greeting greeting);
}
