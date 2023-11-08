import os.path
from typing import Any

from fastapi import APIRouter, Depends
from fastapi_events.dispatcher import dispatch
from sqlalchemy.orm import Session

from app import schemas
from app.core import settings
from app.core.security import verify_token
from app.db import get_db
from app.db.models.site import Site
from app.modules.site import SiteConfigLoader
from app.schemas import SiteEvents

router = APIRouter()


@router.get(path='/codes', summary='支持的站点列表', response_model=schemas.Response)
async def codes() -> Any:
    config_list = SiteConfigLoader.site_config_list
    codes_list = [{'code': item.id, 'name': item.name} for item in config_list]
    return schemas.Response(success=True, data=codes_list)



@router.post(path='/add', summary='新增站点', response_model=schemas.Response)
async def add_site(*, db: Session = Depends(get_db), site_req: schemas.Site, _: schemas.TokenPayload = Depends(verify_token)) -> Any:
    """
    新增站点
    """
    if not site_req.code:
        return schemas.Response(successs=False, message='站点不能为空')
    if not site_req.cookie:
        return schemas.Response(successs=False, message='cookie不能为空')
    if Site.get_by_code(db, site_req.code):
        return schemas.Response(success=False, message='站点已存在')

    # 设置默认值
    site = Site(**site_req.model_dump())
    site.create(db)
    dispatch(SiteEvents.ADD, payload={'id': site.id})
    return schemas.Response(success=True)
