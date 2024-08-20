from google.protobuf.internal import enum_type_wrapper as _enum_type_wrapper
from google.protobuf import descriptor as _descriptor
from google.protobuf import message as _message
from typing import ClassVar as _ClassVar, Optional as _Optional, Union as _Union

DESCRIPTOR: _descriptor.FileDescriptor

class ServerType(int, metaclass=_enum_type_wrapper.EnumTypeWrapper):
    __slots__ = ()
    GOLANG: _ClassVar[ServerType]
    PYTHON: _ClassVar[ServerType]
    JAVA: _ClassVar[ServerType]
GOLANG: ServerType
PYTHON: ServerType
JAVA: ServerType

class Req(_message.Message):
    __slots__ = ("userName", "sender", "recipient", "msg")
    USERNAME_FIELD_NUMBER: _ClassVar[int]
    SENDER_FIELD_NUMBER: _ClassVar[int]
    RECIPIENT_FIELD_NUMBER: _ClassVar[int]
    MSG_FIELD_NUMBER: _ClassVar[int]
    userName: str
    sender: ServerType
    recipient: ServerType
    msg: str
    def __init__(self, userName: _Optional[str] = ..., sender: _Optional[_Union[ServerType, str]] = ..., recipient: _Optional[_Union[ServerType, str]] = ..., msg: _Optional[str] = ...) -> None: ...

class Resp(_message.Message):
    __slots__ = ("msg",)
    MSG_FIELD_NUMBER: _ClassVar[int]
    msg: str
    def __init__(self, msg: _Optional[str] = ...) -> None: ...
