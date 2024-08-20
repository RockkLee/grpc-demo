import grpc

from python_grpc_v2.domain.valueobj.server_type import ServerType
from python_grpc_v2.infra.grpc.protos.greeting import greeting_pb2_grpc, greeting_pb2


def run_stub(user_name: str, sender: ServerType, recipient: ServerType, msg: str):
    # Connect to the gRPC server
    with grpc.insecure_channel(recipient.server_address) as channel:
        # Create a stub (client)
        stub = greeting_pb2_grpc.GreetingServiceStub(channel)

        # Create a request message
        request = greeting_pb2.Req(
            userName=user_name,
            sender=greeting_pb2.ServerType.Name(sender.code),
            recipient=greeting_pb2.ServerType.Name(recipient.code),
            msg=msg
        )

        # Make the call and receive the response
        resp = stub.Greet(request)

        # Print the response from the server
        print(resp.msg)
        return resp.msg
