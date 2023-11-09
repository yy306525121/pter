from typing import Optional, List

from pydantic import BaseModel


class Token(BaseModel):
    token: str
    token_type: str
    expire: int
    roles: List[str]
    username: str


class TokenPayload(BaseModel):
    # 用户ID
    sub: Optional[int] = None
