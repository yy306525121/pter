from apscheduler.executors.pool import ThreadPoolExecutor
from apscheduler.schedulers.background import BackgroundScheduler

from app import schemas
from app.core import settings
from app.db import get_db
from app.db.models.site import Site
from app.modules.site import SiteInfoLoader
from app.utils import Singleton


class Scheduler(metaclass=Singleton):
    # 定时服务
    _scheduler = BackgroundScheduler(timezone=settings.TZ,
                                     executors={
                                         'default': ThreadPoolExecutor(20)
                                     })

    def __init__(self):
        db = get_db()
        site_list = Site.list(db)
        for site in site_list:
            scheduler_id = schemas.SchedulerKey.SiteInfoUpdate + site.code
            self._scheduler.add_job(id=scheduler_id, func=SiteInfoLoader.load_info(site, db), )

        self._scheduler.start()
