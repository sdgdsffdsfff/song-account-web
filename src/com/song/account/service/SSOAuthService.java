package com.song.account.service;

import com.song.account.entity.User;
import com.song.commons.client.ClientSession;
import com.song.commons.service.SSOAuthCallback;

/**
 * 统一身份认证服务
 * 
 * @author songzigw
 *
 */
public interface SSOAuthService extends SSOAuthCallback<User> {

	/** 用户ID的SESSION */
	public static final String SESSION_USERID_KEY = "USER_ID";

	/** 用户ID的SESSION */
	public static final String SESSION_USER_KEY = "USER";

	/**
	 * 去除一个客户端SESSION
	 * 
	 * @param sessionId
	 * @return
	 */
	public void removeClientSession(String sessionId);

	/**
	 * 获取用户身份ID
	 * 
	 * @param sessionId
	 * @return
	 */
	public Long getUserId(String sessionId);
	
	public ClientSession createClientSessionFirst();
	
	public ClientSession getClientSession(String sessionId);

}
