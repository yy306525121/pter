from sqlalchemy import String, select, Boolean
from sqlalchemy.orm import Mapped, mapped_column, Session

from app.db.models import Base


class User(Base):
    id: Mapped[int] = mapped_column(primary_key=True, index=True)
    username: Mapped[str] = mapped_column(String, unique=True)
    password: Mapped[str] = mapped_column(String)
    is_superuser: Mapped[bool] = mapped_column(Boolean, default=False)

    def __repr__(self) -> str:
        return f'User(id={self.id!r}, username={self.username!r}), is_superuser={str(self.is_superuser)!r}'

    @staticmethod
    def get_by_username(db: Session, username: str):
        return db.query(User).filter(User.username == username).first()
