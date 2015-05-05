package com.song.account.service.impl;

import com.song.account.dao.AppInfoDao;
import com.song.account.entity.AppInfo;
import com.song.account.service.AppInfoService;

/**
 * APP应用管理
 * @author 张松
 *
 */
public class AppInfoServiceImpl implements AppInfoService {

	private AppInfoDao appInfoDao;

	public void setAppInfoDao(AppInfoDao appInfoDao) {
		this.appInfoDao = appInfoDao;
	}

	@Override
	public AppInfo getAppInfoById(String appKey) {
		return appInfoDao.queryById(appKey);
	}
}
