package cn.codeyang.pter.framework.download;

import cn.codeyang.pter.common.core.constant.QbConstants;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpGlobalConfig;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangzy
 */
public class QbDownloader implements Downloader {
    private String cookie;

    @Override
    public boolean login(String url, String username, String password) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        HttpResponse response = HttpRequest.post(url + QbConstants.LOGIN_URI)
                .form(params)
                .timeout(HttpGlobalConfig.getTimeout())
                .execute();
        if (response.getStatus() == 200 && response.body().equals("Ok.")) {
            cookie = response.header("set-cookie");
            cookie = StrUtil.subBefore(cookie, )
        }
        System.out.println(response);
        return false;
    }
}
