from unittest import TestCase, IsolatedAsyncioTestCase

from python_grpc_v2.app.service.greeting_service import GreetingService
from python_grpc_v2.infra.orm.connection import async_session, engine


class TestGreetingService(IsolatedAsyncioTestCase):
    async def asyncSetUp(self):
        self.db = async_session()
        self.engine = engine
        self.greeting_service = GreetingService()

    async def asyncTearDown(self):
        await self.db.close()
        await self.engine.dispose()

    async def test_greeting_service(self):
        await self.greeting_service.save("test", 2, 1, "test", self.db)

