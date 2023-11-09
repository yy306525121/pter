import importlib
from pathlib import Path

from app.core import settings
from app.core.security import get_password_hash
from app.db import engine, SessionLocal
from app.db.models import Base
from app.db.models.user import User


def init_db():
    # 导入模块，避免建表缺失
    for module in Path(__file__).with_name("models").glob("*.py"):
        importlib.import_module(f"app.db.models.{module.stem}")
    Base.metadata.create_all(bind=engine)
    # 初始化超级管理员
    _db = SessionLocal()
    user = User.get_by_username(db=_db, username=settings.SUPERUSER)
    if not user:
        user = User(
            username=settings.SUPERUSER,
            password=get_password_hash(settings.SUPERUSER_PASSWORD),
            is_active=True,
            roles='["admin"]'
        )
        user.create(_db)
