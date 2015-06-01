package com.song.account.dao;

import com.song.account.entity.AppInfo;

public interface AppInfoDao {

	/**
	 * 数据插入
	 * @param appInfo
	 * @return
	 */
	int insert(AppInfo appInfo);
	
	/**
	 * 根据主键查询
	 * @param appKey
	 * @return
	 */
	AppInfo queryById(String appKey);
}
