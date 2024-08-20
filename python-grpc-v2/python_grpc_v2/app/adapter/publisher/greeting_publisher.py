from python_grpc_v2.common.metaclass.singalton_meta import SingletonMeta
from python_grpc_v2.domain.entity.greeting import Greeting
from python_grpc_v2.infra.grpc.client import greeting_grpc_client


class GreetingPublisher(metaclass=SingletonMeta):
    def __init__(self):
        pass

    async def publish(self, greeting: Greeting) -> str:
        return greeting_grpc_client.run_stub(greeting.user_name, greeting.sender, greeting.recipient, greeting.msg)