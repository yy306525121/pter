import importlib
import pkgutil


class ModuleHelper:
    """
    模块动态加载
    """

    @classmethod
    def load(cls, package_path, filter_func=lambda name, obj: True):
        """
        导入子模块
        :param package_path: 父包名
        :param filter_func: 子模块过滤函数，入参为模块名和模块对象，返回True则导入，否则不导入
        :return:
        """
        sub_modules: list = []
        packages = importlib.import_module(package_path)
        for importer, package_name, _ in pkgutil.iter_modules(packages.__package__):
            if package_name.startswith('_'):
                continue
            full_package_name = f'{package_path}.{package_name}'
            module = importlib.import_module(full_package_name)
            for name, obj in module.__dict__.items():
                if name.startswith('_'):
                    continue
                if isinstance(obj, type) and filter_func(name, obj):
                    sub_modules.append(obj)
        return sub_modules
