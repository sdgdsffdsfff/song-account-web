package com.song.account.service.impl;

import com.song.account.entity.User;
import com.song.account.service.SSOAuthService;
import com.song.account.service.UserService;
import com.song.commons.Sequence;
import com.song.commons.StringUtil;
import com.song.commons.client.ClientManager;
import com.song.commons.client.ClientSession;

public class SSOAuthServiceImpl implements SSOAuthService {

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public ClientSession createClientSessionFirst() {
		String sessionId = Sequence.getInstance().getSequence(28);
		ClientSession cSession = new ClientSession(sessionId);
		ClientManager.getInstance().addSession(cSession);
		return cSession;
	}
	
	@Override
	public String createClientSession() {
		ClientSession cs = createClientSessionFirst();
		return cs.getId();
	}

	@Override
	public boolean isClientSessionId(String sessionId) {
		ClientSession cs = this.getClientSession(sessionId);
		if (cs == null) {
			return false;
		}
		return true;
	}

	@Override
	public void removeClientSession(String sessionId) {
		if (sessionId != null) {
			ClientManager.getInstance().delSession(sessionId);
		}
	}

	@Override
	public String login(String account, String password, String sessionId) {
		User u = userService.checkLogin(account, password);
		ClientSession cSession = null;
		if (StringUtil.isEmptyOrNull(sessionId)) {
			cSession = this.createClientSessionFirst();
			sessionId = cSession.getId();
		} else {
			cSession = this.getClientSession(sessionId);
			if (cSession == null) {
				cSession = this.createClientSessionFirst();
				sessionId = cSession.getId();
			}
		}
		if (u != null) {
			cSession.setAttribute(SESSION_USERID_KEY, u.getUserId());
			cSession.setAttribute(SESSION_USER_KEY, u);
		}
		return sessionId;
	}

	@Override
	public void logout(String sessionId) {
		this.removeClientSession(sessionId);
	}

	@Override
	public User getAuth(String sessionId) {
		ClientSession session = this.getClientSession(sessionId);
		User user = null;
		if (session != null) {
			user = (User) session.getAttribute(SESSION_USER_KEY);
		}
		return user;
	}

	@Override
	public Long getUserId(String sessionId) {
		ClientSession session = this.getClientSession(sessionId);
		Long userId = null;
		if (session != null) {
			userId = (Long) session.getAttribute(SESSION_USERID_KEY);
		}
		return userId;
	}

	@Override
	public ClientSession getClientSession(String sessionId) {
		return ClientManager.getInstance().getSession(sessionId);
	}

}
