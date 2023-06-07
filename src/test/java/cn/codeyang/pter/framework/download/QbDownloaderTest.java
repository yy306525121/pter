package cn.codeyang.pter.framework.download;

import org.junit.jupiter.api.Test;

/**
 * @author yangzy
 */
class QbDownloaderTest {

    @Test
    public void testLogin() {
        Downloader downloader = new QbDownloader();
        boolean login = downloader.login("http://127.0.0.1:9999", "admin", "123456");
    }
}