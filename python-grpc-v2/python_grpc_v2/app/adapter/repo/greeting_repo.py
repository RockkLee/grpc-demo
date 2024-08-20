import uuid

from python_grpc_v2.common.metaclass.singalton_meta import SingletonMeta
from python_grpc_v2.domain.entity.greeting import Greeting
from python_grpc_v2.infra.orm.connection import MySession
from python_grpc_v2.infra.orm.dao.greeting_dao import GreetingDao
from python_grpc_v2.infra.orm.models.greeting_model import GreetingModel


class GreetingRepo(metaclass=SingletonMeta):
    def __init__(self):
        self.greeting_dao = GreetingDao()

    async def save(self, greeting: Greeting, db: MySession):
        greeting_model = GreetingRepoMapper.from_entity(greeting)
        await self.greeting_dao.save(greeting_model, db)


class GreetingRepoMapper:
    @staticmethod
    def from_entity(entity: Greeting):
        return GreetingModel(
            id=entity.entity_id,
            user_name=entity.user_name,
            sender=entity.sender.code,
            recipient=entity.recipient.code,
            msg=entity.msg
        )
