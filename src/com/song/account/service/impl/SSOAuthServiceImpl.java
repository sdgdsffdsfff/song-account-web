package com.song.account.service.impl;

import java.util.Date;

import com.song.account.dao.ClientSessionDao;
import com.song.account.entity.User;
import com.song.account.service.ErrService;
import com.song.account.service.SSOAuthService;
import com.song.account.service.UserService;
import com.song.commons.Sequence;
import com.song.commons.StringUtil;
import com.song.commons.client.ClientManager;
import com.song.commons.client.ClientSession;
import com.song.commons.service.ServiceException;

public class SSOAuthServiceImpl implements SSOAuthService {

	private UserService userService;
	private ClientSessionDao clientSessionDao;

	public void setClientSessionDao(ClientSessionDao clientSessionDao) {
		this.clientSessionDao = clientSessionDao;
	}

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

		// 保存到数据库
		com.song.account.entity.ClientSession cs = clientSessionDao
				.queryById(sessionId);
		if (cs == null) {
			cs = new com.song.account.entity.ClientSession();
			cs.setClientId(sessionId);
			cs.setUserId(u.getUserId());
			cs.setCreationTime(new Date(cSession.getCreationTime()));
			cs.setLastAccessedTime(new Date(cSession.getLastAccessedTime()));
			clientSessionDao.inster(cs);
		}
		return sessionId;
	}

	@Override
	public void logout(String sessionId) {
		this.removeClientSession(sessionId);
		clientSessionDao.delById(sessionId);
	}

	@Override
	public User getAuth(String sessionId) {
		ClientSession session = this.getClientSession(sessionId);
		User user = null;
		if (session != null) {
			user = (User) session.getAttribute(SESSION_USER_KEY);
		}
		if (user == null) {
			throw new ServiceException(ErrService.SSOAuthE.ACC_103_001,
					"用户登入失效");
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
		if (userId == null) {
			throw new ServiceException(ErrService.SSOAuthE.ACC_103_001,
					"用户登入失效");
		}
		return userId;
	}

	@Override
	public ClientSession getClientSession(String sessionId) {
		ClientSession cs = ClientManager.getInstance().getSession(sessionId);
		if (cs == null) {
			// 从数据库中获取
			com.song.account.entity.ClientSession dbCs = clientSessionDao
					.queryById(sessionId);
			if (dbCs != null && dbCs.getValidFlag()) {
				cs = new ClientSession(sessionId);
				cs.setCreationTime(dbCs.getCreationTime());
				cs.setLastAccessedTime(dbCs.getLastAccessedTime());
				User u = userService.getUserById(dbCs.getUserId());
				if (u != null) {
					cs.setAttribute(SESSION_USERID_KEY, u.getUserId());
					cs.setAttribute(SESSION_USER_KEY, u);
				}
				// 加入缓存
				ClientManager.getInstance().addSession(cs);
			}
		}
		// cs!=null 修改数据库中的访问时间
		if (cs != null) {
			clientSessionDao.updateLastAccessedTime(sessionId, new Date());
		}
		return cs;
	}

	@Override
	public String getRongToken(String sessionId, String resAccountUri) {
		Long userId = getUserId(sessionId);
		if (userId == null) {
			return null;
		}
		return userService.getRongToken(userId, true, resAccountUri);
	}

}
