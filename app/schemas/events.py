from enum import Enum

from fastapi_events.registry.payload_schema import registry as payload_schema
from pydantic import BaseModel


class SiteEvents(Enum):
    ADD = 'site:add'
    UPDATE = 'site:update'


@payload_schema.register(event_name=SiteEvents.UPDATE)
class SiteUpdatePayload(BaseModel):
    site_id: int
    cron: str