# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: python_grpc_v2/infra/grpc/protos/greeting/greeting.proto
# Protobuf Python Version: 5.26.1
"""Generated protocol buffer code."""
from google.protobuf import descriptor as _descriptor
from google.protobuf import descriptor_pool as _descriptor_pool
from google.protobuf import symbol_database as _symbol_database
from google.protobuf.internal import builder as _builder
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor_pool.Default().AddSerializedFile(b'\n8python_grpc_v2/infra/grpc/protos/greeting/greeting.proto\"a\n\x03Req\x12\x10\n\x08userName\x18\x01 \x01(\t\x12\x1b\n\x06sender\x18\x02 \x01(\x0e\x32\x0b.ServerType\x12\x1e\n\trecipient\x18\x03 \x01(\x0e\x32\x0b.ServerType\x12\x0b\n\x03msg\x18\x04 \x01(\t\"\x13\n\x04Resp\x12\x0b\n\x03msg\x18\x01 \x01(\t*.\n\nServerType\x12\n\n\x06GOLANG\x10\x00\x12\n\n\x06PYTHON\x10\x01\x12\x08\n\x04JAVA\x10\x02\x32)\n\x0fGreetingService\x12\x16\n\x05Greet\x12\x04.Req\x1a\x05.Resp\"\x00\x62\x06proto3')

_globals = globals()
_builder.BuildMessageAndEnumDescriptors(DESCRIPTOR, _globals)
_builder.BuildTopDescriptorsAndMessages(DESCRIPTOR, 'python_grpc_v2.infra.grpc.protos.greeting.greeting_pb2', _globals)
if not _descriptor._USE_C_DESCRIPTORS:
  DESCRIPTOR._loaded_options = None
  _globals['_SERVERTYPE']._serialized_start=180
  _globals['_SERVERTYPE']._serialized_end=226
  _globals['_REQ']._serialized_start=60
  _globals['_REQ']._serialized_end=157
  _globals['_RESP']._serialized_start=159
  _globals['_RESP']._serialized_end=178
  _globals['_GREETINGSERVICE']._serialized_start=228
  _globals['_GREETINGSERVICE']._serialized_end=269
# @@protoc_insertion_point(module_scope)
