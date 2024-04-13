CREATE TABLE `cb_store` (
  `st_id` varchar(32) NOT NULL COMMENT '店铺 id',
  `st_store_name` varchar(255) DEFAULT NULL COMMENT '店铺名称',
  `st_legal_person` char(100) DEFAULT NULL COMMENT '法人',
  `st_business_license` varchar(255) DEFAULT NULL COMMENT '营业执照',
  `st_registration_time` datetime DEFAULT NULL COMMENT '注册时间',
  `st_tort_fraction` int(11) DEFAULT NULL COMMENT '侵权扣分',
  `st_serious_trot_count` tinyint(4) DEFAULT NULL COMMENT '严重侵权次数',
  `st_status` tinyint(4) DEFAULT NULL COMMENT '店铺状态 | 1：运营  2、退出运营',
  `st_shop_id` varchar(32) DEFAULT NULL COMMENT '商户 id',
  `st_exit_time` date DEFAULT NULL COMMENT '退出时间',
  `st_platform_type` tinyint(4) DEFAULT NULL COMMENT '所属平台',
  `st_create_time` datetime NOT NULL COMMENT '创建时间',
  `st_create_id` varchar(32) NOT NULL COMMENT '创建人',
  PRIMARY KEY (`st_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺管理';

CREATE TABLE `cb_store_theme` (
  `id` varchar(32) NOT NULL COMMENT 'id',
  `sth_st_id` varchar(32) DEFAULT NULL COMMENT '店铺id',
  `sth_th_id` varchar(32) DEFAULT NULL COMMENT '主题id',
  `sth_up_time` datetime DEFAULT NULL COMMENT '上架时间',
  `sth_product_count` tinyint(4) DEFAULT NULL COMMENT '上架产品数量',
  `sth_tort` tinyint(1) DEFAULT NULL COMMENT '是否侵权',
  `sth_tort_type` tinyint(4) DEFAULT NULL COMMENT '侵权类型 | 1: 知产平台治理-一般侵权 2:知识产权-一般侵权 3:资金冻结 4:严重侵权',
  `sth_tort_time` datetime DEFAULT NULL COMMENT '侵权时间',
  `sth_intellectual_property_name` varchar(255) DEFAULT NULL COMMENT '知识产权名称',
  `sth_remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `sth_create_id` varchar(32) DEFAULT NULL,
  `sth_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='店铺主题管理';

CREATE TABLE `cb_theme` (
  `th_id` varchar(32) NOT NULL COMMENT '主题id',
  `th_name` varchar(255) DEFAULT NULL COMMENT '主题名称',
  `th_theme` varchar(255) DEFAULT NULL COMMENT '关键词',
  `th_label` varchar(255) DEFAULT NULL COMMENT '标签，次关键词',
  `th_category_id` varchar(32) DEFAULT NULL COMMENT '主题分类',
  `th_risk_type` tinyint(4) DEFAULT NULL COMMENT '风险等级 | 1、常规主题  2、一般侵权 3、资金冻结 4、严重侵权',
  `th_flow` tinyint(4) DEFAULT NULL COMMENT '流量等级 | 1、普通 2、爆款',
  `th_remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `th_create_id` varchar(32) DEFAULT NULL COMMENT '创建人',
  `th_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`th_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='主题管理';


-- 03.28 调整字段类型
ALTER TABLE sys_user MODIFY user_id varchar(32);
ALTER TABLE sys_role MODIFY role_id varchar(32);
ALTER TABLE sys_users_roles MODIFY user_id varchar(32);
ALTER TABLE sys_users_roles MODIFY role_id varchar(32);
ALTER TABLE sys_users_jobs MODIFY user_id varchar(32);
ALTER TABLE sys_roles_menus MODIFY role_id varchar(32);

update sys_user set user_id = '2d0e5908df3811e8a4ec2afb6e36c22c' where user_id = 1;
update sys_role set role_id = '53af3b61e02711e8a4ec2afb6e36c22c' where role_id = 1;
update sys_users_roles SET user_id = '2d0e5908df3811e8a4ec2afb6e36c22c',role_id = '53af3b61e02711e8a4ec2afb6e36c22c'WHERE user_id = 1 AND role_id = 1;
update sys_users_jobs set user_id = '2d0e5908df3811e8a4ec2afb6e36c22c'  WHERE user_id= 1;
update sys_roles_menus set role_id = '53af3b61e02711e8a4ec2afb6e36c22c' where role_id = 1;

-- 新增菜单初始化
INSERT INTO `sys_menu`(`menu_id`, `pid`, `sub_count`, `type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `cache`, `hidden`, `permission`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES (117, NULL, 1, 0, '图库管理', NULL, NULL, 1, 'theme', 'gallery', b'0', b'0', b'0', NULL, 'admin', 'admin', '2024-03-03 13:16:30', '2024-03-03 13:16:30');
INSERT INTO `sys_menu`(`menu_id`, `pid`, `sub_count`, `type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `cache`, `hidden`, `permission`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES (119, NULL, 2, 0, '店铺管理', NULL, NULL, 3, 'shopping', 'store', b'0', b'0', b'0', NULL, 'admin', 'admin', '2024-03-15 17:55:21', '2024-03-15 17:55:21');
INSERT INTO `sys_menu`(`menu_id`, `pid`, `sub_count`, `type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `cache`, `hidden`, `permission`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES (118, 117, 0, 1, '主题管理', 'Theme', 'gallery/theme/index', 2, 'list', 'theme', b'0', b'0', b'0', NULL, 'admin', 'admin', '2024-03-03 13:32:22', '2024-03-03 13:32:22');
INSERT INTO `sys_menu`(`menu_id`, `pid`, `sub_count`, `type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `cache`, `hidden`, `permission`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES (120, 119, 0, 1, '我的店铺', 'StoreManager', 'store/storeManager/index', 1, 'index', 'storeManager', b'0', b'0', b'0', NULL, 'admin', 'admin', '2024-03-15 17:59:42', '2024-03-15 17:59:42');
INSERT INTO `sys_menu`(`menu_id`, `pid`, `sub_count`, `type`, `title`, `name`, `component`, `menu_sort`, `icon`, `path`, `i_frame`, `cache`, `hidden`, `permission`, `create_by`, `update_by`, `create_time`, `update_time`)
VALUES (121, 119, 0, 1, '店铺主题', 'StoreTheme', 'store/storeTheme', 999, 'link', 'store-theme', b'0', b'0', b'0', NULL, 'admin', 'admin', '2024-03-16 16:41:16', '2024-03-16 16:41:16');
INSERT INTO `sys_roles_menus`(`menu_id`, `role_id`) VALUES (117, '53af3b61e02711e8a4ec2afb6e36c22c');
INSERT INTO `sys_roles_menus`(`menu_id`, `role_id`) VALUES (118, '53af3b61e02711e8a4ec2afb6e36c22c');
INSERT INTO `sys_roles_menus`(`menu_id`, `role_id`) VALUES (119, '53af3b61e02711e8a4ec2afb6e36c22c');
INSERT INTO `sys_roles_menus`(`menu_id`, `role_id`) VALUES (120, '53af3b61e02711e8a4ec2afb6e36c22c');
INSERT INTO `sys_roles_menus`(`menu_id`, `role_id`) VALUES (121, '53af3b61e02711e8a4ec2afb6e36c22c');

