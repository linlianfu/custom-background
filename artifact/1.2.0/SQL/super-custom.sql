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

ALTER TABLE cb_secret_key ADD sc_last_login_time datetime COMMENT '最后登录时间' AFTER sc_create_time;

drop table if exists cb_character_replace_rule;

/*==============================================================*/
/* Table: cb_character_replace_rule                             */
/*==============================================================*/
create table cb_character_replace_rule
(
   crr_id               varchar(32) not null comment '规则id',
   crr_rule_name        varchar(255) comment '规则名称',
   crr_ws_id            varchar(32) comment '网站id',
   crr_enable           tinyint comment '是否可用',
   crr_create_id        varchar(32) comment '创建人',
   crr_create_time      datetime comment '创建时间',
   primary key (crr_id)
);

alter table cb_character_replace_rule comment '字符替换规则';
drop table if exists cb_character_replace_rule_item;

/*==============================================================*/
/* Table: cb_character_replace_rule_item                        */
/*==============================================================*/
create table cb_character_replace_rule_item
(
   crri_id              varchar(32) not null comment '子规则id',
   crri_crr_id          varchar(32) comment '规则id',
   crri_origin_character varchar(255) comment '原字符',
   crri_new_character   varchar(255) comment '新字符',
   crri_create_id       varchar(32) comment '创建人',
   crri_create_time     datetime comment '创建时间',
   primary key (crri_id)
);

alter table cb_character_replace_rule_item comment '字符替换子规则';
drop table if exists cb_user_download_record;

/*==============================================================*/
/* Table: cb_user_download_record                               */
/*==============================================================*/
create table cb_user_download_record
(
   udr_id               varchar(32) not null comment '下载记录id',
   udr_token_id         varchar(32) comment 'token id',
   udr_ws_id            varchar(32) comment '网站id',
   udr_image_count      int comment '下载数量',
   udr_keyword          varchar(255) comment '搜索关键字',
   udr_search_url       varchar(255) comment '搜索url',
   udr_create_time      datetime comment '创建时间',
   primary key (udr_id)
);

alter table cb_user_download_record comment '用户下载记录';

drop table if exists cb_image_data_info;

/*==============================================================*/
/* Table: cb_image_data_info                                    */
/*==============================================================*/
create table cb_image_data_info
(
   idi_id               varchar(32) not null comment 'id',
   idi_udr_id           varchar(32) comment '下载记录id',
   idi_image_id         varchar(100) comment '图片id',
   idi_image_title      varchar(255) comment '图片标题',
   idi_image_url        varchar(255) comment '图片链接',
   idi_product_url      varchar(255) comment '产品链接',
   idi_token_id         varchar(32) comment 'token id',
   idi_create_time      datetime comment '创建时间',
   primary key (idi_id)
);

alter table cb_image_data_info comment '图片数据信息';

ALTER TABLE cb_security_object ADD so_token_id  varchar(32) COMMENT 'tokenId' AFTER so_token;
ALTER TABLE cb_image_data_info ADD idi_ws_id  varchar(32) COMMENT '网站id' AFTER idi_udr_id;

drop table if exists cb_product_category;

/*==============================================================*/
/* Table: cb_product_category                                   */
/*==============================================================*/
create table cb_product_category
(
   pc_category_id       varchar(32) comment '分类id',
   pc_ws_id             varchar(32) comment '所属网站',
   pc_category_code     varchar(255) comment '分类标识',
   pc_category_name     varchar(255) comment '分类名称',
   pc_enable            tinyint comment '是否可用',
   pc_create_id         varchar(32) comment '创建人',
   pc_create_time       datetime comment '创建时间'
);

alter table cb_product_category comment '网站产品分类 - 类目';



