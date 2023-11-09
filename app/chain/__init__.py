import gc
import pickle
from abc import ABCMeta
from typing import Any

from sqlalchemy.orm import Session

from app.core import ModuleManager, settings


class ChainBase(metaclass=ABCMeta):
    """
    处理链基类
    """

    def __init__(self, db: Session = None):
        self._db = db
        self.module_manager = ModuleManager()

    @staticmethod
    def load_cache(filename: str) -> Any:
        """
        从本地加载缓存
        """
        cache_path = settings.TEMP_PATH / filename
        if cache_path.exists():
            try:
                with open(cache_path, 'rb') as f:
                    return pickle.load(f)
            except Exception as err:
                print(f'加载缓存{cache_path}出错：{err}')
        return None

    @staticmethod
    def save_cache(cache: Any, filename: str) -> None:
        """
        保存缓存到本地
        """
        try:
            with open(settings.TEMP_PATH / filename, 'wb') as f:
                pickle.dump(cache, f)
        except Exception as err:
            print(f'保存缓存{filename}出错：{err}')
        finally:
            del cache
            gc.collect()

    def run_module(self, method: str, *args, **kwargs) -> Any:
        """
        运行包含该方法的所有模块，然后返回结果
        """
        def is_result_empty(ret):
            if isinstance(ret, tuple):
                return all(value is None for value in ret)
            else:
                return ret is None

        print(f'请求模块执行：{method}...')
        result = None
        modules = self.module_manager.get_modules
