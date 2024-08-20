import os

if os.name == "nt":
    DATABASE_URI = "sqlite+aiosqlite:///C://Users//Public//python-grpc-v2.db"
else:
    DATABASE_URI = "sqlite+aiosqlite:////sql_app.db"
ENGINE_FUTURE = True
ENGINE_ECHO = True

GRPC_SERVER_GOLANG = "localhost:50051"
GRPC_SERVER_PYTHON = "localhost:50052"
GRPC_SERVER_JAVA = "localhost:50053"
GRPC_VERBOSITY = "debug"
GRPC_TRACE=""