-- -------------------------------------------------------------
-- TablePlus 5.3.5(495)
--
-- https://tableplus.com/
--
-- Database: pter
-- Generation Time: 2023-07-14 16:11:52.9420
-- -------------------------------------------------------------


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


DROP TABLE IF EXISTS `sys_config`;
CREATE TABLE `sys_config` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `config_name` varchar(255) DEFAULT NULL,
  `config_key` varchar(255) DEFAULT NULL,
  `config_value` varchar(255) DEFAULT NULL,
  `config_type` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `version` int DEFAULT '1' COMMENT '版本号（用于乐观锁，默认1）',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='参数配置表';

DROP TABLE IF EXISTS `sys_dict_data`;
CREATE TABLE `sys_dict_data` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '字典编码',
  `dict_sort` int DEFAULT '0' COMMENT '字典排序',
  `dict_label` varchar(100) DEFAULT '' COMMENT '字典标签',
  `dict_value` varchar(100) DEFAULT '' COMMENT '字典键值',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `css_class` varchar(100) DEFAULT NULL COMMENT '样式属性（其他样式扩展）',
  `list_class` varchar(100) DEFAULT NULL COMMENT '表格回显样式',
  `is_default` char(1) DEFAULT 'N' COMMENT '是否默认（Y是 N否）',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典数据表';

DROP TABLE IF EXISTS `sys_dict_type`;
CREATE TABLE `sys_dict_type` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '字典主键',
  `dict_name` varchar(100) DEFAULT '' COMMENT '字典名称',
  `dict_type` varchar(100) DEFAULT '' COMMENT '字典类型',
  `status` char(1) DEFAULT '0' COMMENT '状态（0正常 1停用）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE KEY `dict_type` (`dict_type`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='字典类型表';

DROP TABLE IF EXISTS `sys_menu`;
CREATE TABLE `sys_menu` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '名称',
  `path` varchar(32) NOT NULL COMMENT '路径',
  `title` varchar(32) NOT NULL,
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `badge` varchar(32) DEFAULT NULL COMMENT 'MenuConfig.meta.badge 菜单徽标',
  `target` varchar(32) DEFAULT NULL COMMENT '_self|_blank',
  `link` varchar(200) DEFAULT NULL,
  `component` varchar(200) NOT NULL,
  `render_menu` tinyint NOT NULL DEFAULT '1' COMMENT 'MenuConfig.meta.visible 是否渲染菜单，默认为 true',
  `permission` varchar(32) DEFAULT NULL COMMENT '菜单权限',
  `parent_id` bigint NOT NULL DEFAULT '0',
  `cacheable` tinyint NOT NULL DEFAULT '1' COMMENT '是否开启缓存',
  `sort` int NOT NULL DEFAULT '1' COMMENT '排序',
  `version` int NOT NULL DEFAULT '1' COMMENT '版本号（用于乐观锁，默认1）',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=404 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `sys_menu_copy`;
CREATE TABLE `sys_menu_copy` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(255) DEFAULT NULL,
  `parent_id` bigint DEFAULT '0' COMMENT '父菜单ID',
  `order_num` int DEFAULT '0' COMMENT '显示顺序',
  `path` varchar(255) DEFAULT NULL,
  `component` varchar(255) DEFAULT NULL COMMENT '组件路径',
  `query` varchar(255) DEFAULT NULL COMMENT '路由参数',
  `is_frame` varchar(255) DEFAULT NULL,
  `is_cache` varchar(255) DEFAULT NULL,
  `menu_type` varchar(255) DEFAULT NULL,
  `visible` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `perms` varchar(255) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `version` int DEFAULT '1' COMMENT '版本号（用于乐观锁，默认1）',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2jrf4gb0gjqi8882gxytpxnhe` (`parent_id`),
  CONSTRAINT `FK2jrf4gb0gjqi8882gxytpxnhe` FOREIGN KEY (`parent_id`) REFERENCES `sys_menu_copy` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2003 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='菜单权限表';

DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '角色ID',
  `role_name` varchar(255) DEFAULT NULL,
  `role_key` varchar(255) DEFAULT NULL,
  `role_sort` int NOT NULL COMMENT '显示顺序',
  `data_scope` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `version` int DEFAULT '1' COMMENT '版本号（用于乐观锁，默认1）',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色信息表';

DROP TABLE IF EXISTS `sys_role_menu`;
CREATE TABLE `sys_role_menu` (
  `role_id` bigint NOT NULL COMMENT '角色ID',
  `menu_id` bigint NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`),
  KEY `FKf3mud4qoc7ayew8nml4plkevo` (`menu_id`),
  CONSTRAINT `FKf3mud4qoc7ayew8nml4plkevo` FOREIGN KEY (`menu_id`) REFERENCES `sys_menu_copy` (`id`),
  CONSTRAINT `FKkeitxsgxwayackgqllio4ohn5` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='角色和菜单关联表';

DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `nick_name` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `login_ip` varchar(255) DEFAULT NULL,
  `login_date` date DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `version` int DEFAULT '1' COMMENT '版本号（用于乐观锁，默认1）',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户表';

DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `user_id` bigint NOT NULL COMMENT '用户ID',
  `role_id` bigint NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKhh52n8vd4ny9ff4x9fb8v65qx` (`role_id`),
  CONSTRAINT `FKb40xxfch70f5qnyfw8yme1n1s` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `FKhh52n8vd4ny9ff4x9fb8v65qx` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户和角色关联表';

DROP TABLE IF EXISTS `t_client`;
CREATE TABLE `t_client` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `client_url` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `enabled` int NOT NULL DEFAULT '1' COMMENT '是否启用 1-启用 0-停用',
  `status` int NOT NULL DEFAULT '1' COMMENT '状态 1-正常 0-异常',
  `cron` varchar(255) DEFAULT NULL,
  `push_notify` int NOT NULL DEFAULT '0' COMMENT '是否推送通知 1-是 0-否',
  `push_monitor` int NOT NULL DEFAULT '0' COMMENT '是否启用监控频道 1-是 0-否',
  `space_alarm` int NOT NULL DEFAULT '0' COMMENT '是否启用空间警告 1-是 0-否',
  `alarm_space` int DEFAULT NULL COMMENT '剩余多少空间的时候开始报警',
  `alert_space_unit` int DEFAULT '1' COMMENT '剩余空间的单位 1-Kib 2-Mib 3-Gib',
  `first_ast_piece_prio` int DEFAULT '1' COMMENT '是否先下载首位文件块 1-是 0-否',
  `auto_report` int DEFAULT '1' COMMENT '是否自动汇报 1-是 0-否 自动在种子添加后的第 5 分钟时汇报一次, 获取更多 Peers',
  `max_upload_speed` int DEFAULT NULL COMMENT '上传速度限制，若下载器的上传速度大于此速度时，不再添加种子',
  `max_upload_speed_unit` int DEFAULT '1' COMMENT '上传速度限制单位 1-Kib 2-Mib 3-Gib',
  `max_download_speed` int DEFAULT NULL COMMENT '下载速度限制，若下载器的下载速度大于此速度时，不再添加种子',
  `max_download_speed_unit` int DEFAULT '1' COMMENT '下载速度限制单位 1-Kib 2-Mib 3-Gib',
  `min_free_space` int DEFAULT NULL COMMENT '最小剩余空间，弱下载器的剩余空间小于此空间，不再添加种子',
  `min_free_space_unit` int DEFAULT '1' COMMENT '最小剩余空间单位 1-Kib 2-Mib 3-Gib',
  `max_download_num` int DEFAULT NULL COMMENT '最大下载数量 超过此数量，不再添加种子',
  `auto_delete` int DEFAULT '1' COMMENT '是否自动删种 1-是 0-否',
  `auto_delete_cron` varchar(255) DEFAULT NULL,
  `version` int DEFAULT '1' COMMENT '版本号（用于乐观锁，默认1）',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='下载器表';

DROP TABLE IF EXISTS `t_site`;
CREATE TABLE `t_site` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(32) NOT NULL COMMENT '站点名称',
  `host` varchar(200) NOT NULL COMMENT '站点HOST',
  `cookie` mediumtext NOT NULL COMMENT '站点cookie',
  `cron` varchar(32) NOT NULL COMMENT 'cron表达式',
  `enable` tinyint NOT NULL DEFAULT '1' COMMENT '是否启用 1-启用 0-停用',
  `priority` int NOT NULL DEFAULT '0' COMMENT '优先级(数字越小优先级越高)',
  `version` int DEFAULT '1' COMMENT '版本号（用于乐观锁，默认1）',
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` date DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` date DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;