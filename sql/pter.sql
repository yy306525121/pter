



INSERT INTO `sys_menu` (`id`, `menu_name`, `parent_id`, `order_num`, `path`, `component`, `query`, `is_frame`, `is_cache`, `menu_type`, `visible`, `status`, `perms`, `icon`, `remark`) VALUES
(1, '系统管理', 0, 255, 'system', NULL, NULL, 1, 0, 'M', '0', '0', NULL, 'system', ''),
(100, '用户管理', 1, 0, 'user', 'system/user/index', NULL, 1, 0, 'C', '0', '0', 'system:user:list', 'user', ''),
(101, '角色管理', 1, 1, 'role', 'system/role/index', NULL, 1, 0, 'C', '0', '0', 'system:role:list', 'peoples', '');

INSERT INTO `sys_role` (`id`, `role_name`, `role_key`, `role_sort`, `data_scope`, `status`, `remark`) VALUES
(100, '超级管理员', 'admin', 0, '1', '0', NULL);

INSERT INTO `sys_role_menu` (`role_id`, `menu_id`) VALUES
(100, 1),
(100, 100),
(100, 101);

INSERT INTO `sys_user` (`id`, `nick_name`, `username`, `password`, `login_ip`, `login_date`, `avatar`) VALUES
(1, '张三', 'admin', '$2a$10$W1TN7H/K7D/H/f15JoQ6K.LFDGqnMouvBcp17Cwz7QJT7rFkm8N3K', '127.0.0.1', '2023-06-30 00:00:00', '');

INSERT INTO `sys_user_role` (`user_id`, `role_id`) VALUES
(1, 100);

INSERT INTO `t_client` (`id`, `name`, `type`, `client_url`, `username`, `password`, `enabled`, `status`, `cron`, `push_notify`, `push_monitor`, `space_alarm`, `alarm_space`, `alert_space_unit`, `first_ast_piece_prio`, `auto_report`, `max_upload_speed`, `max_upload_speed_unit`, `max_download_speed`, `max_download_speed_unit`, `min_free_space`, `min_free_space_unit`, `max_download_num`, `auto_delete`, `auto_delete_cron`) VALUES
(1, 'qBittorent', 'qBittorrent', 'http://127.0.0.1', 'admin', '123', 1, 1, '0/4 * * * * ?', 0, 0, 0, NULL, 1, 1, 1, NULL, 1, NULL, 1, NULL, 1, NULL, 1, '0 0/1 * * * ?');
