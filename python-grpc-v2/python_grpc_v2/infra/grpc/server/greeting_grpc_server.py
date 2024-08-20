import logging

from python_grpc_v2.app.service.greeting_service import GreetingService
from python_grpc_v2.common.log.logging import logger
from python_grpc_v2.infra.grpc.protos.greeting import greeting_pb2_grpc, greeting_pb2
from python_grpc_v2.infra.orm.connection import get_session, async_session


class GreetingGrpcServer(greeting_pb2_grpc.GreetingServiceServicer):
    def __init__(self):
        super().__init__()
        self.__greeting_service = GreetingService()

    async def Greet(self, request: greeting_pb2.Req, context):
        try:
            async with async_session() as db:
                response = greeting_pb2.Resp()
                await self.__greeting_service.save(request.userName, request.sender, request.recipient, request.msg, db)

                response.msg = "Hello, client java!"
                return response
        except Exception as e:
            logger.error(e)
            raise e
