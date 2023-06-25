package cn.codeyang.pter.config;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author yangzy
 */
@Slf4j
@Component
public class RetrofitReceiveCookieInterceptor implements Interceptor {

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {

        Request request = chain.request();
        Response response = chain.proceed(request);
        if (!response.headers("Set-Cookie").isEmpty()) {
            for (String cookie : response.headers("Set-Cookie")) {
                log.info("获取cookie: {}", cookie);
            }
        }
        return response;
    }
}
