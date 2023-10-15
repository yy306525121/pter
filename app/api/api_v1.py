from fastapi import APIRouter

from app.api.endpoints import test

api_router = APIRouter()
api_router.include_router(test.router, prefix='/test', tags=['test'])