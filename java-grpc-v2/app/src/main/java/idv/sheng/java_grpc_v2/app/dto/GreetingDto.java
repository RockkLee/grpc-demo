package idv.sheng.java_grpc_v2.app.dto;

import idv.sheng.java_grpc_v2.domain.valueobj.ServerType;

public record GreetingDto(
    String userName,
    int senderCode,
    int recipientCode,
    String msg
) {
}
