from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker, Session

from app.core import settings

# 数据库引擎
engine = create_engine(f'sqlite:///{settings.CONFIG_PATH}/pter.db', echo=True)

# 数据库会话
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)


def get_db():
    """
    获取数据库会话
    :return: Session
    """
    db = None
    try:
        db = SessionLocal()
        yield db
    finally:
        if db:
            db.close()


class DBOper:
    _db: Session = None

    def __init__(self, db: Session = None):
        self._db = db
