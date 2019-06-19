DROP DATABASE IF EXISTS `test`;
CREATE DATABASE `test` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_bin;
SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for sys_dept
-- ----------------------------
DROP TABLE IF EXISTS `sys_dept`;
CREATE TABLE `sys_dept` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(40) COLLATE utf8mb4_bin NOT NULL COMMENT '部门名称',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父ID',
  `status` smallint(6) NOT NULL COMMENT '状态 1、禁用 0、正常',
  `sort` int(11) NOT NULL COMMENT '排序',
  `operator` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '操作人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='部门表';

-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '新鲜超媒体公司', '0', '0', '1', 'admin', '2019-05-28 09:17:31');
INSERT INTO `sys_dept` VALUES ('2', '科技部', '1', '0', '1', '管理员', '2019-05-28 09:17:59');
INSERT INTO `sys_dept` VALUES ('3', '财务部', '1', '0', '2', '管理员', '2019-05-28 09:18:29');
INSERT INTO `sys_dept` VALUES ('4', '市场部', '1', '0', '3', '管理员', '2019-05-28 09:18:55');
INSERT INTO `sys_dept` VALUES ('5', '运营部', '1', '0', '4', 'admin', '2019-05-28 09:46:40');

-- ----------------------------
-- Table structure for sys_org
-- ----------------------------
DROP TABLE IF EXISTS `sys_org`;
CREATE TABLE `sys_org` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键 ID',
  `parent_id` bigint(20) NOT NULL COMMENT '父ID',
  `name` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '名称',
  `sort` int(11) DEFAULT NULL COMMENT '排序',
  `status` smallint(6) NOT NULL COMMENT '状态 0、启用 1、禁用',
  `remark` varchar(255) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '备注',
  `operator` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '操作人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='组织机构表';

-- ----------------------------
-- Records of sys_org
-- ----------------------------

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `name` varchar(40) COLLATE utf8mb4_bin NOT NULL COMMENT '菜单名称',
  `permission` varchar(30) COLLATE utf8mb4_bin NOT NULL COMMENT '权限标识',
  `path` varchar(150) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'URL',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父ID',
  `icon` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '图标',
  `type` smallint(6) NOT NULL COMMENT '类型 0、菜单 1、按钮',
  `status` smallint(6) NOT NULL COMMENT '状态 1、禁用 0、正常',
  `sort` int(11) NOT NULL COMMENT '排序',
  `operator` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '操作人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `component` varchar(60) COLLATE utf8mb4_bin DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='菜单资源表';

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('5', '系统管理', 'system', 'system', '0', null, '0', '1', '2', 'admin', '2019-05-14 15:59:19', 'PageView');
INSERT INTO `sys_resource` VALUES ('6', '用户管理', 'userList', 'userList', '5', null, '0', '0', '1', '管理员', '2019-05-14 16:00:55', 'userList');
INSERT INTO `sys_resource` VALUES ('7', '角色管理', 'roleList', 'roleList', '5', null, '0', '0', '2', '管理员', '2019-05-14 16:01:34', 'roleList');
INSERT INTO `sys_resource` VALUES ('8', '权限管理', 'permissionList', 'permissionList', '5', null, '0', '0', '3', '管理员', '2019-05-14 16:02:12', 'permissionList');
INSERT INTO `sys_resource` VALUES ('9', '用户新增', 'user-add', null, '6', null, '1', '0', '1', '管理员', '2019-05-15 14:08:18', null);
INSERT INTO `sys_resource` VALUES ('10', '用户修改', 'user-update', null, '6', null, '1', '0', '1', '管理员', '2019-05-15 14:09:10', null);
INSERT INTO `sys_resource` VALUES ('11', '用户删除', 'user-delete', null, '6', null, '1', '0', '1', '管理员', '2019-05-15 14:10:04', null);
INSERT INTO `sys_resource` VALUES ('12', '用户查询', 'user-query', null, '6', null, '1', '0', '1', '管理员', '2019-05-15 14:10:42', null);
INSERT INTO `sys_resource` VALUES ('13', '角色新增', 'role-add', null, '7', null, '1', '0', '1', '管理员', '2019-05-15 14:08:18', null);
INSERT INTO `sys_resource` VALUES ('14', '角色修改', 'role-update', null, '7', null, '1', '0', '1', '管理员', '2019-05-15 14:08:18', null);
INSERT INTO `sys_resource` VALUES ('15', '角色删除', 'role-delete', null, '7', null, '1', '0', '1', '管理员', '2019-05-15 14:08:18', null);
INSERT INTO `sys_resource` VALUES ('16', '角色查询', 'role-query', null, '7', null, '1', '0', '1', '管理员', '2019-05-15 14:08:18', null);
INSERT INTO `sys_resource` VALUES ('17', '权限新增', 'permission-add', null, '8', null, '1', '0', '1', '管理员', '2019-05-15 14:08:18', null);
INSERT INTO `sys_resource` VALUES ('18', '权限修改', 'permission-update', null, '8', null, '1', '0', '1', '管理员', '2019-05-15 14:08:18', null);
INSERT INTO `sys_resource` VALUES ('19', '权限删除', 'permission-delete', null, '8', null, '1', '0', '1', '管理员', '2019-05-15 14:08:18', null);
INSERT INTO `sys_resource` VALUES ('20', '权限查询', 'permission-query', null, '8', null, '1', '0', '1', '管理员', '2019-05-15 14:08:18', null);
INSERT INTO `sys_resource` VALUES ('23', '部门管理', 'deptList', 'deptList', '5', null, '0', '0', '4', 'admin', '2019-05-27 13:08:35', 'deptList');
INSERT INTO `sys_resource` VALUES ('24', '新增部门', 'dept-add', null, '23', null, '1', '0', '1', 'admin', '2019-05-28 09:47:47', null);
INSERT INTO `sys_resource` VALUES ('25', '修改部门', 'dept-update', null, '23', null, '1', '1', '2', 'admin', '2019-05-28 09:48:12', null);
INSERT INTO `sys_resource` VALUES ('26', '删除部门', 'dept-delete', null, '23', null, '1', '0', '3', 'admin', '2019-05-28 09:48:51', null);
INSERT INTO `sys_resource` VALUES ('27', '查询部门', 'dept-query', null, '23', null, '1', '0', '4', 'admin', '2019-05-28 09:49:16', null);

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `role_name` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '角色名',
  `role_code` varchar(40) COLLATE utf8mb4_bin NOT NULL COMMENT '角色编码',
  `role_remark` varchar(100) COLLATE utf8mb4_bin NOT NULL COMMENT '角色备注',
  `status` smallint(6) NOT NULL COMMENT '状态 1、禁用 0、正常',
  `operator` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '操作人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色表';

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', 'admin', '就是管理员', '0', 'admin', '2019-05-14 14:01:12');
INSERT INTO `sys_role` VALUES ('2', 'jeesd', 'jeesd', '测试角色了', '0', 'admin', '2019-05-26 17:33:25');

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `resource_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色资源表';

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES ('83', '6', '1');
INSERT INTO `sys_role_resource` VALUES ('84', '9', '1');
INSERT INTO `sys_role_resource` VALUES ('85', '10', '1');
INSERT INTO `sys_role_resource` VALUES ('86', '11', '1');
INSERT INTO `sys_role_resource` VALUES ('87', '12', '1');
INSERT INTO `sys_role_resource` VALUES ('88', '7', '1');
INSERT INTO `sys_role_resource` VALUES ('89', '13', '1');
INSERT INTO `sys_role_resource` VALUES ('90', '14', '1');
INSERT INTO `sys_role_resource` VALUES ('91', '15', '1');
INSERT INTO `sys_role_resource` VALUES ('92', '16', '1');
INSERT INTO `sys_role_resource` VALUES ('93', '8', '1');
INSERT INTO `sys_role_resource` VALUES ('94', '17', '1');
INSERT INTO `sys_role_resource` VALUES ('95', '18', '1');
INSERT INTO `sys_role_resource` VALUES ('96', '19', '1');
INSERT INTO `sys_role_resource` VALUES ('97', '20', '1');
INSERT INTO `sys_role_resource` VALUES ('98', '23', '1');
INSERT INTO `sys_role_resource` VALUES ('99', '5', '1');
INSERT INTO `sys_role_resource` VALUES ('100', '24', '1');
INSERT INTO `sys_role_resource` VALUES ('101', '25', '1');
INSERT INTO `sys_role_resource` VALUES ('102', '26', '1');
INSERT INTO `sys_role_resource` VALUES ('103', '27', '1');
INSERT INTO `sys_role_resource` VALUES ('115', '6', '2');
INSERT INTO `sys_role_resource` VALUES ('116', '9', '2');
INSERT INTO `sys_role_resource` VALUES ('117', '10', '2');
INSERT INTO `sys_role_resource` VALUES ('118', '11', '2');
INSERT INTO `sys_role_resource` VALUES ('119', '12', '2');
INSERT INTO `sys_role_resource` VALUES ('120', '5', '2');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '用户名',
  `password` varchar(60) COLLATE utf8mb4_bin NOT NULL COMMENT '密码',
  `salt` varchar(10) COLLATE utf8mb4_bin NOT NULL COMMENT '盐',
  `nick_name` varchar(20) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '昵称',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  `head_picture` varchar(200) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '头像',
  `sex` char(1) COLLATE utf8mb4_bin NOT NULL COMMENT '性别',
  `phone` varchar(11) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '手机号',
  `email` varchar(100) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '邮箱',
  `status` smallint(6) NOT NULL COMMENT '状态 2、冻结 3、删除 0、注册 1、正常',
  `operator` varchar(20) COLLATE utf8mb4_bin NOT NULL COMMENT '操作人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='用户表';

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'e10adc3949ba59abbe56e057f20f883e', '', 'admin', '4', null, '1', '13888888888', 'songshid@163.com', '0', 'admin', '2019-05-14 14:00:10');
INSERT INTO `sys_user` VALUES ('2', 'jeesd', 'e10adc3949ba59abbe56e057f20f883e', 'test', 'jeesd', '2', null, '0', '13800000000', 'test@qq.com', '0', 'admin', '2019-05-26 16:18:06');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin COMMENT='角色表';

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('5', '1', '1');
INSERT INTO `sys_user_role` VALUES ('6', '1', '2');
INSERT INTO `sys_user_role` VALUES ('7', '2', '2');

-- ----------------------------
-- Table structure for sys_oauth_client_details
-- ----------------------------
DROP TABLE IF EXISTS `sys_oauth_client_details`;
CREATE TABLE `sys_oauth_client_details` (
  `client_id` varchar(60) NOT NULL,
  `resource_ids` varchar(255) DEFAULT NULL,
  `client_secret` varchar(255) DEFAULT NULL,
  `scope` varchar(256) DEFAULT NULL,
  `authorized_grant_types` varchar(255) DEFAULT NULL,
  `web_server_redirect_uri` varchar(255) DEFAULT NULL,
  `authorities` varchar(255) DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(255) DEFAULT NULL,
  `autoapprove` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户端信息表';

-- ----------------------------
-- Records of sys_oauth_client_details
-- ----------------------------
INSERT INTO `sys_oauth_client_details` VALUES ('jeesd', null, '$2a$10$8oIIPvpAhYOUAW0GlzCljuvGPWTS0Lb3zogTdw0.xMhAtgtRA9z0S', 'auth', 'password,refresh_token,client_credentials', null, null, '7200', null, null, '');
INSERT INTO `sys_oauth_client_details` VALUES ('test', null, '123456', 'auth', 'password,refresh_token', null, null, '7200', null, null, null);
