package cn.codeyang.pter.module.pter.entity;

import cn.codeyang.pter.common.core.domain.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;

/**
 * 下载器表(Client)表实体类
 *
 * @author yangzy
 * @since 2023-06-07 15:01:53
 */
@Data
@Entity
@Table(name = "t_client")
public class Client extends BaseEntity {
    /**
     * 别名
     */
    private String name;
    /**
     * 下载器类型 qBittorrent、Transmission
     */
    private String type;
    /**
     * 下载器地址
     */
    private String clientUrl;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 是否启用 1-启用 0-停用
     */
    private Integer enabled;
    /**
     * 状态 1-正常 0-异常
     */
    private Integer status;
    /**
     * 信息更新周期 默认4s更新一次
     */
    private String cron;
    /**
     * 是否推送通知 1-是 0-否
     */
    private Integer pushNotify;
    /**
     * 是否启用监控频道 1-是 0-否
     */
    private Integer pushMonitor;
    /**
     * 是否启用空间警告 1-是 0-否
     */
    private Integer spaceAlarm;
    /**
     * 剩余多少空间的时候开始报警
     */
    private Integer alarmSpace;
    /**
     * 剩余空间的单位 1-Kib 2-Mib 3-Gib
     */
    private Integer alertSpaceUnit;
    /**
     * 是否先下载首位文件块 1-是 0-否
     */
    private Integer firstAstPiecePrio;
    /**
     * 是否自动汇报 1-是 0-否 自动在种子添加后的第 5 分钟时汇报一次, 获取更多 Peers
     */
    private Integer autoReport;
    /**
     * 上传速度限制，若下载器的上传速度大于此速度时，不再添加种子
     */
    private Integer maxUploadSpeed;
    /**
     * 上传速度限制单位 1-Kib 2-Mib 3-Gib
     */
    private Integer maxUploadSpeedUnit;
    /**
     * 下载速度限制，若下载器的下载速度大于此速度时，不再添加种子
     */
    private Integer maxDownloadSpeed;
    /**
     * 下载速度限制单位 1-Kib 2-Mib 3-Gib
     */
    private Integer maxDownloadSpeedUnit;
    /**
     * 最小剩余空间，弱下载器的剩余空间小于此空间，不再添加种子
     */
    private Integer minFreeSpace;
    /**
     * 最小剩余空间单位 1-Kib 2-Mib 3-Gib
     */
    private Integer minFreeSpaceUnit;
    /**
     * 最大下载数量 超过此数量，不再添加种子
     */
    private Integer maxDownloadNum;
    /**
     * 是否自动删种 1-是 0-否
     */
    private Integer autoDelete;
    /**
     * 主动删种cron表达式，默认每分钟一次
     */
    private String autoDeleteCron;

}

