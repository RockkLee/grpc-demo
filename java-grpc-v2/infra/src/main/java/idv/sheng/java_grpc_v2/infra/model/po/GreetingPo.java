package idv.sheng.java_grpc_v2.infra.model.po;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "greeting")
public class GreetingPo {
    @Id
    private UUID id;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column(name = "sender", nullable = false)
    private int sender;

    @Column(name = "recipient", nullable = false)
    private int recipient;

    @Column(name = "message", nullable = false)
    private String msg;
}
