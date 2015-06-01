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
		ACC_001("ACC_001");

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
		ACC_101("ACC_101"),
		/** 昵称重复错误码 */
		ACC_102("ACC_102"),
		/** 原始密码异常 */
		ACC_103("ACC_103"),
		/** 日期格式异常 */
		ACC_104("ACC_104"),
		/** 账号格式异常 */
		ACC_105("ACC_105"),
		/** 昵称格式异常 */
		ACC_106("ACC_106"),
		/** 密码格式异常 */
		ACC_107("ACC_107"),
		/** 平台绑定异常 */
		ACC_108("ACC_108"),
		/** 账号或密码错误 */
		ACC_109("ACC_109");

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
		ACC_201("ACC_201"),
		/** 验证码重复验证异常 */
		ACC_202("ACC_202"),
		/** 验证码验证时间异常 */
		ACC_203("ACC_203"),
		/** 验证码错误 */
		ACC_204("ACC_204"),
		/** 邮箱已经被激活 */
		ACC_205("ACC_205");

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
		ACC_301("ACC_301"),
		/** 彼此已经互为好友，无需发送好友请求！ */
		ACC_302("ACC_302"),
		/** 对方已经向你发出好友邀请，您无需发送。 */
		ACC_303("ACC_303");

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
		ACC_401("ACC_401");
		
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
