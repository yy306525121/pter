import os
from pathlib import Path

from pydantic.v1 import BaseSettings


class Settings(BaseSettings):

    HOST: str = '0.0.0.0'
    PORT: int = 3000
    # 是否开发模式
    DEV: bool = os.environ.get('DEV', False)

    SUPERUSER: str = 'admin'
    SUPERUSER_PASSWORD: str = 'admin'
    API_V1_STR: str = '/api/v1'

    @property
    def ROOT_PATH(self):
        return Path(__file__).parents[2]

    @property
    def CONFIG_PATH(self):
        return self.ROOT_PATH / 'config'


settings = Settings()
