from typing import Optional

from pydantic import BaseModel


class Site(BaseModel):
    id: Optional[int] = None
    code: Optional[str]
    cookie: Optional[str]
    cron: Optional[str] = None
    enabled: Optional[bool] = None
    priority: Optional[int] = None