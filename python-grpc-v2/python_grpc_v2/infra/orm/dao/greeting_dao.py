from python_grpc_v2.common.metaclass.singalton_meta import SingletonMeta
from python_grpc_v2.infra.orm.connection import MySession
from python_grpc_v2.infra.orm.models.greeting_model import GreetingModel


class GreetingDao(metaclass=SingletonMeta):
    async def save(self, greeting_model: GreetingModel, db: MySession):
        db.add(greeting_model)
