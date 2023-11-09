from typing import Any

from fastapi import APIRouter, Depends

from app import schemas
from app.db.models.user import User
from app.db.userauth import get_current_active_user

router = APIRouter()


@router.get('/current', summary='获取当前登录用户信息', response_model=schemas.UserInfo)
async def get_current_user(current_user: User = Depends(get_current_active_user)) -> Any:
    return current_user
