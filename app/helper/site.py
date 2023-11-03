import os.path
from typing import List

import yaml
from pydantic import BaseModel

from app.core import settings
from app.utils import Singleton


class SiteConfig(BaseModel):
    id: str
    name: str


class SiteHelper(metaclass=Singleton):
    site_config_list: List[SiteConfig] = []

    def __init__(self):
        self.site_config_path = settings.CONFIG_PATH / 'site'
        self._load_site_config()

    def _load_site_config(self):
        if not os.path.exists(self.site_config_path):
            print('站点配置目录不存在，请检查配置')
        for filename in os.listdir(self.site_config_path):
            with open(os.path.join(self.site_config_path, filename)) as file:
                data = yaml.load(file, Loader=yaml.FullLoader)
                site_config = SiteConfig(id=data['id'], name=data['name'])
                self.site_config_list.append(site_config)

