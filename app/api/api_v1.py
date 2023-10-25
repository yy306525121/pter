from fastapi import APIRouter

from app.api.endpoints import test, login, user

api_router = APIRouter()
api_router.include_router(test.router, prefix='/test', tags=['test'])
api_router.include_router(login.router, prefix='/login', tags=['login'])
api_router.include_router(user.router, prefix='/user', tags=['user'])