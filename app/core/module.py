from typing import Optional, Generator

from app.helper import ModuleHelper
from app.utils import Singleton, ObjectUtils


class ModuleManager(metaclass=Singleton):
    """
    模块管理器
    """
    _modules: dict = {}
    # 运行态模块列表
    _running_modules:dict = {}

    def __init__(self):
        self.load_modules()

    def load_modules(self):
        """
        加载所有模块
        """
        # 扫描模块目录
        modules = ModuleHelper.load(
            'app.modules',
            filter_func=lambda _, obj: hasattr(obj, 'init_module') and hasattr(obj, 'init_setting')
        )
        self._running_modules =  {}
        self._modules = {}
        for module in modules:
            module_id = module.__name__
            self._modules[module_id] = module
            # 生成实例
            _module = module()
            # 初始化模块
            if self.check_setting(_module.init_setting()):
                # 通过模块开关控制加载
                _module.init_module()
                self._running_modules[module_id] = _module
                print(f'Module Loaded：{module_id}')

    def stop(self):
        """
        停止所有模块
        """
        for _, module in self._running_modules.items():
            if hasattr(module, 'stop'):
                module.stop()

    @staticmethod
    def check_setting(setting: Optional[tuple]) -> bool:
        """
        检查开关是否已打开，开关使用,分隔多个值，存在就代表开启
        """
        if not setting:
            return True
        switch, value = setting
        if getattr(setting, switch) and value is True:
            return True
        if value in getattr(setting, switch):
            return True
        return False

    def get_modules(self, method: str) -> Generator:
        """
        获取实现了同一方法的模块列表
        :param method: 方法签名
        :return: 实现了method方法的所有模块列表
        """
        if not self._running_modules:
            return []
        for _, module in self._running_modules.items():
            if hasattr(module, method) and ObjectUtils.check_method(getattr(module, method)):
                yield module
