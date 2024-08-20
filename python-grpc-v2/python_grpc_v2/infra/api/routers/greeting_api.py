from fastapi import APIRouter, Depends
from starlette import status

from python_grpc_v2.app.service.greeting_service import GreetingService
from python_grpc_v2.infra.api.schemas.msg import MsgResp
from python_grpc_v2.infra.orm.connection import get_session, MySession

router = APIRouter(
    prefix="/greeting",
    tags=['greeting_api']
)


@router.get("/greet/", response_model=MsgResp, status_code=status.HTTP_201_CREATED)
async def greet(user_name: str, sender: int, recipient: int, msg: str,
                db: MySession = Depends(get_session)):
    user_service = GreetingService()

    resp_msg = await user_service.send_grpc(user_name, sender, recipient, msg, db)
    return MsgResp(message=resp_msg)
