from sqlalchemy import create_engine
from sqlalchemy.orm import sessionmaker

from app.core import settings

# 数据库引擎
engine = create_engine(f'sqlite:///{settings.CONFIG_PATH}/pter.db', echo=True)

# 数据库会话
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
