package cn.codeyang.pter.framework.web.pt.api;

import lombok.SneakyThrows;
import okhttp3.ResponseBody;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import retrofit2.Call;
import retrofit2.Response;

/**
 * @author yangzy
 */
@SpringBootTest
class HDHomeApiTest {
    @Autowired
    private HDHomeApi hdHomeApi;

    @SneakyThrows
    @Test
    public void testIndex() {
        Call<ResponseBody> call = hdHomeApi.index();
        Response<ResponseBody> response = call.execute();
        System.out.println(response);
    }

}