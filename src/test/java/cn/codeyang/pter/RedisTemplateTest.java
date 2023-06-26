package cn.codeyang.pter;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author yangzy
 */
@SpringBootTest
public class RedisTemplateTest {
    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void save() {
        redisTemplate.opsForValue().set("name", "zhangsan");
    }

    @Test
    public void get() {
        Object value = redisTemplate.opsForValue().get("name");
        System.out.println(value.toString());
    }
}
