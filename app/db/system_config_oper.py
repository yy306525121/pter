import json
from typing import Any

from app.db import DBOper
from app.db.models.system_config import SystemConfig
from app.schemas import SystemConfigKey
from app.utils import Singleton
from app.utils.object import ObjectUtils


class SystemConfigOper(DBOper, metaclass=Singleton):
    __SYSTEM_CONF: dict = {}

    def __init__(self):
        """
        加载配置到内存
        """
        super().__init__()
        for item in SystemConfig.list(self._db):
            if ObjectUtils.is_obj(item.value):
                self.__SYSTEM_CONF[item.key] = json.loads(item.value)
            else:
                self.__SYSTEM_CONF[item.key] = item.value

    def set(self, key: str | SystemConfigKey, value: Any):
        """
        设置系统配置
        """
        if isinstance(key, SystemConfigKey):
            key = key.value
        # 更新内存
        self.__SYSTEM_CONF[key] = value
        # 写入数据库
        if ObjectUtils.is_obj(value):
            value = json.dumps(value)
        elif value is None:
            value = ''
        conf = SystemConfig.get_by_key(self._db, key)
        if conf:
            if value:
                conf.update(self._db, {'value': value})
            else:
                conf.delete(self._db, conf.id)
        else:
            conf = SystemConfig(key=key, value=value)
            conf.create(self._db)

    def get(self, key: str | SystemConfigKey = None) -> Any:
        """
        获取系统配置
        """
        if isinstance(key, SystemConfigKey):
            key = key.value
        if not key:
            return self.__SYSTEM_CONF
        return self.__SYSTEM_CONF.get(key)

    def __del__(self):
        if self._db:
            self._db.close()
