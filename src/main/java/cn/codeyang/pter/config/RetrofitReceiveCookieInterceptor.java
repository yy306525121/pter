package cn.codeyang.pter.config;

import cn.codeyang.pter.common.core.constant.CommonConstants;
import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author yangzy
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class RetrofitReceiveCookieInterceptor implements Interceptor {
    private final RedisTemplate<String, Object> redisTemplate;

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        Request request = chain.request();
        Response response = chain.proceed(request);
        if (!response.headers("Set-Cookie").isEmpty()) {
            for (String cookie : response.headers("Set-Cookie")) {
                cookie = StrUtil.subBefore(cookie, ';', false);
                log.info("获取到cookie:{}", cookie);
                redisTemplate.opsForValue().set(CommonConstants.DOWNLOADER_TOKEN_KEY + "", cookie);
            }
        }
        return response;
    }
}
