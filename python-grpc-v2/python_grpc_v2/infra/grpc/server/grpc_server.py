import asyncio
import os
from concurrent import futures

import grpc

from python_grpc_v2.infra.config.config import GRPC_VERBOSITY, GRPC_TRACE
from python_grpc_v2.infra.grpc.protos.greeting import greeting_pb2_grpc
from python_grpc_v2.infra.grpc.server.greeting_grpc_server import GreetingGrpcServer


async def start_grpc_server():
    os.environ["GRPC_VERBOSITY"] = GRPC_VERBOSITY
    os.environ["GRPC_TRACE"] = GRPC_TRACE

    server = grpc.aio.server()

    # add grpc services
    greeting_pb2_grpc.add_GreetingServiceServicer_to_server(GreetingGrpcServer(), server)

    server.add_insecure_port('127.0.0.1:50052')
    await server.start()
    await server.wait_for_termination()


if __name__ == '__main__':
    asyncio.run(start_grpc_server())
