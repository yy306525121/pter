from typing import Optional

from pydantic import BaseModel


class Token(BaseModel):
    token: str
    token_type: str


class TokenPayload(BaseModel):
    # 用户ID
    sub: Optional[int] = None
