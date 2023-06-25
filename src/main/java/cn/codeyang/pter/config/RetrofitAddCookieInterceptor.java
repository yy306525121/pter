package cn.codeyang.pter.config;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;

/**
 * @author yangzy
 */
@Slf4j
public class RetrofitAddCookieInterceptor implements Interceptor {
    //@Override
    //protected Response doIntercept(Chain chain) throws IOException {
    //    Request request = chain.request();
    //    Response response = chain.proceed(request);
    //    return response;
    //}

    @NotNull
    @Override
    public Response intercept(@NotNull Chain chain) throws IOException {
            Request.Builder builder = chain.request().newBuilder();

            //for (String cookie : cookies) {
            //    builder.addHeader("Cookie", cookie);
            //    log.info("OkHttp", "Adding Header: " + cookie);
            //}
            return chain.proceed(builder.build());
    }
}
