from sqlalchemy.ext.asyncio import AsyncSession, create_async_engine, async_sessionmaker

from python_grpc_v2.infra.config.config import ENGINE_FUTURE, ENGINE_ECHO, DATABASE_URI

# Async setting
engine = create_async_engine(
    DATABASE_URI,
    future=ENGINE_FUTURE,  # Allow a seamless shift from prior versions of SQLAlchemy to the new v2.0
    echo=ENGINE_ECHO
)


class MySession(AsyncSession):
    async def commit(self):
        return await super().commit()

    async def rollback(self):
        return await super().rollback()


async_session = async_sessionmaker(
    bind=engine, class_=MySession, expire_on_commit=False
)


async def get_session() -> MySession:
    async with async_session() as session:
        yield session
