import multiprocessing

import uvicorn
from fastapi import FastAPI
from uvicorn import Config

from app.core import settings
from app.db.init import init_db

App = FastAPI()

Server = uvicorn.Server(
    Config(app=App, host=settings.HOST, port=settings.PORT, reload=settings.DEV, workers=multiprocessing.cpu_count()))

if __name__ == '__main__':
    init_db()
    Server.run()
