from typing import Any

from sqlalchemy.orm import DeclarativeBase, declared_attr, Session
from typing_extensions import Self

from app.utils import StringUtils


class Base(DeclarativeBase):
    id: Any
    __name__: str

    def create(self, db):
        db.add(self)
        db.commit()
        return self

    @classmethod
    def get(cls, db: Session, id: int) -> Self:
        return db.query(cls).filter(cls.id == id).first()

    @declared_attr
    def __tablename__(self) -> str:
        return StringUtils.camel_to_snake(self.__name__)
