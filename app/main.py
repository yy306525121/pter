import multiprocessing

import uvicorn
from fastapi import FastAPI
from uvicorn import Config

from app.core import settings
from app.core.event import EventListener
from app.db.init import init_db

App = FastAPI()

Server = uvicorn.Server(
    Config(app=App, host=settings.HOST, port=settings.PORT, reload=settings.DEV, workers=multiprocessing.cpu_count()))


def init_routers():
    """
    初始化路由
    """
    from app.api.api_v1 import api_router
    # API路由
    App.include_router(api_router, prefix=settings.API_V1_STR)


@App.on_event('startup')
def start_module():
    init_routers()


if __name__ == '__main__':
    init_db()
    Server.run()
