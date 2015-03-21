package com.song.account.service;

import java.util.List;

import com.song.account.entity.BindSite;
import com.song.account.entity.User;
import com.song.commons.PageInfo;
import com.song.commons.service.ServiceException;

/**
 * 用户业务逻辑
 * 
 * @author 张松
 * 
 */
public interface UserService {
	/**
	 * 注册用户信息
	 * 
	 * @param account
	 * @param password
	 * @param nick
	 * @return
	 * @throws ServiceException
	 */
	public User register(String account, String password, String nick)
			throws ServiceException;

	/**
	 * 验证用户登入信息
	 * 
	 * @param account
	 * @param password
	 * @return
	 */
	public User checkLogin(String account, String password);

	/**
	 * 获取注册用户信息
	 * 
	 * @param nickName
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public PageInfo<User> getUserList(String nickName, int currPage,
			int pageSize);

	/**
	 * 验证账号是否重复
	 * 
	 * @param account
	 * @return
	 */
	public boolean verifyAccountRep(String account);

	/**
	 * 验证昵称是否重复
	 * 
	 * @param nick
	 * @return
	 */
	public boolean verifyNickRep(String nick);

	/**
	 * 通过账号获取
	 * 
	 * @param actount
	 * @return
	 */
	public User getUserByAccount(String account);

	/**
	 * 获取在线用户的信息（Application范围）
	 * 
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public PageInfo<User> getUserListOnLine(int currPage, int pageSize);

	/**
	 * 根据主键获取
	 * 
	 * @param userId
	 * @return
	 */
	public User getUserById(Long userId);

	/**
	 * 编辑用户基本信息
	 * 
	 * @param userId
	 * @param nickName
	 * @param userName
	 * @param sex
	 * @param birthdayYear
	 * @param birthdayMonth
	 * @param birthdayDay
	 * @param eaddress
	 * @param qqId
	 * @param wangwangId
	 */
	public void editUserBasic(Long userId, String nickName, String userName,
			Integer sex, int birthdayYear, int birthdayMonth, int birthdayDay,
			String summary, String qqId, String wangwangId);

	/**
	 * 修改用户头像
	 * 
	 * @param userId
	 * @param photoPath
	 */
	public void editUserPhoto(Long userId, String photoPath);

	/**
	 * 修改用户登入密码
	 * 
	 * @param userId
	 * @param oldPsw
	 * @param newPsw
	 */
	public void editUserPasswore(Long userId, String oldPsw, String newPsw);

	/**
	 * 编辑用户Email
	 * 
	 * @param userId
	 * @param email
	 */
	public void editUserEmail(Long userId, String email);

	/**
	 * 第三方账号登入
	 * @param siteMark
	 * @param openId
	 * @param accessToken
	 * @param tokenExpireIn
	 * @return
	 */
	public BindSite checkLogin(BindSite.SiteMark siteMark, String openId,
			String accessToken, long tokenExpireIn);

	/**
	 * 绑定第三方平台用户
	 * @param userId
	 * @param siteMark
	 * @param openId
	 * @param accessToken
	 * @param tokenExpireIn
	 * @param regFlag
	 * @return
	 */
	public BindSite bindSiteUser(Long userId, BindSite.SiteMark siteMark,
			String openId, String accessToken, long tokenExpireIn,
			boolean regFlag);

	/**
	 * 取消绑定
	 * 
	 * @param bindSiteId
	 */
	public void cancelBindSite(Long bindSiteId);
	
	/**
	 * 查询用户所绑定的平台
	 * @param userId
	 */
	public List<BindSite> getBindSiteListByUser(Long userId);
}
