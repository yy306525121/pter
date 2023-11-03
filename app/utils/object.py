from typing import Any


class ObjectUtils:
    @staticmethod
    def is_obj(obj: Any):
        if isinstance(obj, list) or isinstance(obj, dict) or isinstance(obj, tuple):
            return True
        elif isinstance(obj, int) or isinstance(obj, float) or isinstance(obj, bool) or isinstance(obj, bytes):
            return False
        else:
            return str(obj).startswith('{') or str(obj).startswith('[')
