package com.song.account.service;


/**
 * 数据验证码业务类
 * @author 张松
 *
 */
public interface IdentifyCodeService {
	
	/**
	 * 验证激活电子邮箱
	 * 
	 * @param userId
	 * @param code
	 * @return
	 */
	public boolean verifyEmail(Long userId, String code);
	
	/**
	 * 发送Email邮箱激活验证码
	 * @param userId
	 * @param url
	 */
	public void sendEmailIdCode(Long userId, String url);
}
