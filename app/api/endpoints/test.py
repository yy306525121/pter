from typing import Any

from fastapi import APIRouter

from app.core import event_manager
from app.core.event import EventType

router = APIRouter()


@router.get('/hello', summary='测试')
def test() -> Any:
    event_manager.publish_event(EventType.SiteUpdate, {'name': 'zs'})
    return {'message': 'hello'}
