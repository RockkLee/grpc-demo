package idv.sheng.java_grpc_v2.domain.entity;

import idv.sheng.java_grpc_v2.domain.entity.base.AggregateRoot;
import idv.sheng.java_grpc_v2.domain.valueobj.ServerType;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
public class Greeting extends AggregateRoot<UUID> {
    private String userName;
    private ServerType sender;
    private ServerType recipient;
    private String msg;

    public Greeting() {
        super();
    }

    @Builder
    public Greeting(UUID id, String userName, ServerType sender, ServerType recipient, String msg) {
        this.setId(id);
        this.userName = userName;
        this.sender = sender;
        this.recipient = recipient;
        this.msg = msg;
    }
}
