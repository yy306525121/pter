from typing import Any

from fastapi import APIRouter
from fastapi_events.dispatcher import dispatch

from app.schemas.events import SiteEvents

router = APIRouter()


@router.get('/hello', summary='测试')
def test() -> Any:
    dispatch(SiteEvents.UPDATE, payload={'site_id': 1, 'cron': 'aaaaa'})
    return {'message': 'hello'}
