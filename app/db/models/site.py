from sqlalchemy import String, Column, Integer, Boolean, Float, DateTime
from sqlalchemy.orm import Mapped, mapped_column, Session

from app.db.models import Base


class Site(Base):
    id = Column(Integer, primary_key=True, autoincrement=True)
    code = Column(String, nullable=False)
    cookie = Column(String, comment='站点cookie', default='')
    cron = Column(String, comment='更新周期, 默认每天的11:55和23:55各更新一次', default='55 11,23 * * *')
    enabled = Column(Boolean, comment='是否启用', default=True)
    priority = Column(Integer, comment='优先级,数值越小优先级越高', default=0)
    username = Column(String, comment='用户名', default='')
    uid = Column(String, comment='站点用户UID', default='')
    upload = Column(Float, comment='站点上传量', default=0)
    download = Column(Float, comment='站点下载量', default=0)
    seeding = Column(Integer, comment='做种数', default=0)
    leeching = Column(Integer, comment='下载数', default=0)
    seeding_size = Column(Float, comment='做种体积', default=0)
    update_time = Column(DateTime, comment='上次刷新时间', nullable=True, default=None)

    def __repr__(self):
        return f'Site(id={self.id}, code={self.code}, cron={self.cron})'

    @staticmethod
    def get_by_code(db: Session, code: str):
        return db.query(Site).filter(Site.code == code).first()

    @staticmethod
    def list(db: Session):
        return db.query(Site).all()
