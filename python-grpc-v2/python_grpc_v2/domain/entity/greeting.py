import uuid

from python_grpc_v2.domain.base import AggregateRoot
from python_grpc_v2.domain.valueobj.server_type import ServerType


class Greeting(AggregateRoot):
    def __init__(self, id: uuid, user_name: str, sender: int, recipient: int, msg: str):
        super().__init__(id)
        self.user_name = user_name
        self.sender = ServerType.from_code(sender)
        self.recipient = ServerType.from_code(recipient)
        self.msg = msg
