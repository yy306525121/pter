package cn.codeyang.pter.framework.download.impl;

import cn.codeyang.pter.common.core.constant.QbConstants;
import cn.codeyang.pter.framework.download.Downloader;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;

import java.util.HashMap;
import java.util.Map;

/**
 * @author yangzy
 */
@Slf4j
public class QbDownloader implements Downloader {

    @Override
    public String login(String clientUrl, String username, String password) {
        Map<String, Object> params = new HashMap<>();
        params.put("username", username);
        params.put("password", password);
        try {
            HttpResponse response = HttpRequest.post(clientUrl + QbConstants.LOGIN_URI)
                    .form(params)
                    .timeout(HttpGlobalConfig.getTimeout())
                    .execute();
            if (response.getStatus() == 200 && response.body().equals("Ok.")) {
                String cookie = response.header("set-cookie");
                cookie = StrUtil.subBefore(cookie, ';', false);
                return cookie;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void addTorrent(@NonNull String clientUrl, @NonNull String cookie, @NonNull String torrentUrl, boolean isSkipChecking, int uploadLimit, int downloadLimit, String savePath, String category, boolean autoTMM, boolean firstLastPiecePrio, boolean paused) {
        Map<String, Object> params = new HashMap<>();
        params.put("urls", clientUrl);
        params.put("cookie", cookie);
        params.put("skip_checking", isSkipChecking);
        params.put("upLimit", uploadLimit);
        params.put("dlLimit", downloadLimit);
        params.put("firstLastPiecePrio", firstLastPiecePrio);
        params.put("paused", paused);
        params.put("autoTMM", autoTMM);

        if (StrUtil.isNotEmpty(savePath)) {
            params.put("savePath", savePath);
        }
        if (StrUtil.isNotEmpty(category)) {
            params.put("category", category);
        }


        try {
            HttpResponse response = HttpRequest.post(clientUrl + QbConstants.ADD_TORRENT)
                    .form(params)
                    .header("cookie", cookie)
                    .timeout(HttpGlobalConfig.getTimeout())
                    .execute();
            if (response.getStatus() == 200) {
                System.out.println("OK");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
