package com.song.account.dao;

import com.song.account.entity.User;
import com.song.commons.PageInfo;

/**
 * 用户数据持久层
 * @author 张松
 *
 */
public interface UserDao {
	/**
	 * 获取主键
	 * @return
	 */
	public long getId(); 
	
	/**
	 * 插入用户数据
	 * 
	 * @param user
	 * @return
	 */
	public int insert(User user);

	/**
	 * 根据ID查询用户数据
	 * 
	 * @param userId
	 * @return
	 */
	public User queryById(Long userId);

	/**
	 * 根据账号查询用户数据
	 * 
	 * @param account
	 * @return
	 */
	public User queryByAccount(String account);

	/**
	 * 查询用户的密码
	 * 
	 * @param account
	 * @return
	 */
	public String queryPassByAccount(String account);

	/**
	 * 根据账号，计算拥有该账号用户个数。
	 * 
	 * @param account
	 * @return
	 */
	public int countByAccount(String account);

	/**
	 * 根据昵称，计算使用该昵称用户个数。
	 * 
	 * @param nick
	 * @return
	 */
	public int countByNick(String nick);

	/**
	 * 查询
	 * @param keyword
	 * @param currPage
	 * @param pageSize
	 * @return
	 */
	public PageInfo<User> queryListByKeyword(String keyword, int currPage, int pageSize);

	/**
	 * 修改密码
	 * @param userId
	 * @param newPsw
	 * @return
	 */
	public int updatePsw(Long userId, String newPsw);

	/**
	 * 修改头像
	 * @param userId
	 * @param photoPath
	 */
	public int updatePhoto(Long userId, String photoPath);

	/**
	 * 修改用户信息
	 * @param userId
	 * @param nickName
	 * @param userName
	 * @param sex
	 * @param birthdayYear
	 * @param birthdayMonth
	 * @param birthdayDay
	 * @param eaddress
	 * @param summary
	 */
	public int update(Long userId, String nickName, String userName,
			Integer sex, int birthdayYear, int birthdayMonth, int birthdayDay,
			String eaddress, String summary);

	/**
	 * 修改用户卖家标识
	 * @param applicantId
	 * @param sellerFlag
	 */
	public int updateSellerFlag(Long applicantId, boolean sellerFlag);
	
	/**
	 * 修改用户EMAIL
	 * @param userId
	 * @param email
	 * @return
	 */
	public int updateEmail(Long userId, String email);
	
	/**
	 * 修改用户激活EMAIL
	 * @param userId
	 * @param enEmail
	 * @return
	 */
	public int updateEnEmail(Long userId, String enEmail);

	/**
	 * 修改用户邮箱验证码
	 * @param userId
	 * @param emIcId
	 */
	public int updateEmIcId(Long userId, Long emIcId);

	/**
	 * 根据enemail查询
	 * @param enEmail
	 * @return
	 */
	public User queryByEnEmail(String enEmail);
}
