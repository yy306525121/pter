DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user`
(
    `id`         int          NOT NULL AUTO_INCREMENT,
    `nick_name`  varchar(30)       DEFAULT NULL COMMENT '昵称',
    `username`   varchar(32)  NOT NULL,
    `password`   varchar(200) NOT NULL,
    `login_ip`   varchar(30)       DEFAULT NULL COMMENT '最后登录IP',
    `login_date` timestamp    NULL DEFAULT NULL COMMENT '最后登录日期',
    `avatar`     varchar(100)      DEFAULT NULL COMMENT '头像地址',
    version      int               default 1 comment '版本号（用于乐观锁，默认1）',
    create_by    varchar(255) null,
    create_time  date         null,
    update_by    varchar(255) null,
    update_time  date         null,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='用户表';


DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role`
(
    `id`         bigint       NOT NULL AUTO_INCREMENT COMMENT '角色ID',
    `role_name`  varchar(30)  NOT NULL COMMENT '角色名称',
    `role_key`   varchar(100) NOT NULL COMMENT '角色权限字符串',
    `role_sort`  int          NOT NULL COMMENT '显示顺序',
    `data_scope` char(1)      DEFAULT '1' COMMENT '数据范围（1：全部数据权限 2：自定数据权限 3：本部门数据权限 4：本部门及以下数据权限）',
    `status`     char(1)      NOT NULL COMMENT '角色状态（0正常 1停用）',
    `remark`     varchar(500) DEFAULT NULL COMMENT '备注',
    version      int          default 1 comment '版本号（用于乐观锁，默认1）',
    create_by    varchar(255) null,
    create_time  date         null,
    update_by    varchar(255) null,
    update_time  date         null,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 101
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='角色信息表';

DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu`
(
    `id`        int          NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
    `menu_name` varchar(50)  NOT NULL COMMENT '菜单名称',
    `parent_id` bigint       DEFAULT '0' COMMENT '父菜单ID',
    `order_num` int          DEFAULT '0' COMMENT '显示顺序',
    `path`      varchar(200) DEFAULT '' COMMENT '路由地址',
    `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
    `query`     varchar(255) DEFAULT NULL COMMENT '路由参数',
    `is_frame`  int          DEFAULT '1' COMMENT '是否为外链（0是 1否）',
    `is_cache`  int          DEFAULT '0' COMMENT '是否缓存（0缓存 1不缓存）',
    `menu_type` char(1)      DEFAULT '0' COMMENT '菜单类型（M目录 C菜单 F按钮）',
    `visible`   char(1)      DEFAULT '0' COMMENT '菜单状态（0显示 1隐藏）',
    `status`    char(1)      DEFAULT '0' COMMENT '菜单状态（0正常 1停用）',
    `perms`     varchar(100) DEFAULT NULL COMMENT '权限标识',
    `icon`      varchar(100) DEFAULT '#' COMMENT '菜单图标',
    `remark`    varchar(500) DEFAULT '' COMMENT '备注',
    version     int          default 1 comment '版本号（用于乐观锁，默认1）',
    create_by   varchar(255) null,
    create_time date         null,
    update_by   varchar(255) null,
    update_time date         null,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2003
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='菜单权限表';

DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu`
(
    `role_id` bigint NOT NULL COMMENT '角色ID',
    `menu_id` bigint NOT NULL COMMENT '菜单ID',
    PRIMARY KEY (`role_id`, `menu_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='角色和菜单关联表';


DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role`
(
    `user_id` bigint NOT NULL COMMENT '用户ID',
    `role_id` bigint NOT NULL COMMENT '角色ID',
    PRIMARY KEY (`user_id`, `role_id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='用户和角色关联表';


DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config`
(
    `id`           int          NOT NULL AUTO_INCREMENT,
    `config_name`  varchar(100) DEFAULT '' COMMENT '参数名称',
    `config_key`   varchar(100) DEFAULT '' COMMENT '参数键名',
    `config_value` varchar(100) DEFAULT '' COMMENT '参数键值',
    `config_type`  varchar(100) DEFAULT 'N' COMMENT '系统内置（Y:是 N:否）',
    `remark`       varchar(500) DEFAULT NULL COMMENT '备注',
    version        int          default 1 comment '版本号（用于乐观锁，默认1）',
    create_by      varchar(255) null,
    create_time    date         null,
    update_by      varchar(255) null,
    update_time    date         null,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='参数配置表';



DROP TABLE IF EXISTS `t_client`;
CREATE TABLE `t_client`
(
    `id`                      int          NOT NULL AUTO_INCREMENT,
    `name`                    varchar(32)  NOT NULL COMMENT '别名',
    `type`                    varchar(32)  NOT NULL COMMENT '下载器类型 qBittorrent、Transmission',
    `client_url`              varchar(32)  NOT NULL COMMENT '下载器地址',
    `username`                varchar(32)  NOT NULL COMMENT '用户名',
    `password`                varchar(32)  NOT NULL COMMENT '密码',
    `enabled`                 int          NOT NULL DEFAULT '1' COMMENT '是否启用 1-启用 0-停用',
    `status`                  int          NOT NULL DEFAULT '1' COMMENT '状态 1-正常 0-异常',
    `cron`                    varchar(32)  NOT NULL DEFAULT '0/4 * * * * ?' COMMENT '信息更新周期 默认4s更新一次',
    `push_notify`             int          NOT NULL DEFAULT '0' COMMENT '是否推送通知 1-是 0-否',
    `push_monitor`            int          NOT NULL DEFAULT '0' COMMENT '是否启用监控频道 1-是 0-否',
    `space_alarm`             int          NOT NULL DEFAULT '0' COMMENT '是否启用空间警告 1-是 0-否',
    `alarm_space`             int                   DEFAULT NULL COMMENT '剩余多少空间的时候开始报警',
    `alert_space_unit`        int                   DEFAULT '1' COMMENT '剩余空间的单位 1-Kib 2-Mib 3-Gib',
    `first_ast_piece_prio`    int                   DEFAULT '1' COMMENT '是否先下载首位文件块 1-是 0-否',
    `auto_report`             int                   DEFAULT '1' COMMENT '是否自动汇报 1-是 0-否 自动在种子添加后的第 5 分钟时汇报一次, 获取更多 Peers',
    `max_upload_speed`        int                   DEFAULT NULL COMMENT '上传速度限制，若下载器的上传速度大于此速度时，不再添加种子',
    `max_upload_speed_unit`   int                   DEFAULT '1' COMMENT '上传速度限制单位 1-Kib 2-Mib 3-Gib',
    `max_download_speed`      int                   DEFAULT NULL COMMENT '下载速度限制，若下载器的下载速度大于此速度时，不再添加种子',
    `max_download_speed_unit` int                   DEFAULT '1' COMMENT '下载速度限制单位 1-Kib 2-Mib 3-Gib',
    `min_free_space`          int                   DEFAULT NULL COMMENT '最小剩余空间，弱下载器的剩余空间小于此空间，不再添加种子',
    `min_free_space_unit`     int                   DEFAULT '1' COMMENT '最小剩余空间单位 1-Kib 2-Mib 3-Gib',
    `max_download_num`        int                   DEFAULT NULL COMMENT '最大下载数量 超过此数量，不再添加种子',
    `auto_delete`             int                   DEFAULT '1' COMMENT '是否自动删种 1-是 0-否',
    `auto_delete_cron`        varchar(32)           DEFAULT '0 0/1 * * * ?' COMMENT '主动删种cron表达式，默认每分钟一次',
    version                   int                   default 1 comment '版本号（用于乐观锁，默认1）',
    create_by                 varchar(255) null,
    create_time               date         null,
    update_by                 varchar(255) null,
    update_time               date         null,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 2
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci COMMENT ='下载器表';



DROP TABLE IF EXISTS `t_site`;
CREATE TABLE `t_site`
(
    `id`        int          NOT NULL AUTO_INCREMENT,
    `name`      varchar(32)  NOT NULL COMMENT '站点名称',
    `cookie`    varchar(200) NOT NULL COMMENT '站点cookie',
    `cron`      varchar(32)  NOT NULL COMMENT 'cron表达式',
    `enable`    int          NOT NULL DEFAULT '1' COMMENT '是否启用 1-启用 0-停用',
    `priority`  int          NOT NULL DEFAULT '0' COMMENT '优先级(数字越小优先级越高)',
    version     int                   default 1 comment '版本号（用于乐观锁，默认1）',
    create_by   varchar(255) null,
    create_time date         null,
    update_by   varchar(255) null,
    update_time date         null,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;