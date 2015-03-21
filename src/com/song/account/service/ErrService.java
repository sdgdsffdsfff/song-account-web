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
		;

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
		ERR_100_001("100_001"),
		/** 昵称重复错误码 */
		ERR_100_002("100_002"),
		/** 原始密码异常 */
		ERR_100_003("100_003"),
		/** 日期格式异常 */
		ERR_100_004("100_004"),
		/** 账号格式异常 */
		ERR_100_005("100_005"),
		/** 昵称格式异常 */
		ERR_100_006("100_006"),
		/** 密码格式异常 */
		ERR_100_007("100_007"),
		/** 平台绑定异常 */
		ERR_100_008("100_008"),
		/** 账号或密码错误 */
		ERR_100_009("100_009");

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
		ERR_104_001("104_001"),
		/** 验证码重复验证异常 */
		ERR_104_002("104_002"),
		/** 验证码验证时间异常 */
		ERR_104_003("104_003"),
		/** 验证码错误 */
		ERR_104_004("104_004"),
		/** 邮箱已经被激活 */
		ERR_104_005("104_005");

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
}
