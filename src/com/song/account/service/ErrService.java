package com.song.account.service;

import com.song.commons.service.ErrorInfo;

/**
 * 异常的业务错误码
 * 
 * @author songzigw
 * 
 */
public abstract class ErrService {
	/**
	 * 通用错误码
	 * @author songzigw
	 *
	 */
	public static enum Common implements ErrorInfo {
		/** 不能自己给自己发信息 */
		ACC_000_001("ACC_OOO_001");

		private final String errCode;

		public String getErrCode() {
			return errCode;
		}

		private Common(String errCode) {
			this.errCode = errCode;
		}

		public Common getInstance(String errCode) {
			for (Common c : Common.values()) {
				if (c.getErrCode().equals(errCode)) {
					return c;
				}
			}
			return null;
		}
	}
	
	/**
	 * 用户业务相关错误码
	 * @author 张松
	 *
	 */
	public static enum UserS implements ErrorInfo {
		/** 账号重复错误码 */
		ACC_100_001("ACC_100_001"),
		/** 昵称重复错误码 */
		ACC_100_002("ACC_100_002"),
		/** 原始密码异常 */
		ACC_100_003("ACC_100_003"),
		/** 日期格式异常 */
		ACC_100_004("ACC_100_004"),
		/** 账号格式异常 */
		ACC_100_005("ACC_100_005"),
		/** 昵称格式异常 */
		ACC_100_006("ACC_100_006"),
		/** 密码格式异常 */
		ACC_100_007("ACC_100_007"),
		/** 平台绑定异常 */
		ACC_100_008("ACC_100_008"),
		/** 账号或密码错误 */
		ACC_100_009("ACC_100_009");

		private final String errCode;

		public String getErrCode() {
			return errCode;
		}

		private UserS(String errCode) {
			this.errCode = errCode;
		}

		/**
		 * 以枚举值得到枚举类型的实例
		 * 
		 * @param errCode
		 * @return
		 */
		public UserS getInstance(String errCode) {
			for (UserS e : UserS.values()) {
				if (e.getErrCode().equals(errCode)) {
					return e;
				}
			}
			return null;
		}
	}
	
	public static enum IdentifyCodeS implements ErrorInfo {
		/** 验证码未生成异常 */
		ACC_101_001("ACC_101_001"),
		/** 验证码重复验证异常 */
		ACC_101_002("ACC_101_002"),
		/** 验证码验证时间异常 */
		ACC_101_003("ACC_101_003"),
		/** 验证码错误 */
		ACC_101_004("ACC_101_004"),
		/** 邮箱已经被激活 */
		ACC_101_005("ACC_101_005");

		private final String errCode;

		/**
		 * 得到枚举值
		 * 
		 * @return
		 */
		public String getErrCode() {
			return errCode;
		}

		private IdentifyCodeS(String errCode) {
			this.errCode = errCode;
		}

		/**
		 * 以枚举值得到枚举类型的实例
		 * 
		 * @param errCode
		 * @return
		 */
		public IdentifyCodeS getInstance(String errCode) {
			for (IdentifyCodeS e : IdentifyCodeS.values()) {
				if (e.getErrCode().equals(errCode)) {
					return e;
				}
			}
			return null;
		}
	}
	
	public static enum FriendE implements ErrorInfo {
		/** 不能自己給自己发送好友请求 */
		ACC_102_001("ACC_1O2_001"),
		/** 彼此已经互为好友，无需发送好友请求！ */
		ACC_102_002("ACC_102_002"),
		/** 对方已经向你发出好友邀请，您无需发送。 */
		ACC_102_003("ACC_102_003");

		private final String errCode;

		public String getErrCode() {
			return errCode;
		}

		private FriendE(String errCode) {
			this.errCode = errCode;
		}

		public FriendE getInstance(String errCode) {
			for (FriendE o : FriendE.values()) {
				if (o.getErrCode().equals(errCode)) {
					return o;
				}
			}
			return null;
		}
	}
	
	public static enum SSOAuthE implements ErrorInfo {
		/** 用户登入已经失效 */
		ACC_103_001("ACC_1O3_001");
		
		private final String errCode;

		public String getErrCode() {
			return errCode;
		}

		private SSOAuthE(String errCode) {
			this.errCode = errCode;
		}

		public SSOAuthE getInstance(String errCode) {
			for (SSOAuthE o : SSOAuthE.values()) {
				if (o.getErrCode().equals(errCode)) {
					return o;
				}
			}
			return null;
		}
	}
}
