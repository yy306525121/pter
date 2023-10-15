from sqlalchemy import String
from sqlalchemy.orm import Mapped, mapped_column, Session

from app.db.models import Base


class Site(Base):
    id: Mapped[int] = mapped_column(primary_key=True, index=True)
    code: Mapped[str] = mapped_column(String)
    cookie: Mapped[str] = mapped_column(String)

    def __repr__(self):
        return f'Site(id={self.id}, code={self.code}, cookie={self.cookie})'

    @staticmethod
    def get_by_code(db: Session, code: str):
        return db.query(Site).filter(Site.code == code).first()

    @staticmethod
    def list(db: Session):
        return db.query(Site).all()
