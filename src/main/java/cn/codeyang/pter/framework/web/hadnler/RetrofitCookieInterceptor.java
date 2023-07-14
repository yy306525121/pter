package cn.codeyang.pter.framework.web.hadnler;

import cn.hutool.core.util.URLUtil;
import com.github.lianjiatech.retrofit.spring.boot.interceptor.BasePathMatchInterceptor;
import lombok.RequiredArgsConstructor;
import okhttp3.HttpUrl;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @author yangzy
 */
@Component
@RequiredArgsConstructor
public class RetrofitCookieInterceptor extends BasePathMatchInterceptor {
    private final RedisTemplate<String, Object> redisTemplate;

    /**
     * 该字典下存了所有的PT站点的HOST列表
     */
    public static final String PT_HOST_DICT_TYPE = "pt_host";


    @Override
    protected Response doIntercept(Chain chain) throws IOException {
        Request request = chain.request();
        HttpUrl url = request.url();
        String host = url.host();
        // TODO 添加cookie


        Request newRequest = request.newBuilder().build();
        return chain.proceed(newRequest);
    }
}
