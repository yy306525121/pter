-- -------------------------------------------------------------
-- TablePlus 5.3.5(495)
--
-- https://tableplus.com/
--
-- Database: pter
-- Generation Time: 2023-07-14 16:12:19.8880
-- -------------------------------------------------------------


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


INSERT INTO `sys_config` (`id`, `config_name`, `config_key`, `config_value`, `config_type`, `remark`, `version`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
(2, '开启验证码', 'sys.account.captchaEnabled', 'false', 'Y', NULL, 1, NULL, NULL, NULL, NULL);

INSERT INTO `sys_dict_data` (`id`, `dict_sort`, `dict_label`, `dict_value`, `dict_type`, `css_class`, `list_class`, `is_default`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(1, 1, 'HDHome', 'hdhome.org', 'pt_host', NULL, NULL, 'N', '0', '', NULL, '', NULL, NULL);

INSERT INTO `sys_dict_type` (`id`, `dict_name`, `dict_type`, `status`, `create_by`, `create_time`, `update_by`, `update_time`, `remark`) VALUES
(100, 'PT站点HOST信息', 'pt_host', '0', '', NULL, '', NULL, NULL);

INSERT INTO `sys_menu` (`id`, `name`, `path`, `title`, `icon`, `badge`, `target`, `link`, `component`, `render_menu`, `permission`, `parent_id`, `cacheable`, `sort`, `version`, `create_by`, `create_time`, `update_by`, `update_time`, `status`) VALUES
(1, 'workplace', '/workplace', '工作台', 'DashboardOutlined', 'new', '_self', NULL, '@/pages/workplace', 1, NULL, 0, 1, 1, 1, NULL, NULL, NULL, NULL, '0'),
(2, 'table', '/table', '表格', 'TableOutlined', NULL, '_self', NULL, '@/pages/table', 1, NULL, 0, 1, 2, 1, NULL, NULL, NULL, NULL, '0'),
(3, 'personal', '/personal', '个人中心', 'ProfileOutlined', NULL, NULL, NULL, '@/pages/personal', 1, NULL, 0, 1, 3, 1, NULL, NULL, NULL, NULL, '0'),
(4, 'system', '/system', '系统配置', 'SettingOutlined', NULL, '_self', NULL, '@/components/layout/BlankView.vue', 1, NULL, 0, 1, 4, 1, NULL, NULL, NULL, NULL, '0'),
(5, 'bilibili', '/bilibili', 'B站', 'BoldOutlined', 'iframe', '_self', 'https://www.bilibili.com', 'iframe', 1, 'edit', 0, 1, 5, 1, NULL, NULL, NULL, NULL, '0'),
(6, 'github', '/github', 'Github', 'GithubOutlined', 'link', '_blank', 'https://github.com/stepui/stepin-template', 'link', 1, NULL, 0, 1, 1, 1, NULL, NULL, NULL, NULL, '0'),
(401, 'menu', '/system/menu', '菜单管理', NULL, '12', '_self', NULL, '@/pages/system/menu', 1, NULL, 4, 1, 1, 1, NULL, NULL, NULL, NULL, '0'),
(402, 'user', '/system/user', '用户管理', NULL, NULL, '_self', NULL, '@/pages/system/user', 1, NULL, 4, 1, 2, 1, NULL, NULL, NULL, NULL, '0');

INSERT INTO `sys_menu_copy` (`id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `remark`, `version`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
(1, '系统管理', NULL, 255, 'system', NULL, NULL, '1', '0', 'M', '0', '0', NULL, 'system', '', 1, NULL, NULL, NULL, NULL),
(100, '用户管理', 1, 0, 'user', 'system/user/index', NULL, '1', '0', 'C', '0', '0', 'system:user:list', 'user', '', 1, NULL, NULL, NULL, NULL),
(101, '角色管理', 1, 1, 'role', 'system/role/index', NULL, '1', '0', 'C', '0', '0', 'system:role:list', 'peoples', '', 1, NULL, NULL, NULL, NULL);

INSERT INTO `sys_role` (`id`, `role_name`, `role_key`, `role_sort`, `data_scope`, `status`, `remark`, `version`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
(100, '超级管理员', 'admin', 0, '1', '0', NULL, 1, NULL, NULL, NULL, NULL);

INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES
(100, 1),
(100, 100),
(100, 101);

INSERT INTO `sys_user` (`id`, `nick_name`, `username`, `password`, `login_ip`, `login_date`, `avatar`, `version`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
(1, '张三', 'admin', '$2a$10$W1TN7H/K7D/H/f15JoQ6K.LFDGqnMouvBcp17Cwz7QJT7rFkm8N3K', '127.0.0.1', '2023-07-13', '', 1, NULL, NULL, NULL, NULL);

INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES
(1, 100);

INSERT INTO `t_client` (`id`, `name`, `type`, `client_url`, `username`, `password`, `enabled`, `status`, `cron`, `push_notify`, `push_monitor`, `space_alarm`, `alarm_space`, `alert_space_unit`, `first_ast_piece_prio`, `auto_report`, `max_upload_speed`, `max_upload_speed_unit`, `max_download_speed`, `max_download_speed_unit`, `min_free_space`, `min_free_space_unit`, `max_download_num`, `auto_delete`, `auto_delete_cron`, `version`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
(1, 'qBittorent', 'qBittorrent', 'http://127.0.0.1', 'admin', '123', 1, 1, '0/4 * * * * ?', 0, 0, 0, NULL, 1, 1, 1, NULL, 1, NULL, 1, NULL, 1, NULL, 1, '0 0/1 * * * ?', 1, NULL, NULL, NULL, NULL);

INSERT INTO `t_site` (`id`, `name`, `host`, `cookie`, `cron`, `enable`, `priority`, `version`, `create_by`, `create_time`, `update_by`, `update_time`) VALUES
(1, 'HDHome', 'hdhome.org', 'c_secure_uid=MTI0ODIz; c_secure_pass=e1325bfe488d1dbe99f4c8fab240a1a3; c_secure_ssl=bm9wZQ%3D%3D; c_secure_tracker_ssl=bm9wZQ%3D%3D; c_secure_login=bm9wZQ%3D%3D; c_secure_uid=MTI0ODIz; c_secure_pass=e1325bfe488d1dbe99f4c8fab240a1a3; c_secure_ssl=bm9wZQ%3D%3D; c_secure_tracker_ssl=bm9wZQ%3D%3D; c_secure_login=bm9wZQ%3D%3D', '55 11,23 * * *', 1, 1, 1, NULL, NULL, NULL, NULL);



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;