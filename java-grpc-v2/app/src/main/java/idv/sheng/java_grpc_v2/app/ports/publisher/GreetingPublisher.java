package idv.sheng.java_grpc_v2.app.ports.publisher;

import idv.sheng.java_grpc_v2.app.dto.GreetingDto;

import java.util.concurrent.TimeoutException;

public interface GreetingPublisher {
    String publish(GreetingDto greeting) throws InterruptedException, TimeoutException;
}
