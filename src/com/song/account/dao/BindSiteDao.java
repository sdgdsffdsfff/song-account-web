package com.song.account.dao;

import java.util.Date;
import java.util.List;

import com.song.account.entity.BindSite;
import com.song.account.entity.BindSite.SiteMark;

public interface BindSiteDao {

	/**
	 * 数据插入
	 * @param bindSite
	 * @return
	 */
	int insert(BindSite bindSite);
	
	/**
	 * 查询用户绑定的平台
	 * @param userId
	 * @return
	 */
	List<BindSite> queryListByUserId(Long userId);
	
	/**
	 * 根据主键查询
	 * @param bindSiteId
	 * @return
	 */
	BindSite queryById(Long bindSiteId);
	
	/**
	 * 查询第三方平台账号
	 * @param siteMark
	 * @param openId
	 * @return
	 */
	BindSite queryByOpenId(SiteMark siteMark, String openId);
	
	/**
	 * 查询用户绑定的第三方平台
	 * @param siteMark
	 * @param userId
	 * @return
	 */
	BindSite queryByUserId(SiteMark siteMark, Long userId);
	
	/**
	 * 删除绑定关系
	 * @param bindSiteId
	 * @return
	 */
	int deletById(Long bindSiteId);
	
	/**
	 * 修改accessToken等信息
	 * @param bindSiteId
	 * @param accessToken
	 * @param nickName
	 * @param photoPath
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	int updateToken(Long bindSiteId, String accessToken, String nickName,
			String photoPath, Date startDate, Date endDate);
}
