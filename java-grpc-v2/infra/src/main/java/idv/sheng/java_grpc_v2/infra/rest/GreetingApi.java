package idv.sheng.java_grpc_v2.infra.rest;

import idv.sheng.java_grpc_v2.app.dto.GreetingDto;
import idv.sheng.java_grpc_v2.app.service.GreetingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeoutException;

@Slf4j
@RestController
public class GreetingApi {
    @Autowired
    private GreetingService greetingService;

    @GetMapping("/greet")
    public String greetPython(String userName, int sender, int recipient, String msg) throws InterruptedException, TimeoutException {
        GreetingDto dto = new GreetingDto(userName, sender, recipient, msg);
        return greetingService.greet(dto);
    }
}
