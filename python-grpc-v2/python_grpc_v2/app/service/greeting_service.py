import uuid

from python_grpc_v2.app.adapter.publisher.greeting_publisher import GreetingPublisher
from python_grpc_v2.app.adapter.repo.greeting_repo import GreetingRepo
from python_grpc_v2.common.metaclass.singalton_meta import SingletonMeta
from python_grpc_v2.domain.entity.greeting import Greeting
from python_grpc_v2.infra.orm.connection import MySession


class GreetingService(metaclass=SingletonMeta):
    def __init__(self):
        self.__greeting_repo = GreetingRepo()
        self.__greeting_publisher = GreetingPublisher()

    async def save(self, user_name: str, sender: int, recipient: int, msg: str, db: MySession):
        greeting = Greeting(id=uuid.uuid4(), user_name=user_name, sender=sender, recipient=recipient, msg=msg)
        await self.__greeting_repo.save(greeting, db)
        await db.commit()

    async def send_grpc(self, user_name: str, sender: int, recipient: int, msg: str, db: MySession) -> str:
        greeting = Greeting(id=uuid.uuid4(), user_name=user_name, sender=sender, recipient=recipient, msg=msg)
        return await self.__greeting_publisher.publish(greeting)
