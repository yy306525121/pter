create table t_user
(
    id       int auto_increment
        primary key,
    username varchar(32) not null,
    password varchar(32) not null,
    login_ip varchar(30) null comment '最后登录IP'
) comment '用户表';

create table t_site
(
    id       int auto_increment
        primary key,
    name     varchar(32)   not null comment '站点名称',
    cookie   varchar(200)  not null comment '站点cookie',
    cron     varchar(32)   not null comment 'cron表达式',
    enable   int default 1 not null comment '是否启用 1-启用 0-停用',
    priority int default 0 not null comment '优先级(数字越小优先级越高)'
) comment '站点表';

create table t_client
(
    id                      int auto_increment primary key,
    name                    varchar(32)           not null comment '别名',
    type                    varchar(32)           not null comment '下载器类型 qBittorrent、Transmission',
    client_url              varchar(32)           not null comment '下载器地址',
    username                varchar(32)           not null comment '用户名',
    password                varchar(32)           not null comment '密码',
    enabled                 int         default 1 not null comment '是否启用 1-启用 0-停用',
    status                  int         default 1 not null comment '状态 1-正常 0-异常',
    cron                    varchar(32)           not null default '0/4 * * * * ?' comment '信息更新周期 默认4s更新一次',
    push_notify             int         default 0 not null comment '是否推送通知 1-是 0-否',
    push_monitor            int         default 0 not null comment '是否启用监控频道 1-是 0-否',
    space_alarm             int         default 0 not null comment '是否启用空间警告 1-是 0-否',
    alarm_space             int         default null comment '剩余多少空间的时候开始报警',
    alert_space_unit        int         default 1 comment '剩余空间的单位 1-Kib 2-Mib 3-Gib',
    first_ast_piece_prio    int         default 1 comment '是否先下载首位文件块 1-是 0-否',
    auto_report             int         default 1 comment '是否自动汇报 1-是 0-否 自动在种子添加后的第 5 分钟时汇报一次, 获取更多 Peers',
    max_upload_speed        int         default null comment '上传速度限制，若下载器的上传速度大于此速度时，不再添加种子',
    max_upload_speed_unit   int         default 1 comment '上传速度限制单位 1-Kib 2-Mib 3-Gib',
    max_download_speed      int         default null comment '下载速度限制，若下载器的下载速度大于此速度时，不再添加种子',
    max_download_speed_unit int         default 1 comment '下载速度限制单位 1-Kib 2-Mib 3-Gib',
    min_free_space          int         default null comment '最小剩余空间，弱下载器的剩余空间小于此空间，不再添加种子',
    min_free_space_unit     int         default 1 comment '最小剩余空间单位 1-Kib 2-Mib 3-Gib',
    max_download_num        int         default null comment '最大下载数量 超过此数量，不再添加种子',
    auto_delete             int         default 1 comment '是否自动删种 1-是 0-否',
    auto_delete_cron        varchar(32) default '0 0/1 * * * ?' comment '主动删种cron表达式，默认每分钟一次'
) comment '下载器表';

create table t_config (
                          id int auto_increment primary key ,
                          config_name varchar(100) default '' comment '参数名称',
                          config_key varchar(100) default '' comment '参数键名',
                          config_value varchar(100) default '' comment '参数键值',
                          config_type varchar(100) default 'N' comment '系统内置（Y:是 N:否）',
                          remark varchar(500) default null comment '备注'
) comment '参数配置表';