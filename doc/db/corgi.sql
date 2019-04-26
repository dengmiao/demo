/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : corgi

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2019-04-26 16:17:54
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_demo
-- ----------------------------
DROP TABLE IF EXISTS `sys_demo`;
CREATE TABLE `sys_demo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `del_flag` int(11) DEFAULT NULL,
  `json` json DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_demo
-- ----------------------------
INSERT INTO `sys_demo` VALUES ('1', '1', '2019-04-26 12:52:27', '1', '2019-04-26 12:52:27', '0', '{\"age\": \"18\", \"name\": \"Bob\"}', 'Bob');
INSERT INTO `sys_demo` VALUES ('2', null, null, null, null, null, '{\"age\": \"20\", \"name\": \"Tom\"}', 'Tom');
INSERT INTO `sys_demo` VALUES ('3', null, null, null, null, null, '{\"age\": \"20\", \"name\": \"Tom\"}', 'Tom');
INSERT INTO `sys_demo` VALUES ('4', null, null, null, null, null, '[{\"age\": \"20\", \"name\": \"Tom\"}, {\"sex\": \"男\", \"name\": \"Tom\"}]', 'Tom');

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除标识位 0正常 1已删除',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `address` varchar(1000) DEFAULT NULL COMMENT '地址',
  `depart_name` varchar(255) DEFAULT NULL COMMENT '机构/部门名称',
  `depart_name_abbr` varchar(100) DEFAULT NULL COMMENT '缩写',
  `depart_name_en` varchar(100) DEFAULT NULL COMMENT '英文名',
  `depart_order` int(11) DEFAULT '0' COMMENT '排序',
  `description` text COMMENT '描述',
  `fax` varchar(255) DEFAULT NULL COMMENT '传真',
  `memo` text COMMENT '备注',
  `mobile` varchar(50) DEFAULT NULL COMMENT '手机号',
  `org_code` varchar(255) DEFAULT NULL COMMENT '机构编码',
  `org_type` varchar(255) DEFAULT NULL COMMENT '机构类型',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父机构ID',
  `parent_ids` varchar(1000) DEFAULT NULL COMMENT '父ID集合',
  `status` int(1) DEFAULT '1' COMMENT '状态（1启用，0不启用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='组织机构';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '1', '2019-02-11 14:21:51', '0', '1', '2019-02-18 23:17:33', null, '沙河娜扎信息技术有限公司', null, null, '0', null, null, null, null, 'A01', '1', null, null, null);
INSERT INTO `sys_dept` VALUES ('2', '1', '2019-02-21 16:14:41', '0', null, null, null, '研发部', null, null, '0', null, null, null, null, 'A01A05', '2', '1', '1,', null);
INSERT INTO `sys_dept` VALUES ('3', '1', '2019-02-21 16:14:35', '0', '1', '2019-02-25 12:49:41', null, '财务部', null, null, '0', null, null, null, null, 'A01A04', '2', '1', '1,', null);
INSERT INTO `sys_dept` VALUES ('4', '1', '2019-02-20 17:15:34', '0', null, null, null, '市场部', null, null, '0', null, null, null, null, 'A01A03', '2', '1', '1,', null);

-- ----------------------------
-- Table structure for sys_dict
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict`;
CREATE TABLE `sys_dict` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除标识位 0正常 1已删除',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `description` text COMMENT '描述',
  `dict_code` varchar(255) DEFAULT NULL COMMENT '字典编码',
  `dict_name` varchar(255) DEFAULT NULL COMMENT '字典名称',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典类型';

-- ----------------------------
-- Records of sys_dict
-- ----------------------------

-- ----------------------------
-- Table structure for sys_dict_item
-- ----------------------------
DROP TABLE IF EXISTS `sys_dict_item`;
CREATE TABLE `sys_dict_item` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `del_flag` int(1) DEFAULT NULL COMMENT '删除标识位 0正常 1已删除',
  `update_by` varchar(255) DEFAULT NULL COMMENT '更新人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `description` varchar(255) DEFAULT NULL COMMENT '描述',
  `dict_id` varchar(255) DEFAULT NULL COMMENT '字典id',
  `item_text` varchar(255) DEFAULT NULL COMMENT '字典项文本',
  `item_value` varchar(255) DEFAULT NULL COMMENT '字典项值',
  `sort_order` decimal(10,2) DEFAULT NULL COMMENT '排序',
  `status` int(1) DEFAULT NULL COMMENT '状态（1启用 0不启用）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='字典分项';

-- ----------------------------
-- Records of sys_dict_item
-- ----------------------------

-- ----------------------------
-- Table structure for sys_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_permission`;
CREATE TABLE `sys_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `always_show` int(3) DEFAULT NULL,
  `component` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `hidden` int(2) DEFAULT NULL,
  `icon` varchar(255) DEFAULT NULL,
  `is_leaf` int(11) DEFAULT NULL,
  `menu_type` int(11) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `perms` varchar(255) DEFAULT NULL,
  `redirect` varchar(255) DEFAULT NULL,
  `sort_no` int(11) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_permission
-- ----------------------------
INSERT INTO `sys_permission` VALUES ('1', null, '2018-12-25 20:34:38', '0', 'admin', '2019-02-22 13:49:30', '0', 'layouts/RouteView', null, '0', 'setting', '0', '0', '系统管理', null, null, null, '1', '/isystem');
INSERT INTO `sys_permission` VALUES ('2', null, '2018-12-25 20:34:38', '0', 'admin', '2019-01-30 15:10:05', '0', 'layouts/RouteView', null, '0', 'dashboard', '0', '0', '系统监控', null, null, null, '2', '/dashboard3');
INSERT INTO `sys_permission` VALUES ('3', null, '2018-12-25 20:34:38', '0', 'admin', '2019-02-19 22:46:09', '0', 'layouts/RouteView', null, '0', 'qrcode', '0', '0', '常见案例', null, null, null, '3', '/jeecg');
INSERT INTO `sys_permission` VALUES ('4', null, '2018-12-25 20:34:38', '0', null, null, null, 'layouts/PageView', null, null, 'form', '0', '0', '表单页', null, null, null, '4', '/form');
INSERT INTO `sys_permission` VALUES ('5', null, '2018-12-25 20:34:38', '0', 'admin', '2019-02-12 14:55:20', '0', 'layouts/PageView', null, '0', 'table', '0', '0', '列表页', null, null, '/list/query-list', '5', '/list');
INSERT INTO `sys_permission` VALUES ('6', null, '2018-12-25 20:34:38', '0', null, null, null, 'layouts/RouteView', null, null, 'profile', '0', '0', '详情页', null, null, null, '6', '/profile');
INSERT INTO `sys_permission` VALUES ('7', null, '2018-12-25 20:34:38', '0', null, null, null, 'layouts/PageView', null, null, 'check-circle-o', '0', '0', '结果页', null, null, null, '7', '/result');
INSERT INTO `sys_permission` VALUES ('8', null, '2018-12-25 20:34:38', '0', null, null, null, 'layouts/RouteView', null, null, 'warning', '0', '0', '异常页', null, null, null, '8', '/exception');
INSERT INTO `sys_permission` VALUES ('9', null, '2018-12-25 20:34:38', '0', null, null, '0', 'layouts/RouteView', null, null, 'user', '0', '0', '个人页', null, null, null, '9', '/account');
INSERT INTO `sys_permission` VALUES ('10', null, '2018-12-25 20:34:38', '0', 'admin', '2019-01-23 11:52:22', '0', 'system/UserList', null, '0', null, '1', '1', '用户管理', '1', null, null, '1', '/isystem/user');
INSERT INTO `sys_permission` VALUES ('11', 'admin', '2019-01-29 18:47:40', '0', 'admin', '2019-01-29 18:51:29', '0', 'system/DepartList', null, '0', null, '1', '1', '部门管理', '1', null, null, '1', '/isystem/depart');
INSERT INTO `sys_permission` VALUES ('12', null, '2018-12-25 20:34:38', '0', null, null, null, 'system/RoleList', null, null, null, '1', '1', '角色管理', '1', null, null, '2', '/isystem/role');
INSERT INTO `sys_permission` VALUES ('13', null, '2018-12-25 20:34:38', '0', null, null, null, 'system/PermissionList', null, null, null, '1', '1', '菜单管理', '1', null, null, '3', '/isystem/permission');
INSERT INTO `sys_permission` VALUES ('14', null, '2018-12-26 10:11:18', '0', 'admin', '2019-02-19 16:38:58', '0', 'system/LogList', null, '0', 'question-circle', '1', '1', '日志管理', '1', null, null, '4', '/isystem/log');
INSERT INTO `sys_permission` VALUES ('15', null, '2019-01-03 09:38:52', '0', 'admin', '2019-01-19 14:08:59', null, 'system/QuartzJobList', null, null, null, '1', '1', '定时任务', '1', null, null, '5', '/isystem/QuartzJobList');
INSERT INTO `sys_permission` VALUES ('16', null, '2018-12-28 13:54:43', '0', null, '2018-12-28 15:37:54', null, 'system/DictList', null, null, null, '1', '1', '数据字典', '1', null, null, '5', '/isystem/dict');
INSERT INTO `sys_permission` VALUES ('17', null, '2019-01-02 17:23:01', '0', null, '2019-01-02 17:31:23', null, 'system/SysAnnouncementList', null, null, '', '1', '1', '系统通告', '1', 'annountCement', null, '6', '/isystem/annountCement');
INSERT INTO `sys_permission` VALUES ('18', 'admin', '2019-01-30 09:43:22', '0', null, null, '0', 'layouts/IframePageView', null, '0', null, '1', '1', 'SQL监控', '2', null, null, '1', 'http://localhost:7777/corgi/druid/');
INSERT INTO `sys_permission` VALUES ('19', null, '2018-12-25 20:34:38', '0', null, null, null, 'dashboard/Monitor', null, null, null, '1', '1', '监控页', '2', null, null, '2', '/dashboard/monitor');
INSERT INTO `sys_permission` VALUES ('20', 'admin', '2019-01-30 10:00:01', '0', null, null, '0', 'layouts/IframePageView', null, '0', null, '1', '1', '在线文档', '2', null, null, '3', 'http://localhost:7777/corgi/swagger-ui.html#/');
INSERT INTO `sys_permission` VALUES ('21', null, '2018-12-25 20:34:38', '0', null, null, null, 'dashboard/Workplace', null, null, null, '1', '1', '工作台', '2', null, null, '3', '/dashboard/workplace');
INSERT INTO `sys_permission` VALUES ('22', null, '2018-12-28 15:57:30', '0', 'admin', '2019-02-15 16:24:37', '0', 'jeecg/JeecgDemoList', null, '0', null, '1', '1', '单表模型示例', '3', null, null, '1', '/jeecg/jeecgDemoList');
INSERT INTO `sys_permission` VALUES ('23', 'admin', '2019-02-20 14:45:09', '0', 'admin', '2019-02-21 16:26:21', '0', 'jeecg/tablist/JeecgOrderDMainList', null, '0', null, '1', '1', '一对多Tab示例', '3', null, null, '2', '/jeecg/tablist/JeecgOrderDMainList');
INSERT INTO `sys_permission` VALUES ('24', 'admin', '2019-02-15 16:24:11', '0', 'admin', '2019-02-18 10:50:14', '0', 'jeecg/JeecgOrderMainList', null, '0', null, '1', '1', '一对多示例', '3', null, null, '2', '/jeecg/JeecgOrderMainList');
INSERT INTO `sys_permission` VALUES ('25', 'admin', '2019-02-19 15:58:48', '0', null, null, '0', 'jeecg/PrintDemoList', null, '0', null, '1', '1', '打印测试', '3', null, null, '3', '/jeecg/PrintDemoList');
INSERT INTO `sys_permission` VALUES ('26', null, '2018-12-25 20:34:38', '0', 'admin', '2019-02-15 16:24:56', '0', 'jeecg/helloworld', null, '0', null, '1', '1', 'helloworld', '3', null, null, '4', '/jeecg/helloworld');
INSERT INTO `sys_permission` VALUES ('27', 'admin', '2019-01-29 19:44:06', '0', 'admin', '2019-02-15 16:25:11', '0', 'layouts/IframePageView', null, '0', null, '1', '1', 'online表单', '3', null, null, '4', 'http://localhost:7777/corgi/auto/cgform/list');
INSERT INTO `sys_permission` VALUES ('28', 'admin', '2019-01-29 19:44:06', '0', 'admin', '2019-02-15 16:25:02', '0', 'layouts/IframePageView', null, '0', null, '1', '1', '百度', '3', null, null, '4', 'http://www.baidu.com');
INSERT INTO `sys_permission` VALUES ('29', 'admin', '2019-02-19 16:02:23', '0', 'admin', '2019-02-21 16:25:45', '0', 'jeecg/FlowTest', null, '0', null, '1', '1', '数据回执模拟', '3', null, null, '6', '/jeecg/FlowTest');
INSERT INTO `sys_permission` VALUES ('30', null, '2018-12-25 20:34:38', '0', null, null, null, 'form/BasicForm', null, null, null, '1', '1', '基础表单', '4', null, null, '1', '/form/base-form');
INSERT INTO `sys_permission` VALUES ('31', null, '2018-12-25 20:34:38', '0', null, null, null, 'form/stepForm/StepForm', null, null, null, '1', '1', '分步表单', '4', null, null, '2', '/form/step-form');
INSERT INTO `sys_permission` VALUES ('32', null, '2018-12-25 20:34:38', '0', null, null, null, 'form/advancedForm/AdvancedForm', null, null, null, '1', '1', '高级表单', '4', null, null, '3', '/form/advanced-form');
INSERT INTO `sys_permission` VALUES ('33', null, '2018-12-25 20:34:38', '0', null, null, null, 'list/TableList', null, null, null, '1', '1', '查询表格', '5', null, null, '1', '/list/query-list');
INSERT INTO `sys_permission` VALUES ('34', null, '2018-12-25 20:34:38', '0', null, null, null, 'list/TableInnerEditList', null, null, null, '1', '1', '内联编辑表格', '5', null, null, '2', '/list/edit-table');
INSERT INTO `sys_permission` VALUES ('35', null, '2018-12-25 20:34:38', '0', null, null, null, 'list/UserList', null, null, null, '1', '1', '用户列表', '5', null, null, '3', '/list/user-list');
INSERT INTO `sys_permission` VALUES ('36', null, '2018-12-25 20:34:38', '0', null, null, null, 'list/RoleList', null, null, null, '1', '1', '角色列表', '5', null, null, '4', '/list/role-list');
INSERT INTO `sys_permission` VALUES ('37', null, '2018-12-25 20:34:38', '0', null, null, null, 'list/PermissionList', null, null, null, '1', '1', '权限列表', '5', null, null, '5', '/list/permission-list');
INSERT INTO `sys_permission` VALUES ('38', null, '2018-12-25 20:34:38', '0', null, null, null, 'list/StandardList', null, null, null, '1', '1', '标准列表', '5', null, null, '6', '/list/basic-list');
INSERT INTO `sys_permission` VALUES ('39', null, '2018-12-25 20:34:38', '0', null, null, null, 'list/CardList', null, null, null, '1', '1', '卡片列表', '5', null, null, '7', '/list/card');
INSERT INTO `sys_permission` VALUES ('40', null, '2018-12-25 20:34:38', '0', 'admin', '2019-02-12 15:09:13', '0', 'list/search/SearchLayout', null, '0', null, '0', '1', '搜索列表', '5', null, '/list/search/article', '8', '/list/search');
INSERT INTO `sys_permission` VALUES ('41', null, '2018-12-25 20:34:38', '0', null, null, null, 'profile/basic/Index', null, null, null, '1', '1', '基础详情页', '6', null, null, '1', '/profile/basic');
INSERT INTO `sys_permission` VALUES ('42', null, '2018-12-25 20:34:38', '0', null, null, null, 'profile/advanced/Advanced', null, null, null, '1', '1', '高级详情页', '6', null, null, '2', '/profile/advanced');
INSERT INTO `sys_permission` VALUES ('43', null, '2018-12-25 20:34:38', '0', null, null, null, 'result/Success', null, null, null, '1', '1', '成功', '7', null, null, '1', '/result/success');
INSERT INTO `sys_permission` VALUES ('44', null, '2018-12-25 20:34:38', '0', null, null, null, 'result/Error', null, null, null, '1', '1', '失败', '7', null, null, '2', '/result/fail');
INSERT INTO `sys_permission` VALUES ('45', null, '2018-12-25 20:34:38', '0', null, null, null, 'exception/403', null, null, null, '1', '1', '403', '8', null, null, '1', '/exception/403');
INSERT INTO `sys_permission` VALUES ('46', null, '2018-12-25 20:34:38', '0', null, null, null, 'exception/404', null, null, null, '1', '1', '404', '8', null, null, '2', '/exception/404');
INSERT INTO `sys_permission` VALUES ('47', null, '2018-12-25 20:34:38', '0', null, null, null, 'exception/500', null, null, null, '1', '1', '500', '8', null, null, '3', '/exception/500');
INSERT INTO `sys_permission` VALUES ('48', null, '2018-12-25 20:34:38', '0', null, null, null, 'account/center/Index', null, null, null, '1', '1', '个人中心', '9', null, null, '1', '/account/center');
INSERT INTO `sys_permission` VALUES ('49', null, '2018-12-25 20:34:38', '0', null, '2018-12-26 19:05:26', '1', 'account/settings/Index', null, null, null, '0', '1', '个人设置', '9', null, null, '2', '/account/settings');
INSERT INTO `sys_permission` VALUES ('50', 'admin', '2019-02-12 14:00:34', '0', 'admin', '2019-02-12 14:17:54', '0', 'list/TableList', null, '0', null, '1', '1', '搜索列表（文章）', '40', null, null, '1', '/list/search/article');
INSERT INTO `sys_permission` VALUES ('51', 'admin', '2019-02-12 14:02:51', '0', 'admin', '2019-02-12 14:14:01', '0', 'list/TableList', null, '0', null, '1', '1', '搜索列表（应用）', '40', null, null, '1', '/list/search/application');
INSERT INTO `sys_permission` VALUES ('52', 'admin', '2019-02-12 14:01:40', '0', 'admin', '2019-02-12 14:14:18', '0', 'list/TableList', null, '0', null, '1', '1', '搜索列表（项目）', '40', null, null, '1', '/list/search/project');
INSERT INTO `sys_permission` VALUES ('53', null, '2018-12-26 18:58:35', '0', null, null, null, 'account/settings/BaseSetting', null, null, null, '1', '1', '基本设置', '49', 'BaseSettings', null, null, '/account/settings/base');
INSERT INTO `sys_permission` VALUES ('54', null, '2018-12-26 19:01:20', '0', null, null, null, 'account/settings/Binding', null, null, null, '1', '1', '账户绑定', '49', 'BindingSettings', null, null, '/account/settings/binding');
INSERT INTO `sys_permission` VALUES ('55', null, '2018-12-26 19:00:46', '0', null, '2018-12-26 21:13:25', null, 'account/settings/Custom', null, null, null, '1', '1', '个性化设置', '49', 'CustomSettings', null, null, '/account/settings/custom');
INSERT INTO `sys_permission` VALUES ('56', null, '2018-12-26 18:59:52', '0', null, null, null, 'account/settings/Security', null, null, null, '1', '1', '安全设置', '49', 'SecuritySettings', null, null, '/account/settings/security');
INSERT INTO `sys_permission` VALUES ('57', null, '2018-12-26 19:02:05', '0', null, null, null, 'account/settings/Notification', null, null, '', '1', '1', '新消息通知', '49', 'NotificationSettings', null, null, '/account/settings/notification');
INSERT INTO `sys_permission` VALUES ('58', null, '2018-12-25 20:34:38', '0', 'admin', '2019-02-22 12:40:02', '0', 'dashboard/Analysis', null, '0', 'bank', '1', '0', '首页', null, null, null, '-1', '/dashboard/analysis');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `role_code` varchar(255) DEFAULT NULL,
  `role_name` varchar(255) DEFAULT NULL,
  `data_scope` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '1', '2019-04-09 13:50:20', '0', '1', '2019-04-09 13:50:22', '这是超级管理员角色', 'admin', '超级管理员', null);

-- ----------------------------
-- Table structure for sys_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_permission`;
CREATE TABLE `sys_role_permission` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `permission_id` varchar(255) DEFAULT NULL,
  `role_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=59 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_permission
-- ----------------------------
INSERT INTO `sys_role_permission` VALUES ('1', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '1', '1');
INSERT INTO `sys_role_permission` VALUES ('2', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '2', '1');
INSERT INTO `sys_role_permission` VALUES ('3', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '3', '1');
INSERT INTO `sys_role_permission` VALUES ('4', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '4', '1');
INSERT INTO `sys_role_permission` VALUES ('5', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '5', '1');
INSERT INTO `sys_role_permission` VALUES ('6', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '6', '1');
INSERT INTO `sys_role_permission` VALUES ('7', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '7', '1');
INSERT INTO `sys_role_permission` VALUES ('8', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '8', '1');
INSERT INTO `sys_role_permission` VALUES ('9', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '9', '1');
INSERT INTO `sys_role_permission` VALUES ('10', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '10', '1');
INSERT INTO `sys_role_permission` VALUES ('11', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '11', '1');
INSERT INTO `sys_role_permission` VALUES ('12', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '12', '1');
INSERT INTO `sys_role_permission` VALUES ('13', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '13', '1');
INSERT INTO `sys_role_permission` VALUES ('14', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '14', '1');
INSERT INTO `sys_role_permission` VALUES ('15', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '15', '1');
INSERT INTO `sys_role_permission` VALUES ('16', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '16', '1');
INSERT INTO `sys_role_permission` VALUES ('17', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '17', '1');
INSERT INTO `sys_role_permission` VALUES ('18', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '18', '1');
INSERT INTO `sys_role_permission` VALUES ('19', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '19', '1');
INSERT INTO `sys_role_permission` VALUES ('20', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '20', '1');
INSERT INTO `sys_role_permission` VALUES ('21', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '21', '1');
INSERT INTO `sys_role_permission` VALUES ('22', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '22', '1');
INSERT INTO `sys_role_permission` VALUES ('23', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '23', '1');
INSERT INTO `sys_role_permission` VALUES ('24', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '24', '1');
INSERT INTO `sys_role_permission` VALUES ('25', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '25', '1');
INSERT INTO `sys_role_permission` VALUES ('26', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '26', '1');
INSERT INTO `sys_role_permission` VALUES ('27', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '27', '1');
INSERT INTO `sys_role_permission` VALUES ('28', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '28', '1');
INSERT INTO `sys_role_permission` VALUES ('29', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '29', '1');
INSERT INTO `sys_role_permission` VALUES ('30', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '30', '1');
INSERT INTO `sys_role_permission` VALUES ('31', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '31', '1');
INSERT INTO `sys_role_permission` VALUES ('32', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '32', '1');
INSERT INTO `sys_role_permission` VALUES ('33', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '33', '1');
INSERT INTO `sys_role_permission` VALUES ('34', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '34', '1');
INSERT INTO `sys_role_permission` VALUES ('35', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '35', '1');
INSERT INTO `sys_role_permission` VALUES ('36', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '36', '1');
INSERT INTO `sys_role_permission` VALUES ('37', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '37', '1');
INSERT INTO `sys_role_permission` VALUES ('38', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '38', '1');
INSERT INTO `sys_role_permission` VALUES ('39', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '39', '1');
INSERT INTO `sys_role_permission` VALUES ('40', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '40', '1');
INSERT INTO `sys_role_permission` VALUES ('41', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '41', '1');
INSERT INTO `sys_role_permission` VALUES ('42', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '42', '1');
INSERT INTO `sys_role_permission` VALUES ('43', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '43', '1');
INSERT INTO `sys_role_permission` VALUES ('44', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '44', '1');
INSERT INTO `sys_role_permission` VALUES ('45', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '45', '1');
INSERT INTO `sys_role_permission` VALUES ('46', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '46', '1');
INSERT INTO `sys_role_permission` VALUES ('47', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '47', '1');
INSERT INTO `sys_role_permission` VALUES ('48', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '48', '1');
INSERT INTO `sys_role_permission` VALUES ('49', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '49', '1');
INSERT INTO `sys_role_permission` VALUES ('50', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '50', '1');
INSERT INTO `sys_role_permission` VALUES ('51', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '51', '1');
INSERT INTO `sys_role_permission` VALUES ('52', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '52', '1');
INSERT INTO `sys_role_permission` VALUES ('53', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '53', '1');
INSERT INTO `sys_role_permission` VALUES ('54', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '54', '1');
INSERT INTO `sys_role_permission` VALUES ('55', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '55', '1');
INSERT INTO `sys_role_permission` VALUES ('56', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '56', '1');
INSERT INTO `sys_role_permission` VALUES ('57', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '57', '1');
INSERT INTO `sys_role_permission` VALUES ('58', '1', '2019-04-10 10:41:39', '0', '1', '2019-04-10 10:41:39', '58', '1');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `birthday` datetime DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `realname` varchar(255) DEFAULT NULL,
  `salt` varchar(255) DEFAULT NULL,
  `sex` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_51bvuyvihefoh4kp5syh2jpi4` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', '1', '2019-04-08 16:32:30', '0', '1', '2019-04-08 16:33:26', '650910883689775.jpg', '2019-04-08 16:33:29', 'admin@admin.admin', '$2a$10$PS04ecXfknNd3V8d.ymLTObQciapMU4xU8.GADBZZsuTZr7ymnagy', '18383111234', '超级管理员', 'RCGTeGiH', '1', '1', 'admin');
INSERT INTO `sys_user` VALUES ('2', '1', '2019-04-17 07:35:03', '0', '1', '2019-04-19 07:53:59', '109951163464139769.jpg', '2019-04-16 16:00:00', '11223@qq.com', '$2a$10$JaJ7zOP7qKP2W..hKAV1re4PJqi.ifqxYvsRat0610k492HRppHye', '18312345678', 'corgi', 'xJtw3pU2', '2', '1', 'corgi');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `create_by` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `del_flag` int(11) DEFAULT NULL,
  `update_by` varchar(255) DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `role_id` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('1', '1', '2019-04-09 13:51:59', '0', '1', '2019-04-09 13:51:56', '1', '1');
