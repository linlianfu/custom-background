CREATE TABLE `cb_image_parse` (
  `ip_id` varchar(32) NOT NULL COMMENT '地址id',
  `ip_ws_id` varchar(32) DEFAULT NULL COMMENT '解析的网站',
  `ip_parse_url` varchar(255) DEFAULT NULL COMMENT '解析地址',
  `ip_parse_type` tinyint(4) DEFAULT NULL COMMENT '解析类型 | 1 PNG | 2 JPG',
  `ip_create_id` varchar(32) DEFAULT NULL COMMENT '创建人',
  `ip_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ip_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='图片解析地址';
CREATE TABLE `cb_image_parse_auth` (
  `ipa_id` varchar(32) NOT NULL COMMENT '授权id',
  `ipa_ip_id` varchar(32) DEFAULT NULL COMMENT '地址id',
  `ipa_sc_id` varchar(32) DEFAULT NULL COMMENT 'token id',
  `ipa_create_id` varchar(32) DEFAULT NULL COMMENT '创建人',
  `ipa_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ipa_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='解析地址授权';
CREATE TABLE `cb_website` (
  `ws_id` varchar(32) NOT NULL COMMENT '网站id',
  `ws_code` varchar(255) DEFAULT NULL COMMENT '网站标识',
  `ws_address` varchar(255) DEFAULT NULL COMMENT '网站地址',
  `ws_create_id` varchar(32) DEFAULT NULL COMMENT '创建人',
  `ws_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`ws_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='网站管理';

update `cb_security_object` set so_content = 'TD' where so_content = 'tostadora';
update `cb_security_object` set so_content = 'PI' where so_content = 'pinterest';
update `cb_security_object` set so_content = 'TSP' where so_content = 'teeshirtpalace';
update `cb_security_object` set so_content = 'INK' where so_content = 'inkspired';
update `cb_security_object` set so_content = 'AS' where so_content = 'artistshot';
update `cb_security_object` set so_content = 'TP' where so_content = 'teepublic';
update `cb_security_object` set so_content = 'DB' where so_content = 'designbyhumans';
update `cb_security_object` set so_content = 'TL' where so_content = 'threadless';

ALTER TABLE cb_image_parse ADD ip_parse_name varchar(255) comment '解析名称' AFTER ip_id
ALTER TABLE cb_website ADD ws_pixel_length int COMMENT '图片像素长度' AFTER ws_address;
ALTER TABLE cb_website ADD ws_pixel_width int COMMENT '图片像素宽度' AFTER ws_pixel_length;
ALTER TABLE cb_website ADD ws_site_name varchar(255) COMMENT '网站名称' AFTER ws_id;
ALTER TABLE cb_image_parse_auth ADD ipa_type tinyint(4) COMMENT '类型 | 1 预览 | 2 下载' AFTER ipa_sc_id;
update cb_image_parse_auth set ipa_type = 2;

ALTER TABLE cb_image_parse ADD ip_available_range tinyint(4) COMMENT '可用范围 | -1 不限 | 1 内部 | 2 外部 ' AFTER ip_parse_type;

ALTER TABLE cb_secret_key ADD sc_belong tinyint(4) COMMENT '归属 | 1 内部 | 2 外部' AFTER sc_device_number;