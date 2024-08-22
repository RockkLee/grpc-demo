from unittest import IsolatedAsyncioTestCase, mock
from unittest.mock import AsyncMock

from python_grpc_v2.app.service.greeting_service import GreetingService
from python_grpc_v2.infra.orm.connection import async_session, engine


class TestGreetingService(IsolatedAsyncioTestCase):
    async def asyncSetUp(self):
        self.db = async_session()
        self.engine = engine

    async def asyncTearDown(self):
        await self.db.close()
        await self.engine.dispose()

    async def test_save(self):
        greeting_service = GreetingService()
        await greeting_service.save("test", 2, 1, "test", self.db)
