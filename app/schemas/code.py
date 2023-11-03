from pydantic import BaseModel


class Code(BaseModel):
    code: str
    name: str