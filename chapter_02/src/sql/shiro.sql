drop database if exists myshiro2;
create database myshiro2;
use myshiro2;

CREATE TABLE `sys_organization` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `available` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO sys_organization values(1, '总公司', 1, 0, 1);
INSERT INTO sys_organization values(2, '分公司1', 1, 1, 1);
INSERT INTO sys_organization values(3, '分公司2', 2, 1, 1);
INSERT INTO sys_organization values(4, '分公司11', 1, 2, 1);


CREATE TABLE `sys_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `priority` int(11) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `permission` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO sys_resource values(1, '资源', 'menu', '', 1, 0,'',1);

INSERT INTO sys_resource values(11, '组织机构管理', 'menu', '/organization', 1, 1,'organization:*',1);
INSERT INTO sys_resource values(12, '组织机构增加', 'button', '', 1, 11,'organization:create',1);
INSERT INTO sys_resource values(13, '组织机构修改', 'button', '', 2, 11,'organization:update',1);
INSERT INTO sys_resource values(14, '组织机构删除', 'button', '', 3, 11,'organization:delete',1);
INSERT INTO sys_resource values(15, '组织机构查看', 'button', '', 4, 11,'organization:view',1);

INSERT INTO sys_resource values(21, '用户管理', 'menu', '/user', 2, 1,'user:*',1);
INSERT INTO sys_resource values(22, '用户增加', 'button', '', 1, 21,'user:create',1);
INSERT INTO sys_resource values(23, '用户修改', 'button', '', 2, 21,'user:update',1);
INSERT INTO sys_resource values(24, '用户删除', 'button', '', 3, 21,'user:delete',1);
INSERT INTO sys_resource values(25, '用户查看', 'button', '', 4, 21,'user:view',1);

INSERT INTO sys_resource values(31, '资源管理', 'menu', '/resource', 3, 1,'resource:*',1);
INSERT INTO sys_resource values(32, '资源增加', 'button', '', 1, 31,'resource:create',1);
INSERT INTO sys_resource values(33, '资源修改', 'button', '', 2, 31,'resource:update',1);
INSERT INTO sys_resource values(34, '资源删除', 'button', '', 3, 31,'resource:delete',1);
INSERT INTO sys_resource values(35, '资源查看', 'button', '', 4, 31,'resource:view',1);


INSERT INTO sys_resource values(41, '角色管理', 'menu', '/role', 4, 1,'role:*',1);
INSERT INTO sys_resource values(42, '角色增加', 'button', '', 1, 41,'role:create',1);
INSERT INTO sys_resource values(43, '角色修改', 'button', '', 2, 41,'role:update',1);
INSERT INTO sys_resource values(44, '角色删除', 'button', '', 3, 41,'role:delete',1);
INSERT INTO sys_resource values(45, '角色查看', 'button', '', 4, 41,'role:view',1);

INSERT INTO sys_resource values(51, '系统日志', 'menu', '/log', 5, 1,'log:*',1);

CREATE TABLE `sys_user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `salt` varchar(50) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO sys_user VALUES (1,'admin','edacefd0d298af6111ffb9c806d9ea2b','d38e6fa8c076c1e4b40f978e218d3ed3',1);

CREATE TABLE `sys_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `rolename` varchar(50) DEFAULT NULL,
  `description` varchar(50) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO sys_role VALUES (1, 'admin', '超级管理员', 1);

CREATE TABLE `sys_user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `role_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO sys_user_role VALUES (1, 1, 1);

CREATE TABLE `sys_role_resource` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) DEFAULT NULL,
  `resource_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT INTO  sys_role_resource VALUES (1, 1, 11);
INSERT INTO  sys_role_resource VALUES (2, 1, 21);
INSERT INTO  sys_role_resource VALUES (3, 1, 31);
INSERT INTO  sys_role_resource VALUES (4, 1, 41);
INSERT INTO  sys_role_resource VALUES (5, 1, 51);

CREATE TABLE `sys_log` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) DEFAULT NULL,
  `operation` varchar(50) DEFAULT NULL,
  `method` varchar(200) DEFAULT NULL,
  `params` varchar(200) DEFAULT NULL,
  `time` bigint(20) DEFAULT NULL,
  `ip` varchar(50) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;





