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
		/** 好友（联系人） */
		ACC_FRIEND,
		/** 好友申请（申请加为好友） */
		ACC_FRIEND_APPLY,
		/** 好友申请时，往来的消息 */
		ACC_FRIEND_MESSAGE,
		/** 客户端唯一标识 */
		ACC_CLIENT_SESSION,
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
	 * 
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

	/**
	 * 好友（联系人）
	 * 
	 * @author songzigw
	 *
	 */
	public static enum FriendF implements Fields {
		/** 用户ID */
		USER_ID,
		/** 用户好友ID */
		FRIEND_USER_ID,
		/** 添加时间 */
		ADD_TIME,
	}

	/**
	 * 好友申请（申请加为好友）
	 * 
	 * @author songzigw
	 *
	 */
	public static enum FriendApplyF implements Fields {
		/** 主键ID */
		FAY_ID,
		/** 申请人 */
		FROM_USER_ID,
		/** 接受方 */
		TO_USER_ID,
		/** 申请时间 */
		ADD_TIME,
		/** 发送的信息 */
		FRIDEND_MESSAGE,
		/** 申请结果：0未处理 1同意  */
		RESULT_STATUS,
		/** 消息在申请方是否删除 */
		FROM_DEL_FLAG,
		/** 消息在接受方是否删除 */
		TO_DEL_FLAG,
	}
	
	/**
	 * 好友申请时，往来的消息
	 * 
	 * @author songzigw
	 *
	 */
	public static enum FriendMessageF implements Fields {
		/** 主键ID */
		FME_ID,
		/** 好友申请ID */
		FAY_ID,
		/** 消息放送方 */
		FROM_USER_ID,
		/** 消息接受方 */
		TO_USER_ID,
		/** 发送时间 */
		ADD_TIME,
		/** 信息内容 */
		MESSAGE,
	}
	
	public static enum ClientSessionF implements Fields {
		/** 客户端唯一标识ID */
		CLIENT_ID,
		/** 创建时间 */
		CREATION_TIME,
		/** 最近访问时间 */
		LAST_ACCESSED_TIME,
		/** 客户端对应用户ID */
		USER_ID,
		/** SESSION是否有效（超时） */
		VALID_FLAG,
	}
}
