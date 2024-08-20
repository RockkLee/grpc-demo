import asyncio
from multiprocessing import Process

import uvicorn
from fastapi import FastAPI

from python_grpc_v2.infra.api.routers import greeting_api
from python_grpc_v2.infra.grpc.server import grpc_server

app = FastAPI()
app.include_router(greeting_api.router)


@app.get("/")
async def root():
    return {"message": "Hello World"}


def __async_grpc_wrapper():
    asyncio.run(grpc_server.start_grpc_server())


if __name__ == "__main__":
    p = Process(target=__async_grpc_wrapper)
    p.start()
    uvicorn.run(app, host="127.0.0.1", port=8000)
    p.join()
