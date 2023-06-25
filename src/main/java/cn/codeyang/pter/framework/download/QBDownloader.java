package cn.codeyang.pter.framework.download;

import cn.codeyang.pter.common.core.constant.QbConstants;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * @author yangzy
 */
public interface QBDownloader {

    /**
     * 登录
     *
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @FormUrlEncoded
    @POST(QbConstants.LOGIN_URI)
    Call<ResponseBody> login(@Field("username") String username, @Field("password") String password);

    /**
     * 添加下载
     *
     * @param torrentUrl         种子连接
     * @param isSkipChecking     跳过散列检查
     * @param uploadLimit        设置种子上传速度限制（bytes/second）
     * @param downloadLimit      设置种子的下载速度限制（bytes/second）
     * @param savePath           种子保存地址
     * @param category           种子分类
     * @param autoTMM            是否使用自动管理
     * @param firstLastPiecePrio 是否先下载首尾文件块
     * @param paused             在暂停状态下添加种子
     * @return
     */
    @FormUrlEncoded
    @POST(QbConstants.ADD_TORRENT)
    Call<ResponseBody> addTorrent(String torrentUrl,
                                  boolean isSkipChecking,
                                  int uploadLimit,
                                  int downloadLimit,
                                  String savePath,
                                  String category,
                                  boolean autoTMM,
                                  boolean firstLastPiecePrio,
                                  boolean paused);


}
