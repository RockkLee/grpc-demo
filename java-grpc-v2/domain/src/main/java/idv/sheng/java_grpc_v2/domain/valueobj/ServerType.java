package idv.sheng.java_grpc_v2.domain.valueobj;

import lombok.Getter;

@Getter
public enum ServerType {
    GOLANG(0),
    PYTHON(1),
    JAVA(2);

    private final int code;

    ServerType(int code) {
        this.code = code;
    }

    public static ServerType of(int code) {
        return switch (code) {
            case 0 -> GOLANG;
            case 1 -> PYTHON;
            case 2 -> JAVA;
            default -> throw new IllegalStateException("Unexpected value: " + code);
        };
    }
}
