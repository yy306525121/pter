package cn.codeyang.pter.framework.download;

import cn.codeyang.pter.framework.download.impl.QbDownloader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * @author yangzy
 */
class QbDownloaderTest {
    public static final String baseUrl = "http://127.0.0.1:9999";
    Downloader downloader = new QbDownloader();
    private String cookie;

    @BeforeEach
    public void init() {
        cookie = downloader.login(baseUrl, "admin", "123456");
    }


    @Test
    public void testAddTorrent() {
        downloader.addTorrent(baseUrl, cookie, "https://hdhome.org/download.php?id=166547", false, 0, 0, "/Users/yangzy/Download",
                "测试", false, false,false);
    }
}