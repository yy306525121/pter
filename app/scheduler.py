from apscheduler.executors.pool import ThreadPoolExecutor
from apscheduler.schedulers.background import BackgroundScheduler

from app.core import settings
from app.db import get_db
from app.utils import Singleton


class Scheduler(metaclass=Singleton):
    # 定时服务
    _scheduler = BackgroundScheduler(timezone=settings.TZ,
                                     executors={
                                         'default': ThreadPoolExecutor(20)
                                     })

    def __init__(self):
        self._scheduler.start()
