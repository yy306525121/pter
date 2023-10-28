from fastapi_events.handlers.local import local_handler
from fastapi_events.typing import Event

from app.schemas.events import SiteEvents


@local_handler.register(event_name=SiteEvents.UPDATE)
def handler_site_update_event(event: Event):
    event_name, payload = event
    print(payload)
    print(f'触发事件{event_name}')