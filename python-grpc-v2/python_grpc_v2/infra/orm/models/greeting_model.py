from python_grpc_v2.infra.orm.models.base import Base
from sqlalchemy import Column, String, Integer, Uuid


class GreetingModel(Base):
    __tablename__ = "greeting"

    id = Column(Uuid, primary_key=True)
    user_name = Column(String(255))
    sender = Column(Integer)
    recipient = Column(Integer)
    msg = Column(String(255))