package cn.codeyang.pter.framework.download;

/**
 * 下载器接口
 *
 * @author yangzy
 */
public interface Downloader {
    /**
     * 登录
     *
     * @param clientUrl 下载器地址
     * @param username  用户名
     * @param password  密码
     * @return 如果登录成功，返回cookie， 否则返回null
     */
    String login(String clientUrl, String username, String password);


    /**
     * 向下载器添加种子文件
     * @param clientUrl 下载器地址
     * @param cookie cookie
     * @param torrentUrl 种子连接
     * @param isSkipChecking 跳过散列检查
     * @param uploadLimit 设置种子上传速度限制（bytes/second）
     * @param downloadLimit 设置种子的下载速度限制（bytes/second）
     * @param savePath 种子保存地址
     * @param category 种子分类
     * @param autoTMM 是否使用自动管理
     * @param firstLastPiecePrio 是否先下载首尾文件块
     * @param paused 在暂停状态下添加种子
     */
    void addTorrent(String clientUrl,
                    String cookie,
                    String torrentUrl,
                    boolean isSkipChecking,
                    int uploadLimit,
                    int downloadLimit,
                    String savePath,
                    String category,
                    boolean autoTMM,
                    boolean firstLastPiecePrio,
                    boolean paused);
}
