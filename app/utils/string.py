import re


class StringUtils:

    @staticmethod
    def camel_to_snake(text):
        """
        驼峰转下划线
        :param text:
        :return:
        """
        result = re.sub('([A-Z])', r'_\1', text).lower()
        # 如果原字符串以大写字母开头，则添加下划线前缀并转换为小写
        if result.startswith('_'):
            result = result[1:]
        return result


if __name__ == '__main__':
    camel_case_str = "MyCamelCaseString"
    snake_case_str = StringUtils.camel_to_snake(camel_case_str)
    print(snake_case_str)  # 输出 "my_camel_case_string"
