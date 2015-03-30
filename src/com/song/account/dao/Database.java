package com.song.account.dao;

import com.song.commons.dao.Fields;
import com.song.commons.dao.Tables;

public abstract class Database {

	public static enum Account implements Tables {
		/** 用户表 */
		ACC_USER,
		/** 数据验证码 */
		ACC_IDENTIFY_CODE,
		/** 绑定第三方平台 */
		ACC_BIND_SITE,
		/** 应用相关信息 */
		ACC_APP_INFO,
	}

	public static enum IdentifyCodeF implements Fields {
		/** 主键ID */
		IC_ID,
		/** 添加时间 */
		ADD_TIME,
		/** 待验证数据ID */
		DATA_ID,
		/** 表名称 */
		TABLE_NAME,
		/** 字段名称 */
		FIELD_NAME,
		/** 验证码 */
		CODE,
		/** 验证起始时间 */
		START_TIME,
		/** 验证结束时间 */
		END_TIME,
		/** 验证提交时间 */
		VERIFY_TIME,
		/** 验证状态（0,未验证;1验证成功;2;验证失败） */
		VERIFY_STATE,
		/** 内容描述 */
		TEXT_DESC,
	}
	
	/**
	 * 用户表的字段
	 * 
	 * @author 张松
	 * 
	 */
	public static enum UserF implements Fields {
		/** 用户ID */
		USER_ID,
		/** 账号 */
		ACCOUNT,
		/** 密码 */
		PASSWORD,
		/** 昵称 */
		NICK_NAME,
		/** 用户姓名 */
		USER_NAME,
		/** 添加时间 */
		ADD_TIME,
		/** 头像路径 */
		PHOTO_PATH,
		/** 性别 */
		SEX,
		/** 电子邮箱 */
		EMAIL,
		/** Enable(激活的) */
		EN_EMAIL,
		/** 电子邮件激活验证码 */
		EM_IC_ID,
		/** 生日-年 */
		BIRTHDAY_YEAR,
		/** 生日-月 */
		BIRTHDAY_MONTH,
		/** 生日-日 */
		BIRTHDAY_DAY,
		/** 简介 */
		SUMMARY,
		/** E通信地址（聊天工具） */
		EADDRESS,
		/** 用户在RongCloud服务器上的Token */
		RONG_TOKEN,
	}
	
	public static enum BindSiteF implements Fields {
		/** 主键ID */
		BIND_SITE_ID,
		/** 添加时间 */
		ADD_TIME,
		/** 用户ID */
		USER_ID,
		/** 第三方平台标示 */
		SITE_MARK,
		/** 第三方OpenId */
		OPEN_ID,
		/** access_token */
		ACCESS_TOKEN,
		/** 第三方平台用户名称 */
		NICK_NAME,
		/** 是否是第三方用户注册 */
		REG_FLAG,
		/** 授权起始日期 */
		START_DATE,
		/** 授权结束日期 */
		END_DATE,
		/** 第三方平台用户头像 */
		PHOTO_PATH,
		/** 修改时间 */
		UPD_TIME,
	}
	
	/**
	 * APP信息
	 * @author 张松
	 *
	 */
	public static enum AppInfoF implements Fields {
		/** appKey */
		APP_KEY,
		/** appSecret */
		APP_SECRET,
		/** 添加时间 */
		ADD_TIME,
	}
	
}
