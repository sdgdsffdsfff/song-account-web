package com.song.account.service.impl;

import io.rong.im.api.ApiRongIM;
import io.rong.im.api.models.UserToken;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import weibo4j.Users;
import weibo4j.model.WeiboException;

import com.qq.connect.QQConnectException;
import com.qq.connect.api.qzone.UserInfo;
import com.qq.connect.javabeans.qzone.UserInfoBean;
import com.song.account.dao.BindSiteDao;
import com.song.account.dao.UserDao;
import com.song.account.entity.BindSite;
import com.song.account.entity.BindSite.SiteMark;
import com.song.account.entity.User;
import com.song.account.service.ErrService;
import com.song.account.service.UserService;
import com.song.commons.PageInfo;
import com.song.commons.PasswordMD;
import com.song.commons.StringUtil;
import com.song.commons.service.ServiceException;

public class UserServiceImpl implements UserService {

	private static Logger logger = Logger.getLogger(UserServiceImpl.class);

	private UserDao userDao;

	private BindSiteDao bindSiteDao;

	public void setBindSiteDao(BindSiteDao bindSiteDao) {
		this.bindSiteDao = bindSiteDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public User checkLogin(String account, String password) {
		password = PasswordMD.md5(password);
		User u = userDao.queryByAccount(account);
		if (u == null) {
			u = userDao.queryByEnEmail(account);
		}
		if (u != null && password.equals(u.getPassword())) {
			return u;
		} else {
			throw new ServiceException(ErrService.UserS.ACC_109,
					"用户账号或者密码错误");
		}
	}

	@Override
	public PageInfo<User> getUserList(String nickName, int currPage,
			int pageSize) {
		return userDao.queryListByKeyword(nickName, currPage, pageSize);
	}

	@Override
	public PageInfo<User> getUserListOnLine(int currPage, int pageSize) {

		return null;
	}

	@Override
	public User register(String account, String password, String nick)
			throws ServiceException {
		if (StringUtil.isEmptyOrNull(password)
				|| StringUtil.isEmptyOrNull(nick)) {
			throw new IllegalArgumentException();
		}

		if (!StringUtil.isEmptyOrNull(account)) {
			// 验证账号格式
			if (!StringUtil.match(account, "^\\w{5,50}$")) {
				throw new ServiceException(ErrService.UserS.ACC_105, "账号：“"
						+ account + "”格式错误。");
			}
			account = account.toLowerCase();
			// 验证账号是否重复
			if (this.verifyAccountRep(account)) {
				throw new ServiceException(ErrService.UserS.ACC_101, "账号：“"
						+ account + "”已经被使用。");
			}
		}

		// 验证昵称格式
		if (!StringUtil.match(nick, "^.{1,12}$")) {
			throw new ServiceException(ErrService.UserS.ACC_106, "昵称：“"
					+ nick + "”格式错误。");
		}
		// 验证密码格式
		if (!StringUtil.match(password, "^.{6,20}$")) {
			throw new ServiceException(ErrService.UserS.ACC_107, "密码：“"
					+ password + "”格式错误。");
		}
		// 验证昵称是否重复
		if (this.verifyNickRep(nick)) {
			throw new ServiceException(ErrService.UserS.ACC_102, "昵称：“"
					+ nick + "”已经被使用。");
		}

		User user = new User();
		user.setAccount(account);
		// 加密处理
		password = PasswordMD.md5(password);
		user.setPassword(password);
		user.setNickName(nick);
		user.setAddTime(new Date());
		return this.addUser(user);
	}

	@Override
	public boolean verifyAccountRep(String account) {
		int n = userDao.countByAccount(account);
		if (n != 0) {
			return true;
		}
		return false;
	}

	@Override
	public boolean verifyNickRep(String nick) {
		int n = userDao.countByNick(nick);
		if (n != 0) {
			return true;
		}
		return false;
	}

	@Override
	public User getUserByAccount(String account) {
		return userDao.queryByAccount(account);
	}

	@Override
	public User getUserById(Long userId) {
		return userDao.queryById(userId);
	}

	@Override
	public void editUserPasswore(Long userId, String oldPsw, String newPsw) {
		// 验证密码格式
		if (!StringUtil.match(newPsw, "^.{6,20}$")) {
			throw new ServiceException(ErrService.UserS.ACC_107, "密码：“"
					+ newPsw + "”格式错误。");
		}

		// 密码MD5加密
		oldPsw = PasswordMD.md5(oldPsw);
		User user = userDao.queryById(userId);
		if (!user.getPassword().equals(oldPsw)) {
			throw new ServiceException(ErrService.UserS.ACC_103, "用户：“"
					+ userId + "”原始密码错误。");
		}

		newPsw = PasswordMD.md5(newPsw);
		userDao.updatePsw(userId, newPsw);
	}

	@Override
	public void editUserPhoto(Long userId, String photoPath) {
		userDao.updatePhoto(userId, photoPath);
	}

	@Override
	public void editUserBasic(Long userId, String nickName, String userName,
			Integer sex, int birthdayYear, int birthdayMonth, int birthdayDay,
			String summary, String qqId, String wangwangId) {
		// 数据不能为空
		if (userId <= 0 || nickName.trim().equals("")
				|| userName.trim().equals("")) {
			throw new IllegalArgumentException();
		}
		// 验证昵称格式
		if (!StringUtil.match(nickName, "^.{1,12}$")) {
			throw new ServiceException(ErrService.UserS.ACC_106, "昵称：“"
					+ nickName + "”格式错误。");
		}
		// 验证生日格式
		Calendar calendar = Calendar.getInstance();
		try {
			calendar.set(birthdayYear, birthdayMonth - 1, birthdayDay);
		} catch (Exception e) {
			throw new ServiceException(ErrService.UserS.ACC_104, "生日：“"
					+ birthdayYear + "年" + birthdayMonth + "月" + birthdayDay
					+ "日”，格式错误。", e);
		}
		// 验证昵称
		User user = userDao.queryById(userId);
		if (!user.getNickName().equals(nickName)) {
			if (this.verifyNickRep(nickName)) {
				throw new ServiceException(ErrService.UserS.ACC_102, "昵称：“"
						+ nickName + "”已经被使用。");
			}
		}

		JSONObject eaddress = new JSONObject();
		if (!StringUtil.isEmptyOrNull(qqId)) {
			eaddress.put("qqId", qqId);
		}
		if (!StringUtil.isEmptyOrNull(wangwangId)) {
			eaddress.put("wangwangId", wangwangId);
		}

		userDao.update(userId, nickName, userName, sex, birthdayYear,
				birthdayMonth, birthdayDay, eaddress.toString(), summary);
	}

	@Override
	public void editUserEmail(Long userId, String email) {
		if (email == null || email.equals("")) {
			throw new ServiceException(ErrService.UserS.ACC_102,
					"电子邮箱格式错误。");
		}
		User u = userDao.queryByEnEmail(email);
		if (u != null) {
			throw new ServiceException(ErrService.UserS.ACC_102,
					"该电子邮箱已经被激活。");
		}
		User user = userDao.queryById(userId);
		if (!email.equals(user.getEmail())) {
			userDao.updateEmail(userId, email);
		}
	}

	@Override
	public BindSite checkLogin(BindSite.SiteMark siteMark, String openId,
			String accessToken, long tokenExpireIn) {
		// 第三方用户信息在当前平台保存的信息
		BindSite bs = bindSiteDao.queryByOpenId(siteMark, openId);
		// 获取第三方用户信息
		BindSite bsOther = UserServiceImpl.getBindSite(siteMark, openId,
				accessToken);

		if (bs != null) {
			// 更新token等信息
			bsOther.setBindSiteId(bs.getBindSiteId());
			this.changeToken(bsOther, tokenExpireIn);
		} else {
			// 注册用户
			User user = register(bsOther.getNickName(), bsOther.getPhotoPath());
			// 绑定平台
			bs = bindSiteUser(user.getUserId(), siteMark, openId, accessToken,
					tokenExpireIn, true);
		}
		return bs;
	}

	private User register(String nickName, String photoPath) {
		if (StringUtil.isEmptyOrNull(nickName)) {
			throw new IllegalArgumentException();
		}

		long userId = userDao.getId();
		User user = new User();
		user.setPhotoPath(photoPath);
		user.setNickName(nickName + userId);
		user.setAddTime(new Date());
		user.setUserId(userId);
		return this.addUser(user);
	}

	private User addUser(User user) {
		userDao.insert(user);
		// UserStatistics userSta = new UserStatistics();
		// userSta.setUserId(user.getUserId());
		// userStatisticsDao.insert(userSta);
		return user;
	}

	/**
	 * 获取第三方用户信息
	 * 
	 * @param siteMark
	 * @param openId
	 * @param accessToken
	 * @return
	 */
	public static BindSite getBindSite(SiteMark siteMark, String openId,
			String accessToken) {
		BindSite bs = new BindSite();
		bs.setSiteMark(siteMark);
		bs.setOpenId(openId);
		bs.setAccessToken(accessToken);
		String nickName = null;
		if (SiteMark.QQ.equals(siteMark)) {
			UserInfo ui = new UserInfo(accessToken, openId);
			UserInfoBean uib = null;
			try {
				uib = ui.getUserInfo();
			} catch (QQConnectException e) {
				logger.error(e);
				throw new ServiceException(ErrService.UserS.ACC_108,
						"QQ_API调用异常", "第三方平台异常，请从新尝试！");
			}
			nickName = uib.getNickname();
		} else if (SiteMark.SINA.equals(siteMark)) {
			Users um = new Users();
			um.client.setToken(accessToken);
			try {
				weibo4j.model.User user = um.showUserById(openId);
				nickName = user.getScreenName();
			} catch (WeiboException e) {
				logger.error(e);
				throw new ServiceException(ErrService.UserS.ACC_108,
						"SINA_API调用异常", "第三方平台异常，请从新尝试！");
			}
		}
		bs.setNickName(nickName);
		return bs;
	}

	@Override
	public BindSite bindSiteUser(Long userId, SiteMark siteMark, String openId,
			String accessToken, long tokenExpireIn, boolean regFlag) {
		if (userId == null || userId <= 0) {
			throw new IllegalArgumentException();
		}

		BindSite bs3 = bindSiteDao.queryByOpenId(siteMark, openId);
		if (bs3 != null) {
			throw new ServiceException(ErrService.UserS.ACC_108,
					"当前第三方账号已经被其他账号绑定！");
		}
		// 当前用户是否已经绑定
		BindSite bs = bindSiteDao.queryByUserId(siteMark, userId);
		// 获取第三方用户信息
		BindSite bsOther = UserServiceImpl.getBindSite(siteMark, openId,
				accessToken);

		if (bs != null) {
			// 更新token等信息
			bsOther.setBindSiteId(bs.getBindSiteId());
			this.changeToken(bsOther, tokenExpireIn);
		} else {
			// 绑定平台
			bs = new BindSite();
			bs.setAccessToken(accessToken);
			bs.setOpenId(openId);
			bs.setNickName(bsOther.getNickName());
			bs.setRegFlag(regFlag);
			bs.setSiteMark(siteMark);
			bs.setUserId(userId);
			bs.setStartDate(new Date());
			bs.setEndDate(new Date(bs.getStartDate().getTime() + tokenExpireIn));
			bs.setPhotoPath(bsOther.getPhotoPath());
			bindSiteDao.insert(bs);
		}
		return bs;
	}

	private void changeToken(BindSite bsOther, long tokenExpireIn) {
		bsOther.setStartDate(new Date());
		bindSiteDao.updateToken(bsOther.getBindSiteId(), bsOther
				.getAccessToken(), bsOther.getNickName(), bsOther
				.getPhotoPath(), bsOther.getStartDate(), new Date(bsOther
				.getStartDate().getTime() + tokenExpireIn));
	}

	@Override
	public void cancelBindSite(Long bindSiteId) {
		BindSite bs = bindSiteDao.queryById(bindSiteId);
		if (bs == null) {
			throw new ServiceException(ErrService.UserS.ACC_108,
					"已经解绑成功，不允许再次操作！");
		}
		if (bs.getRegFlag()) {
			throw new ServiceException(ErrService.UserS.ACC_108,
					"第三方账号注册，不允许解除绑定关系！");
		}
		bindSiteDao.deletById(bindSiteId);
	}

	@Override
	public List<BindSite> getBindSiteListByUser(Long userId) {
		return bindSiteDao.queryListByUserId(userId);
	}

	@Override
	public String getRongToken(long userId, boolean isNew, String resAccountUri) {
		User user = this.getUserById(userId);
		if (user == null) {
			return null;
		}
		if (user.getRongToken() != null && !isNew) {
			return user.getRongToken();
		}
		
		String imgUrl = user.getPhotoPath();
		if (imgUrl != null && !imgUrl.startsWith("http://")) {
			imgUrl = resAccountUri + imgUrl;
		}
	
		UserToken ut = null;
		try {
			String appKey = "0vnjpoadnxp5z";
			String appSecret = "3UH04WirdR69D";
			ApiRongIM.init(appKey, appSecret);
			ut = ApiRongIM.getToken(user.getUserId() + "",
					user.getNickName(), imgUrl);
			user.setRongToken(ut.getToken());
		} catch (Exception e) {
			logger.error("userId=" + user.getUserId(), e);
		}
		// 修改数据库
		userDao.updateRongToken(userId, user.getRongToken());

		return user.getRongToken();
	}

}
