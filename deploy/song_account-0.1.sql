/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50151
Source Host           : 127.0.0.1:3306
Source Database       : song_account

Target Server Type    : MYSQL
Target Server Version : 50151
File Encoding         : 65001

Date: 2015-05-06 01:31:35
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `ACC_APP_INFO`
-- ----------------------------
DROP TABLE IF EXISTS `ACC_APP_INFO`;
CREATE TABLE `ACC_APP_INFO` (
  `APP_KEY` varchar(20) NOT NULL COMMENT 'appKey',
  `APP_SECRET` varchar(70) DEFAULT NULL COMMENT 'appSecret',
  `ADD_TIME` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`APP_KEY`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ACC_APP_INFO
-- ----------------------------

-- ----------------------------
-- Table structure for `ACC_BIND_SITE`
-- ----------------------------
DROP TABLE IF EXISTS `ACC_BIND_SITE`;
CREATE TABLE `ACC_BIND_SITE` (
  `BIND_SITE_ID` int(20) NOT NULL DEFAULT '0' COMMENT '主键ID',
  `ADD_TIME` datetime DEFAULT NULL COMMENT '添加时间',
  `USER_ID` int(20) DEFAULT NULL COMMENT '用户ID',
  `SITE_MARK` int(2) DEFAULT NULL COMMENT '第三方平台标示',
  `OPEN_ID` varchar(100) DEFAULT NULL COMMENT '第三方OpenId',
  `ACCESS_TOKEN` varchar(100) DEFAULT NULL COMMENT 'access_token',
  `NICK_NAME` varchar(50) DEFAULT NULL COMMENT '第三方平台用户名称',
  `REG_FLAG` int(1) DEFAULT NULL COMMENT '是否是第三方用户注册',
  `START_DATE` datetime DEFAULT NULL COMMENT '授权起始日期',
  `END_DATE` datetime DEFAULT NULL COMMENT '授权结束日期',
  `PHOTO_PATH` varchar(300) DEFAULT NULL COMMENT '第三方平台用户头像',
  `UPD_TIME` datetime DEFAULT NULL COMMENT 'UPD_TIME',
  PRIMARY KEY (`BIND_SITE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ACC_BIND_SITE
-- ----------------------------
INSERT INTO `ACC_BIND_SITE` VALUES ('1', '2014-03-20 16:55:15', '63', '1', '38016C3327F818C55213C5C45D3F48CC', '26AD20D4774F497C9936CEE65CDA054C', '泰', '1', null, null, null, null);
INSERT INTO `ACC_BIND_SITE` VALUES ('3', '2014-03-20 17:44:56', '21', '1', '2599134E0B4E83CBE9B641FEF47C1141', 'DE6CAD2F604F65F27101A8987A6D6F5E', '否', '0', null, null, null, null);
INSERT INTO `ACC_BIND_SITE` VALUES ('4', '2014-03-21 14:19:05', '64', '1', '9C8B620826BCB82265CD9EEC7BEB31DE', '3E5F7B0C76002FBFE0DD6DC2FFEA5013', '测试6', '1', null, null, null, null);
INSERT INTO `ACC_BIND_SITE` VALUES ('5', '2014-03-24 13:57:38', '65', '2', '3665359825', '2.00rWTDAEninn4B535088945dpmeEDD', '喜折购', '1', null, null, null, null);
INSERT INTO `ACC_BIND_SITE` VALUES ('6', '2014-04-04 16:09:13', '66', '2', '2570742490', '2.00YYZynCninn4B04f6d35c1e_8XjoC', 'R2two', '1', null, null, null, null);
INSERT INTO `ACC_BIND_SITE` VALUES ('7', '2014-05-27 10:33:22', '67', '1', 'C514FB11A1FA1ABA3CF9C8C3132D9EDE', 'E61698161C9888CB6902FEC21684101A', '                  小暖丶', '1', null, null, null, null);

-- ----------------------------
-- Table structure for `ACC_CLIENT_SESSION`
-- ----------------------------
DROP TABLE IF EXISTS `ACC_CLIENT_SESSION`;
CREATE TABLE `ACC_CLIENT_SESSION` (
  `CLIENT_ID` varchar(28) NOT NULL DEFAULT '',
  `CREATION_TIME` datetime DEFAULT NULL,
  `LAST_ACCESSED_TIME` datetime DEFAULT NULL,
  `USER_ID` int(20) DEFAULT NULL,
  `VALID_FLAG` int(1) DEFAULT NULL,
  PRIMARY KEY (`CLIENT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ACC_CLIENT_SESSION
-- ----------------------------
INSERT INTO `ACC_CLIENT_SESSION` VALUES ('5SWK6NMO7J8YZ74JQPR6SC7S07XK', '2015-04-21 13:13:12', '2015-04-22 15:49:06', '21', '1');
INSERT INTO `ACC_CLIENT_SESSION` VALUES ('9PKFRYDT4OFYPBO765W04JLPCDSV', '2015-04-24 17:21:36', '2015-04-24 17:55:09', '21', '1');
INSERT INTO `ACC_CLIENT_SESSION` VALUES ('DP5ULFJMCNPBI1E9JARMEJBPGPL9', '2015-04-11 10:05:58', '2015-04-11 22:24:58', '21', '1');
INSERT INTO `ACC_CLIENT_SESSION` VALUES ('I0ZQNIYCAMM3301JHA8SMTV6CHC5', '2015-04-09 13:11:57', '2015-04-09 16:25:11', '21', '1');
INSERT INTO `ACC_CLIENT_SESSION` VALUES ('NIAFMZ6TUBKRVSIU304ZFL0RY8BW', '2015-04-09 20:59:15', '2015-04-10 08:28:31', '21', '1');
INSERT INTO `ACC_CLIENT_SESSION` VALUES ('ZPL84QQ0ZNMW15SJQ7AQKVTZCQ9R', '2015-04-10 13:12:02', '2015-04-10 15:49:51', '21', '1');

-- ----------------------------
-- Table structure for `ACC_FRIEND`
-- ----------------------------
DROP TABLE IF EXISTS `ACC_FRIEND`;
CREATE TABLE `ACC_FRIEND` (
  `USER_ID` int(20) NOT NULL DEFAULT '0',
  `FRIEND_USER_ID` int(20) NOT NULL DEFAULT '0',
  `ADD_TIME` datetime DEFAULT NULL,
  PRIMARY KEY (`USER_ID`,`FRIEND_USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ACC_FRIEND
-- ----------------------------

-- ----------------------------
-- Table structure for `ACC_FRIEND_APPLY`
-- ----------------------------
DROP TABLE IF EXISTS `ACC_FRIEND_APPLY`;
CREATE TABLE `ACC_FRIEND_APPLY` (
  `FAY_ID` int(20) NOT NULL DEFAULT '0',
  `FROM_USER_ID` int(20) DEFAULT NULL,
  `TO_USER_ID` int(20) DEFAULT NULL,
  `ADD_TIME` datetime DEFAULT NULL,
  `FRIDEND_MESSAGE` varchar(50) DEFAULT NULL,
  `RESULT_STATUS` int(1) DEFAULT NULL,
  `FROM_DEL_FLAG` int(1) DEFAULT NULL,
  `TO_DEL_FLAG` int(1) DEFAULT NULL,
  PRIMARY KEY (`FAY_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ACC_FRIEND_APPLY
-- ----------------------------

-- ----------------------------
-- Table structure for `ACC_FRIEND_MESSAGE`
-- ----------------------------
DROP TABLE IF EXISTS `ACC_FRIEND_MESSAGE`;
CREATE TABLE `ACC_FRIEND_MESSAGE` (
  `FME_ID` int(20) NOT NULL DEFAULT '0',
  `FAY_ID` int(20) DEFAULT NULL,
  `FROM_USER_ID` int(20) DEFAULT NULL,
  `TO_USER_ID` int(20) DEFAULT NULL,
  `ADD_TIME` datetime DEFAULT NULL,
  `MESSAGE` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`FME_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ACC_FRIEND_MESSAGE
-- ----------------------------

-- ----------------------------
-- Table structure for `ACC_IDENTIFY_CODE`
-- ----------------------------
DROP TABLE IF EXISTS `ACC_IDENTIFY_CODE`;
CREATE TABLE `ACC_IDENTIFY_CODE` (
  `IC_ID` int(20) NOT NULL DEFAULT '0' COMMENT '主键ID',
  `ADD_TIME` datetime DEFAULT NULL COMMENT '添加时间',
  `DATA_ID` int(20) DEFAULT NULL COMMENT '待验证数据ID',
  `TABLE_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT 'TABLE_NAME',
  `FIELD_NAME` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '字段名称',
  `CODE` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '验证码',
  `START_TIME` datetime DEFAULT NULL COMMENT '验证起始时间',
  `END_TIME` datetime DEFAULT NULL COMMENT '验证结束时间',
  `VERIFY_TIME` datetime DEFAULT NULL COMMENT '验证提交时间',
  `VERIFY_STATE` int(2) DEFAULT NULL COMMENT '验证状态（0,未验证;1验证成功;2;验证失败）',
  `TEXT_DESC` varchar(50) COLLATE utf8_bin DEFAULT NULL COMMENT '内容描述',
  PRIMARY KEY (`IC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- ----------------------------
-- Records of ACC_IDENTIFY_CODE
-- ----------------------------
INSERT INTO `ACC_IDENTIFY_CODE` VALUES ('1', '2013-11-30 15:54:51', '1', 'XZG_USER', 'EM_IC_ID', 'QKP9NOXPK', '2013-11-30 15:54:51', '2013-12-01 15:54:51', '2013-11-30 16:19:35', '1', '??????258906142@qq.com');
INSERT INTO `ACC_IDENTIFY_CODE` VALUES ('2', '2013-11-30 16:34:26', '1', 'XZG_USER', 'EM_IC_ID', 'EBRO5J0KO', '2013-11-30 16:34:26', '2013-12-01 16:34:26', null, '0', '??????3598776258@qq.com');
INSERT INTO `ACC_IDENTIFY_CODE` VALUES ('3', '2013-11-30 16:34:41', '1', 'XZG_USER', 'EM_IC_ID', '6C7WEHN24', '2013-11-30 16:34:41', '2013-12-01 16:34:41', null, '0', '??????3598776258@qq.com');
INSERT INTO `ACC_IDENTIFY_CODE` VALUES ('4', '2013-11-30 16:39:02', '1', 'XZG_USER', 'EM_IC_ID', 'NCS11NSYW', '2013-11-30 16:39:02', '2013-12-01 16:39:02', null, '0', '??????3598776258@qq.com');
INSERT INTO `ACC_IDENTIFY_CODE` VALUES ('5', '2013-11-30 16:40:20', '1', 'XZG_USER', 'EM_IC_ID', 'KB46SD03R', '2013-11-30 16:40:20', '2013-12-01 16:40:20', '2013-11-30 16:46:39', '2', '??????3598776258@qq.com');
INSERT INTO `ACC_IDENTIFY_CODE` VALUES ('6', '2013-11-30 16:55:14', '1', 'XZG_USER', 'EM_IC_ID', 'MVAXUBQTF', '2013-11-30 16:55:14', '2013-12-01 16:55:14', '2013-11-30 16:58:58', '1', '??????3598776258@qq.com');
INSERT INTO `ACC_IDENTIFY_CODE` VALUES ('7', '2013-11-30 16:59:41', '1', 'XZG_USER', 'EM_IC_ID', '7CRXEW5P5', '2013-11-30 16:59:41', '2013-12-01 16:59:41', '2013-11-30 17:05:38', '1', '??????423515365@qq.com');
INSERT INTO `ACC_IDENTIFY_CODE` VALUES ('11', '2013-11-30 17:28:46', '1', 'XZG_USER', 'EM_IC_ID', '9RBHP372W', '2013-11-30 17:28:46', '2013-12-01 17:28:46', '2013-11-30 17:36:06', '1', '??????258906142@qq.com');
INSERT INTO `ACC_IDENTIFY_CODE` VALUES ('12', '2013-11-30 18:39:36', '41', 'XZG_USER', 'EM_IC_ID', '6QCMS8RER', '2013-11-30 18:39:36', '2013-12-01 18:39:36', '2013-11-30 18:41:56', '1', '??????348822724@qq.com');
INSERT INTO `ACC_IDENTIFY_CODE` VALUES ('13', '2013-11-30 19:12:33', '21', 'XZG_USER', 'EM_IC_ID', 'DPEJ160N9', '2013-11-30 19:12:33', '2013-12-01 19:12:33', null, '0', '??????songzigw@163.com');

-- ----------------------------
-- Table structure for `ACC_USER`
-- ----------------------------
DROP TABLE IF EXISTS `ACC_USER`;
CREATE TABLE `ACC_USER` (
  `USER_ID` int(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `ACCOUNT` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '账号',
  `PASSWORD` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `NICK_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '昵称',
  `USER_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '用户姓名',
  `ADD_TIME` datetime DEFAULT NULL COMMENT '添加时间',
  `PHOTO_PATH` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '头像路径',
  `SEX` int(1) DEFAULT NULL COMMENT '性别',
  `EMAIL` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '电子邮箱',
  `ACT_EMAIL` int(1) DEFAULT NULL,
  `BIRTHDAY_YEAR` int(4) unsigned DEFAULT NULL COMMENT '生日-年',
  `BIRTHDAY_MONTH` int(2) unsigned DEFAULT NULL COMMENT '生日-月',
  `BIRTHDAY_DAY` int(2) DEFAULT NULL COMMENT '生日-日',
  `SELLER_FLAG` int(1) DEFAULT NULL,
  `SUMMARY` varchar(100) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '简介',
  `EADDRESS` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'E通信地址（聊天工具）',
  `EN_EMAIL` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT 'Enable(激活的)',
  `EM_IC_ID` int(20) DEFAULT NULL COMMENT '电子邮件激活验证码',
  `RONG_TOKEN` varchar(120) DEFAULT NULL COMMENT 'RongCloud服务器上的Token',
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `ACC_USER_IND_2` (`NICK_NAME`) USING BTREE,
  UNIQUE KEY `ACC_USER_IND_1` (`ACCOUNT`) USING BTREE,
  UNIQUE KEY `ACC_USER_IND_3` (`EN_EMAIL`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ACC_USER
-- ----------------------------
INSERT INTO `ACC_USER` VALUES ('1', 'zhangsong', '748606ac481e2d91', '喜折购', '松子', '2013-09-02 14:45:05', '/res_account/images/user_logo/1.jpg', '2', '258906142@qq.com', null, '1930', '1', '1', '0', '', '{\"qqId\":\"258906142\"}', '258906142@qq.com', '11', null);
INSERT INTO `ACC_USER` VALUES ('21', 'songzigw', '748606ac481e2d91', '爱娜购', '张松', '2013-09-06 10:41:31', '/res_account/images/user_logo/1.jpg', '2', 'songzigw@163.com', null, '1985', '1', '30', '1', '我是来打酱油的，关注我哦！！！有惊喜哦！！！', '{\"qqId\":\"258906142\"}', '', '13', 'bwD01txjf2bkEBlpIWhgpeuxXSr8deC8oO9Moyszkcn3FgXd+vOAOffN1ykb4GFNOli7EWt6Du4fQRY8x3KQxg==');
INSERT INTO `ACC_USER` VALUES ('41', '348822724', 'a1d0bab5bd18efaf', 'ioe', null, '2013-11-30 18:36:03', '/res_account/images/user_logo/1.jpg', null, '348822724@qq.com', null, null, null, null, '0', null, null, '348822724@qq.com', '12', null);
INSERT INTO `ACC_USER` VALUES ('46', 'songzigw001', '748606ac481e2d91', '爱你1', '爱你1', '2014-01-13 12:23:26', '/res_account/images/user_logo/1.jpg', '2', null, null, '1930', '1', '1', '0', '', '{\"qqId\":\"258906142\"}', null, null, null);
INSERT INTO `ACC_USER` VALUES ('47', 'zkadmin', '68e963ef1e9940ac', '管理员', null, '2014-01-22 22:14:23', '/res_account/images/user_logo/1.jpg', null, null, null, null, null, null, '0', null, null, null, null, null);
INSERT INTO `ACC_USER` VALUES ('48', 'tian1005', '394759a0cfd161fe', '杰丽丝真爱美妆', '甜甜', '2014-01-23 15:30:53', '/res_account/images/user_logo/1.jpg', '2', null, null, '1988', '10', '5', '1', '', '{\"qqId\":\"67006355\",\"wangwangId\":\"杰丽丝真爱美妆\"}', null, null, null);
INSERT INTO `ACC_USER` VALUES ('49', 'taobao', '748606ac481e2d91', 'baobao', '淘宝', '2014-01-26 14:32:24', '/res_account/images/user_logo/3.jpg', null, null, null, '1930', '1', '1', '1', '', '{\"qqId\":\"258906142\"}', null, null, null);
INSERT INTO `ACC_USER` VALUES ('50', 'tmall', '748606ac481e2d91', 'maomao', null, '2014-01-26 14:36:27', '/res_account/images/user_logo/4.jpg', null, null, null, null, null, null, '1', null, null, null, null, null);
INSERT INTO `ACC_USER` VALUES ('51', 'sophiastella4480', 'ebfdc9c2b9612894', 'sophiastella', null, '2014-02-22 09:40:10', '/res_account/images/user_logo/1.jpg', null, null, null, null, null, null, '0', null, null, null, null, null);
INSERT INTO `ACC_USER` VALUES ('52', 'yinzi_0910', 'ba6b7e9c422fac53', '无关淮南淮北', null, '2014-03-07 16:31:14', '/res_account/images/user_logo/1.jpg', null, null, null, null, null, null, '1', null, null, null, null, null);
INSERT INTO `ACC_USER` VALUES ('53', 'taobao_nz', '748606ac481e2d91', '淘宝女装o', '张松', '2014-03-09 11:01:34', '/res_account/images/user_logo/1.jpg', null, null, null, '1930', '1', '1', '1', '淘宝女装', '{\"qqId\":\"258906142\"}', null, null, null);
INSERT INTO `ACC_USER` VALUES ('54', 'taobao_nanz', 'ff8aaa8a2dde9154', '淘宝男装o', null, '2014-03-09 11:02:53', '/res_account/images/user_logo/1.jpg', null, null, null, null, null, null, '1', null, null, null, null, null);
INSERT INTO `ACC_USER` VALUES ('55', 'taobao_jujia', '748606ac481e2d91', '淘宝居家o', null, '2014-03-09 11:03:48', '/res_account/images/user_logo/1.jpg', null, null, null, null, null, null, '1', null, null, null, null, null);
INSERT INTO `ACC_USER` VALUES ('56', 'wjy070316', 'a790fb2babed6daa', '漂流瓶o', '杨海燕', '2014-03-10 10:28:06', '/res_account/images/user_logo/5.jpg', '2', null, null, '1980', '8', '2', '1', '以诚会友，美丽而又自信', '{\"qqId\":\"38773406\"}', null, null, null);
INSERT INTO `ACC_USER` VALUES ('57', 'taobao_lyq', 'ff8aaa8a2dde9154', '淘宝连衣裙o', null, '2014-03-10 21:21:25', '/res_account/images/user_logo/1.jpg', null, null, null, null, null, null, '1', null, null, null, null, null);
INSERT INTO `ACC_USER` VALUES ('58', 'taobao_neiyi', 'ff8aaa8a2dde9154', '淘宝内衣o', null, '2014-03-10 23:09:04', '/res_account/images/user_logo/1.jpg', null, null, null, null, null, null, '1', null, null, null, null, null);
INSERT INTO `ACC_USER` VALUES ('59', 'shujun668', 'e1cd072856a2aea4', '潮流女装o', '唐淑君', '2014-03-11 11:55:45', '/res_account/images/user_logo/1.jpg', '2', null, null, '1990', '5', '1', '1', '', '{\"qqId\":\"584882413\",\"wangwangId\":\"吖淑女吧专店\"}', null, null, null);
INSERT INTO `ACC_USER` VALUES ('60', 'celia_18', '666c8e80c54aab6d', '七匹狼针纺o', '邵杭燕', '2014-03-11 12:51:53', '/res_account/images/user_logo/1.jpg', '2', null, null, '1930', '1', '1', '1', '', '{\"qqId\":\"304640639\",\"wangwangId\":\"hujianguo5\"}', null, null, null);
INSERT INTO `ACC_USER` VALUES ('61', '1025270264', 'b1f4c24e0c1b64ce', '09年的夏天o', null, '2014-03-11 14:57:33', '/res_account/images/user_logo/1.jpg', null, null, null, null, null, null, '0', null, null, null, null, null);
INSERT INTO `ACC_USER` VALUES ('62', 'minixx77', '3b9d9704584f315d', 'minixx77o', null, '2014-03-11 17:04:41', '/res_account/images/user_logo/3.jpg', null, null, null, null, null, null, '1', null, null, null, null, null);
INSERT INTO `ACC_USER` VALUES ('63', null, null, '泰63', null, '2014-03-20 16:55:15', '/res_account/images/user_logo/1.jpg', null, null, null, null, null, null, '0', null, null, null, null, null);
INSERT INTO `ACC_USER` VALUES ('64', null, null, '测试664', null, '2014-03-21 14:19:04', '/res_account/images/user_logo/1.jpg', null, null, null, null, null, null, '0', null, null, null, null, null);
INSERT INTO `ACC_USER` VALUES ('65', null, null, '喜折购65', null, '2014-03-24 13:57:38', '/res_account/images/user_logo/1.jpg', null, null, null, null, null, null, '0', null, null, null, null, null);
INSERT INTO `ACC_USER` VALUES ('66', null, null, 'R2two66', null, '2014-04-04 16:09:13', '/res_account/images/user_logo/1.jpg', null, null, null, null, null, null, '0', null, null, null, null, null);
INSERT INTO `ACC_USER` VALUES ('67', null, null, '小暖o', '吴俊', '2014-05-27 10:33:22', '/res_account/images/user_logo/1.jpg', '2', null, null, '1989', '10', '23', '0', '简约时尚魅力！', '{\"qqId\":\"362206876\"}', null, null, null);

-- ----------------------------
-- Table structure for `DPE_COLLOCATION`
-- ----------------------------
DROP TABLE IF EXISTS `DPE_COLLOCATION`;
CREATE TABLE `DPE_COLLOCATION` (
  `COL_ID` int(20) NOT NULL DEFAULT '0' COMMENT '搭配ID',
  `PROMULGATOR_ID` int(20) DEFAULT NULL COMMENT '发布者ID',
  `DESCRIPTION` varchar(140) DEFAULT NULL COMMENT '搭配描述',
  `COL_PIC_ID` int(20) DEFAULT NULL COMMENT '搭配照主图ID',
  `COL_PIC_URL` varchar(200) DEFAULT NULL COMMENT '搭配照主图地址',
  `ADD_TIME` datetime DEFAULT NULL COMMENT '发布时间',
  `DEL_FLAG` int(1) DEFAULT NULL COMMENT '是否删除',
  `TAGS` varchar(100) DEFAULT NULL COMMENT '搭配标签',
  `ME_FLAG` int(1) DEFAULT NULL COMMENT '是否是本人照',
  `FORWARD_COL_ID` int(20) DEFAULT NULL COMMENT '被转发搭配ID',
  `FIRST_COL_ID` int(20) DEFAULT NULL COMMENT '原始搭配ID',
  `CHECK_CODE` int(1) DEFAULT NULL COMMENT '审核状态码',
  PRIMARY KEY (`COL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of DPE_COLLOCATION
-- ----------------------------

-- ----------------------------
-- Table structure for `DPE_COL_CHECK`
-- ----------------------------
DROP TABLE IF EXISTS `DPE_COL_CHECK`;
CREATE TABLE `DPE_COL_CHECK` (
  `COL_CHECK_ID` int(20) NOT NULL DEFAULT '0' COMMENT '操作ID',
  `COL_ID` int(20) DEFAULT NULL COMMENT '搭配ID',
  `OPR_ID` int(20) DEFAULT NULL COMMENT '操作者ID',
  `CHECK_CODE` int(1) DEFAULT NULL COMMENT '审核状态码',
  `CHCEK_DESC` varchar(50) DEFAULT NULL COMMENT '审核描述',
  `ADD_TIME` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`COL_CHECK_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of DPE_COL_CHECK
-- ----------------------------

-- ----------------------------
-- Table structure for `DPE_COL_CLASSIFY`
-- ----------------------------
DROP TABLE IF EXISTS `DPE_COL_CLASSIFY`;
CREATE TABLE `DPE_COL_CLASSIFY` (
  `COL_CLA_ID` int(20) NOT NULL COMMENT '主键',
  `COL_ID` int(20) DEFAULT NULL COMMENT '搭配ID',
  `TYPE_ID` int(20) DEFAULT NULL COMMENT '类别ID',
  `ADD_TIME` datetime DEFAULT NULL COMMENT '添加时间',
  `OPR_ID` int(20) DEFAULT NULL COMMENT '操作员',
  PRIMARY KEY (`COL_CLA_ID`),
  UNIQUE KEY `DPE_COL_CLA_IND_1` (`COL_ID`,`TYPE_ID`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of DPE_COL_CLASSIFY
-- ----------------------------

-- ----------------------------
-- Table structure for `DPE_COL_COMMENT`
-- ----------------------------
DROP TABLE IF EXISTS `DPE_COL_COMMENT`;
CREATE TABLE `DPE_COL_COMMENT` (
  `COL_COM_ID` int(20) NOT NULL,
  `USER_ID` int(20) DEFAULT NULL,
  `COL_ID` int(20) DEFAULT NULL,
  `ADD_TIME` datetime DEFAULT NULL,
  `COM_TEXT` varchar(280) DEFAULT NULL,
  PRIMARY KEY (`COL_COM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of DPE_COL_COMMENT
-- ----------------------------

-- ----------------------------
-- Table structure for `DPE_COL_EVERYDAY`
-- ----------------------------
DROP TABLE IF EXISTS `DPE_COL_EVERYDAY`;
CREATE TABLE `DPE_COL_EVERYDAY` (
  `COL_ID` int(20) NOT NULL COMMENT '搭配ID',
  `ADD_TIME` datetime DEFAULT NULL COMMENT '添加时间',
  `OPR_ID` int(20) DEFAULT NULL COMMENT '操作员',
  PRIMARY KEY (`COL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of DPE_COL_EVERYDAY
-- ----------------------------

-- ----------------------------
-- Table structure for `DPE_COL_FAVORITE`
-- ----------------------------
DROP TABLE IF EXISTS `DPE_COL_FAVORITE`;
CREATE TABLE `DPE_COL_FAVORITE` (
  `COL_ID` int(20) NOT NULL COMMENT '搭配ID',
  `USER_ID` int(20) NOT NULL COMMENT '用户ID',
  `ADD_TIME` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`COL_ID`,`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of DPE_COL_FAVORITE
-- ----------------------------

-- ----------------------------
-- Table structure for `DPE_COL_PICTURE`
-- ----------------------------
DROP TABLE IF EXISTS `DPE_COL_PICTURE`;
CREATE TABLE `DPE_COL_PICTURE` (
  `COL_PIC_ID` int(20) NOT NULL COMMENT '搭配照ID',
  `COL_ID` int(20) DEFAULT NULL COMMENT '搭配主表ID',
  `PROMULGATOR_ID` int(20) DEFAULT NULL COMMENT '发布者ID',
  `COVER_FLAG` int(1) DEFAULT NULL COMMENT '是否主图',
  `ADD_TIME` datetime DEFAULT NULL COMMENT '发布时间',
  `DESCRIPTION` varchar(140) DEFAULT NULL COMMENT '照片描述',
  `PIC_URL` varchar(200) DEFAULT NULL COMMENT '搭配照地址',
  PRIMARY KEY (`COL_PIC_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of DPE_COL_PICTURE
-- ----------------------------

-- ----------------------------
-- Table structure for `DPE_COL_PRAISE`
-- ----------------------------
DROP TABLE IF EXISTS `DPE_COL_PRAISE`;
CREATE TABLE `DPE_COL_PRAISE` (
  `USER_ID` int(20) NOT NULL COMMENT '用户ID',
  `COL_ID` int(20) NOT NULL COMMENT '搭配ID',
  `ADD_TIME` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`USER_ID`,`COL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of DPE_COL_PRAISE
-- ----------------------------

-- ----------------------------
-- Table structure for `DPE_COL_STATISTICS`
-- ----------------------------
DROP TABLE IF EXISTS `DPE_COL_STATISTICS`;
CREATE TABLE `DPE_COL_STATISTICS` (
  `COL_ID` int(20) NOT NULL COMMENT '搭配ID',
  `PRAISE_COUNT` int(11) DEFAULT NULL COMMENT '点攒数',
  `COMMENT_COUNT` int(11) DEFAULT NULL COMMENT '评论数',
  `FAVORITE_COUNT` int(11) DEFAULT NULL COMMENT '收藏数',
  `FORWARD_COUNT` int(11) DEFAULT NULL COMMENT '转发数',
  `ADD_TIME` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`COL_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of DPE_COL_STATISTICS
-- ----------------------------

-- ----------------------------
-- Table structure for `DPE_COL_TAG`
-- ----------------------------
DROP TABLE IF EXISTS `DPE_COL_TAG`;
CREATE TABLE `DPE_COL_TAG` (
  `TAG_ID` int(20) NOT NULL COMMENT '标签ID',
  `TAG_NAME` varchar(50) DEFAULT NULL COMMENT '标签名称',
  `TYPE_ID` int(20) DEFAULT NULL COMMENT '标签类别ID',
  `ADD_TIME` datetime DEFAULT NULL COMMENT '创建时间',
  `PARENT_ID` int(20) DEFAULT NULL COMMENT '父级标签ID',
  PRIMARY KEY (`TAG_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of DPE_COL_TAG
-- ----------------------------

-- ----------------------------
-- Table structure for `DPE_COL_TYPE`
-- ----------------------------
DROP TABLE IF EXISTS `DPE_COL_TYPE`;
CREATE TABLE `DPE_COL_TYPE` (
  `TYPE_ID` int(20) NOT NULL COMMENT '搭配类别ID',
  `TYPE_NAME` varchar(50) DEFAULT NULL COMMENT '搭配类别名称',
  `TYPE_STATE` int(1) DEFAULT NULL COMMENT '搭配类别状态',
  `TYPE_DESC` varchar(50) DEFAULT NULL COMMENT '搭配类别描述',
  `ADD_TIME` datetime DEFAULT NULL COMMENT '添加时间',
  `PICTURE_ICO` varchar(200) DEFAULT NULL COMMENT '图标',
  `PICTURE_ICO2` varchar(200) DEFAULT NULL COMMENT '图标2',
  PRIMARY KEY (`TYPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of DPE_COL_TYPE
-- ----------------------------

-- ----------------------------
-- Table structure for `DPE_COL_USER`
-- ----------------------------
DROP TABLE IF EXISTS `DPE_COL_USER`;
CREATE TABLE `DPE_COL_USER` (
  `USER_ID` int(20) NOT NULL COMMENT '用户ID',
  `ADD_TIME` datetime DEFAULT NULL COMMENT '添加时间',
  `TAGS` varchar(100) DEFAULT NULL COMMENT '用户标签',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of DPE_COL_USER
-- ----------------------------

-- ----------------------------
-- Table structure for `DPE_DEVELOPMENT`
-- ----------------------------
DROP TABLE IF EXISTS `DPE_DEVELOPMENT`;
CREATE TABLE `DPE_DEVELOPMENT` (
  `DEV_ID` int(20) NOT NULL DEFAULT '0' COMMENT '动态ID',
  `USER_ID` int(20) DEFAULT NULL COMMENT '用户ID',
  `DESCRIPTION` varchar(50) DEFAULT NULL COMMENT '动态描述',
  `DEV_TYPE` int(3) DEFAULT NULL COMMENT '动态类型',
  `TARGET_ID` int(20) DEFAULT NULL COMMENT '目标ID',
  `ADD_TIME` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`DEV_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of DPE_DEVELOPMENT
-- ----------------------------

-- ----------------------------
-- Table structure for `DPE_USER_FOLLOW`
-- ----------------------------
DROP TABLE IF EXISTS `DPE_USER_FOLLOW`;
CREATE TABLE `DPE_USER_FOLLOW` (
  `FOCUS_ID` int(20) NOT NULL COMMENT '用户ID（焦点人物）',
  `ADD_TIME` datetime DEFAULT NULL COMMENT '添加时间',
  `FOLLOW_ID` int(20) NOT NULL COMMENT '粉丝ID',
  PRIMARY KEY (`FOCUS_ID`,`FOLLOW_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of DPE_USER_FOLLOW
-- ----------------------------

-- ----------------------------
-- Table structure for `DPE_USER_STATISTICS`
-- ----------------------------
DROP TABLE IF EXISTS `DPE_USER_STATISTICS`;
CREATE TABLE `DPE_USER_STATISTICS` (
  `USER_ID` int(20) NOT NULL DEFAULT '0' COMMENT '用户ID',
  `ADD_TIME` date DEFAULT NULL COMMENT '添加时间',
  `FOLLOW_COUNT` int(11) DEFAULT NULL COMMENT '粉丝数',
  `REGARD_COUNT` int(11) DEFAULT NULL COMMENT '关注数',
  `DAPEI_COUNT` int(11) DEFAULT NULL COMMENT '搭配数',
  `DEVEL_COUNT` int(11) DEFAULT NULL COMMENT '动态数',
  PRIMARY KEY (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of DPE_USER_STATISTICS
-- ----------------------------

-- ----------------------------
-- Table structure for `KEY_TABLE`
-- ----------------------------
DROP TABLE IF EXISTS `KEY_TABLE`;
CREATE TABLE `KEY_TABLE` (
  `KEY_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '',
  `KEY_VALUE` int(20) NOT NULL DEFAULT '0',
  PRIMARY KEY (`KEY_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of KEY_TABLE
-- ----------------------------
INSERT INTO `KEY_TABLE` VALUES ('ACC_BIND_SITE', '7');
INSERT INTO `KEY_TABLE` VALUES ('ACC_CLIENT_SESSION', '1');
INSERT INTO `KEY_TABLE` VALUES ('ACC_FRIEND', '1');
INSERT INTO `KEY_TABLE` VALUES ('ACC_FRIEND_APPLY', '1');
INSERT INTO `KEY_TABLE` VALUES ('ACC_FRIEND_MESSAGE', '1');
INSERT INTO `KEY_TABLE` VALUES ('ACC_IDENTIFY_CODE', '15');
INSERT INTO `KEY_TABLE` VALUES ('ACC_USER', '67');
INSERT INTO `KEY_TABLE` VALUES ('OPE_ADMIN', '1');

-- ----------------------------
-- Table structure for `OPN_ADMIN`
-- ----------------------------
DROP TABLE IF EXISTS `OPN_ADMIN`;
CREATE TABLE `OPN_ADMIN` (
  `ADMIN_ID` int(20) NOT NULL COMMENT '管理员ID',
  `ACCOUNT` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL COMMENT '账号',
  `PASSWORD` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '密码',
  `ADMIN_NAME` varchar(50) CHARACTER SET utf8 COLLATE utf8_bin DEFAULT NULL COMMENT '姓名',
  `ADD_TIME` datetime DEFAULT NULL COMMENT '添加时间',
  `PHOTO_PATH` varchar(200) DEFAULT NULL COMMENT '用户头像',
  `EMAIL` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `PHONE` varchar(50) DEFAULT NULL COMMENT '电话',
  `GENDER` int(1) DEFAULT NULL COMMENT '1男 2女',
  `USER_ID` int(20) NOT NULL COMMENT '关联用户ID（一旦关联不能修改）',
  PRIMARY KEY (`ADMIN_ID`),
  UNIQUE KEY `OPN_ADMIN_IND_1` (`USER_ID`) USING BTREE,
  UNIQUE KEY `OPN_ADMIN_IND_2` (`ACCOUNT`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of OPN_ADMIN
-- ----------------------------
INSERT INTO `OPN_ADMIN` VALUES ('1', 'zhangsong', '748606ac481e2d91', '松子', '2013-10-04 20:15:18', null, null, null, null, '1');

-- ----------------------------
-- Table structure for `OPN_ADMINROLE`
-- ----------------------------
DROP TABLE IF EXISTS `OPN_ADMINROLE`;
CREATE TABLE `OPN_ADMINROLE` (
  `ADMIN_ID` int(20) NOT NULL DEFAULT '0' COMMENT '管理员ID',
  `ROLE_ID` int(20) NOT NULL DEFAULT '0' COMMENT '角色ID',
  `ADD_TIME` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`ADMIN_ID`,`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OPN_ADMINROLE
-- ----------------------------

-- ----------------------------
-- Table structure for `OPN_AUTHORITY`
-- ----------------------------
DROP TABLE IF EXISTS `OPN_AUTHORITY`;
CREATE TABLE `OPN_AUTHORITY` (
  `ROLE_ID` int(20) NOT NULL DEFAULT '0' COMMENT '角色ID',
  `MENU_ID` int(20) NOT NULL DEFAULT '0' COMMENT '菜单ID',
  `OPE_IDS` varchar(50) DEFAULT NULL COMMENT '操作范围',
  `ADD_TIME` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`ROLE_ID`,`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OPN_AUTHORITY
-- ----------------------------

-- ----------------------------
-- Table structure for `OPN_MENU`
-- ----------------------------
DROP TABLE IF EXISTS `OPN_MENU`;
CREATE TABLE `OPN_MENU` (
  `MENU_ID` int(20) NOT NULL DEFAULT '0' COMMENT '菜单ID',
  `MENU_NAME` varchar(50) DEFAULT NULL COMMENT '菜单名称',
  `MENU_DESC` varchar(100) DEFAULT NULL COMMENT '菜单描述',
  `ADD_TIME` datetime DEFAULT NULL COMMENT '添加时间',
  `PRIOR_ID` int(20) DEFAULT NULL COMMENT '上级ID',
  `ACT_URL` varchar(200) DEFAULT NULL COMMENT 'ACT_URL',
  `OPE_IDS` varchar(50) DEFAULT NULL COMMENT '操作范围（“,”隔开）',
  PRIMARY KEY (`MENU_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OPN_MENU
-- ----------------------------

-- ----------------------------
-- Table structure for `OPN_OPERATION`
-- ----------------------------
DROP TABLE IF EXISTS `OPN_OPERATION`;
CREATE TABLE `OPN_OPERATION` (
  `OPE_ID` int(20) NOT NULL DEFAULT '0' COMMENT '操作项ID',
  `OPE_NAME` varchar(50) DEFAULT NULL COMMENT '操作项名称',
  `ADD_TIME` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`OPE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OPN_OPERATION
-- ----------------------------

-- ----------------------------
-- Table structure for `OPN_ROLE`
-- ----------------------------
DROP TABLE IF EXISTS `OPN_ROLE`;
CREATE TABLE `OPN_ROLE` (
  `ROLE_ID` int(20) NOT NULL DEFAULT '0' COMMENT '角色ID',
  `ROLE_NAME` varchar(50) DEFAULT NULL COMMENT '角色名称',
  `ROLE_DESC` varchar(100) DEFAULT NULL COMMENT '角色描述',
  `ADD_TIME` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`ROLE_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of OPN_ROLE
-- ----------------------------
