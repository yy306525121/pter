package cn.codeyang.pter.framework.web.pt.api;

import cn.codeyang.pter.framework.web.hadnler.RetrofitCookieInterceptor;
import com.github.lianjiatech.retrofit.spring.boot.core.RetrofitClient;
import com.github.lianjiatech.retrofit.spring.boot.interceptor.Intercept;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * @author yangzy
 */
@RetrofitClient(baseUrl = "https://hdhome.org")
@Intercept(handler = RetrofitCookieInterceptor.class)
public interface HDHomeApi {

    /**
     * 访问首页
     * @return
     */
    @GET("index.php")
    Call<ResponseBody> index();

}
