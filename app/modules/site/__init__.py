import os
from typing import List, Any
from urllib.parse import urljoin

import yaml
from fspy import FlareSolverr
from pydantic import BaseModel
from sqlalchemy.orm import Session

from app.core import settings
from app.db.models.site import Site
from app.db.system_config_oper import SystemConfigOper
from app.helper import FlareSolverrHelper
from app.schemas import SystemConfigKey
from app.utils import Singleton


class SiteConfig(BaseModel):
    id: str
    name: str
    url: str


class SiteConfigLoader(metaclass=Singleton):
    """
    从config/site目录下加载站点配置文件
    将这些配置文件封装成SiteConfig对象并存入List中
    """
    site_yaml_list = {}

    def __init__(self):
        self.site_config_path = settings.CONFIG_PATH / 'site'
        self._load_site_config()

    def _load_site_config(self):
        if not os.path.exists(self.site_config_path):
            print('站点配置目录不存在，请检查配置')
        for filename in os.listdir(self.site_config_path):
            with open(os.path.join(self.site_config_path, filename)) as file:
                data = yaml.load(file, Loader=yaml.FullLoader)
                id = data['id']
                name = data['name']
                site_config = SiteConfig(id=id, name=name)
                self.site_yaml_list[id] = site_config


class SiteInfoLoader:
    """
    从站点加载用户ID、做种数、做种量等数据
    """

    @staticmethod
    def load_info(site_code: str, db: Session):
        site = Site.get_by_code(db, site_code)
        conf = SystemConfigOper().get(key=SystemConfigKey.FlareSolverr)
        site_yaml = SiteConfigLoader.site_yaml_list.get(site_code)
        if not site_yaml:
            print(f'未找到站点{site_code}对应的yaml文件，停止获取站点信息')
            return
        if not conf:
            print(f'系统未配置FlareSolverr配置，系统将使用request库获取站点信息')
        else:
            SiteInfoLoader.load_info_with_solver(conf, site_yaml, site)
        if not site:
            print(f'未找到code为：{site_code}的站点数据，获取信息失败')

    @staticmethod
    def load_info_with_solver(solver_conf: Any, site_config: SiteConfig, site: Site):
        solver = FlareSolverr(host=solver_conf.host, port=solver_conf.port)
        solver.request_get(url=urljoin(site_config.url, '/index.php'))







