from typing import Self, List

from sqlalchemy import Integer, Column, String
from sqlalchemy.orm import Session

from app.db.models import Base


class SystemConfig(Base):
    """配置表"""
    id = Column(Integer, primary_key=True, autoincrement=True)
    key = Column(String ,index=True)
    value = Column(String , nullable=True)

    @staticmethod
    def get_by_key(db: Session, key: str):
        return db.query(SystemConfig).filter(SystemConfig.key == key).first()

    @staticmethod
    def list(db: Session):
        return list(db.query(SystemConfig).all())
