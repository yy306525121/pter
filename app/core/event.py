from abc import ABCMeta, abstractmethod, ABC
from enum import Enum
from queue import Queue, Empty
from threading import Thread

from app.utils import Singleton


class EventType(Enum):
    SiteUpdate = 'site:update'


class EventObject(object):
    def __init__(self, event_type: EventType, data: dict = None):
        self.event_type = event_type
        self.data = data


class EventListener(metaclass=ABCMeta):
    @abstractmethod
    def on_application_event(self, event: EventObject):
        pass


class EventManager(object):
    """
    事件管理器
    """

    def __init__(self):
        # 事件对象列表
        self._event_queue = Queue()
        self._listener = {}
        self._thread = Thread(target=self.__run)
        self._thread.start()

    def publish_event(self, event_type: EventType, data: dict = None):
        if event_type not in EventType:
            return
        event = EventObject(event_type, data=data)
        self._event_queue.put(event)

    def add_event_listener(self, event_type: EventType, handler):
        try:
            handler_list = self._listener[event_type.value]
        except KeyError:
            handler_list = []
            self._listener[event_type.value] = handler_list
        if handler not in handler_list:
            handler_list.append(handler)
        print(self._listener)

    def register(self, event_type: [EventType, list]):
        """
        事件监听器注册
        """

        def decorator(f):
            self.add_event_listener(event_type, f)
            print('aaaa')
            return f

        return decorator

    def __run(self):
        """引擎与你行"""
        while True:
            try:
                # 获取事件的阻塞时间为1秒，如果1s内队列中有元素，则取出，否则1s后报Empty异常
                event = self._event_queue.get(block=True, timeout=1)
                self.__event_process(event)
            except Empty:
                print(f'队列是空的')
                pass

    def __event_process(self, event: EventObject):
        # 检查是否存在对该事件进行监听的函数
        if event.event_type.value in self._listener:
            for handler in self._listener[event.event_type.value]:
                # TODO 这里的调用方式需要处理
                handler(event=event)


event_manager = EventManager()
