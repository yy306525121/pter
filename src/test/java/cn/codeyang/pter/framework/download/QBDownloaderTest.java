package cn.codeyang.pter.framework.download;

import cn.codeyang.pter.config.RetrofitReceiveCookieInterceptor;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;

/**
 * @author yangzy
 */
@SpringBootTest
public class QBDownloaderTest {

    @Test
    public void login() throws IOException {
        OkHttpClient.Builder client = new OkHttpClient.Builder()
                .addInterceptor(new RetrofitReceiveCookieInterceptor());


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://127.0.0.1:9999")
                .client(client.build())
                .build();

        QBDownloader qbDownloader = retrofit.create(QBDownloader.class);
        Call<ResponseBody> call = qbDownloader.login("admin", "1234567");
        Response<ResponseBody> result = null;
        try {
            result = call.execute();
            if (result.isSuccessful()) {
                System.out.println(result.body().string());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}
