from enum import Enum

from python_grpc_v2.infra.config.config import GRPC_SERVER_GOLANG, GRPC_SERVER_JAVA, GRPC_SERVER_PYTHON


class ServerType(Enum):
    GOLANG = 0
    PYTHON = 1
    JAVA = 2

    def __init__(self, code):
        self.code = code

        client_map = {
            0: GRPC_SERVER_GOLANG,
            1: GRPC_SERVER_PYTHON,
            2: GRPC_SERVER_JAVA
        }
        self.server_address = client_map[code]

    @staticmethod
    def from_code(code):
        for status in ServerType:
            if status.code == code:
                return status
        raise ValueError(f"No matching enum value for code: {code}")
