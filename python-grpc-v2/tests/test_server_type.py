import unittest

from python_grpc_v2.domain.valueobj.server_type import ServerType
from python_grpc_v2.infra.grpc.protos.greeting import greeting_pb2


class ServerTypeTest(unittest.TestCase):

    def test_from_code_valid(self):
        # Test valid codes
        self.assertEqual(ServerType.from_code(0).code, 0)
        self.assertEqual(ServerType.from_code(1).code, 1)
        self.assertEqual(ServerType.from_code(2).code, 2)

    def test_from_code_invalid(self):
        # Test invalid code, should raise ValueError
        with self.assertRaises(ValueError) as context:
            ServerType.from_code(3)

    def test_grpc_server_type(self):
        # Test grpc server type
        grpc_server_type = greeting_pb2.ServerType.Name(0)
        print(grpc_server_type)

