import uuid
from unittest import IsolatedAsyncioTestCase
from sqlalchemy import select

from python_grpc_v2.app.adapter.repo.greeting_repo import GreetingRepo
from python_grpc_v2.domain.entity.greeting import Greeting
from python_grpc_v2.infra.orm.connection import async_session, engine
from python_grpc_v2.infra.orm.models.greeting_model import GreetingModel


class TestGreetingRepo(IsolatedAsyncioTestCase):
    async def asyncSetUp(self):
        self.db = async_session()
        self.engine = engine

    async def asyncTearDown(self):
        await self.db.close()
        await self.engine.dispose()

    async def test_save(self):
        id = uuid.uuid4()
        user_name = "test_repo"
        sender = 1
        recipient = 2
        msg = "repo testing"

        greeting = Greeting(id=id, user_name=user_name, sender=sender, recipient=recipient, msg=msg)
        greeting_repo = GreetingRepo()
        await greeting_repo.save(greeting, self.db)
        await self.db.commit()

        stmt = select(GreetingModel).where(GreetingModel.id == id)
        result = await self.db.execute(stmt)
        greeting_model = result.scalar()
        self.assertEqual(greeting_model.id, id)
        self.assertEqual(greeting_model.user_name, user_name)
        self.assertEqual(greeting_model.sender, sender)
        self.assertEqual(greeting_model.recipient, recipient)
        self.assertEqual(greeting_model.msg, msg)
