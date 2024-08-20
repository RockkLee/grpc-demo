package idv.sheng.java_grpc_v2.domain.entity.base;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Entity<T> {
    private T id;
}
