import multiprocessing
from contextlib import asynccontextmanager

import uvicorn
from dotenv import load_dotenv
from fastapi import FastAPI
from fastapi_events.handlers.local import local_handler
from fastapi_events.middleware import EventHandlerASGIMiddleware
from uvicorn import Config

from app.core import settings
from app.db.init import init_db
from app.scheduler import Scheduler


def load_env():
    if not load_dotenv(settings.CONFIG_PATH / '.env'):
        print("Could not load .env file or it is empty. Please check if it exists and is readable.")
        exit(1)


@asynccontextmanager
async def lifespan(App: FastAPI):
    startup()
    yield
    shutdown()


App = FastAPI(lifespan=lifespan)
App.add_middleware(EventHandlerASGIMiddleware, handlers=[local_handler])

Server = uvicorn.Server(
    Config(app=App, host=settings.HOST, port=settings.PORT, reload=settings.DEV, workers=multiprocessing.cpu_count()))


def init_routers():
    """
    初始化路由
    """
    from app.api.api_v1 import api_router
    # API路由
    App.include_router(api_router, prefix=settings.API_V1_STR)


def startup():
    load_env()
    init_routers()
    Scheduler()


def shutdown():
    pass


if __name__ == '__main__':
    init_db()
    Server.run()
