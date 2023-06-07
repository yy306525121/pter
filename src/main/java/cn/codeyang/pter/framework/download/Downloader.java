package cn.codeyang.pter.framework.download;

/**
 * 下载器接口
 *
 * @author yangzy
 */
public interface Downloader {
    boolean login(String url, String username, String password);
}
