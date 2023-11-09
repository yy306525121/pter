from typing import List

from pydantic import BaseModel


class UserInfo(BaseModel):
    id: int
    username: str
    is_active: bool
    roles: str