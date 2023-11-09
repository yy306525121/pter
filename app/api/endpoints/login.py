import json
from datetime import timedelta
from typing import Any

from fastapi import Depends, HTTPException, APIRouter
from fastapi.security import OAuth2PasswordRequestForm
from sqlalchemy.orm import Session

from app import schemas
from app.core import security, settings
from app.db import get_db
from app.db.models.user import User

router = APIRouter()


@router.post("/access-token", summary="获取token", response_model=schemas.Token)
async def login(db: Session = Depends(get_db), form_data: OAuth2PasswordRequestForm = Depends()) -> Any:
    user = User.authenticate(db, username=form_data.username, password=form_data.password)
    if not user:
        raise HTTPException(status_code=401, detail='用户名密码不正确')
    elif not user.is_active:
        raise HTTPException(status_code=403, detail='用户未启用')
    token, expire = security.create_access_token(user.id,
                                                 expires_delta=timedelta(minutes=settings.ACCESS_TOKEN_EXPIRE_MINUTES))
    roles=[]
    if user.roles:
        roles = json.loads(user.roles)

    return schemas.Token(
        token=token,
        expire=expire,
        token_type='bearer',
        roles=roles,
        username=user.username
    )
