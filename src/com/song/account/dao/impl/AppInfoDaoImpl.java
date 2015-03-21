package com.song.account.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.song.account.dao.AppInfoDao;
import com.song.account.entity.AppInfo;
import com.song.commons.dao.BasicDao;

public class AppInfoDaoImpl extends BasicDao<AppInfo> implements AppInfoDao {

	@Override
	protected String getFields(String tableName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected String getParamMarks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Object[] getParams(AppInfo t) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected List<Object> getParams(AppInfo t, StringBuffer sqlWhere) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void init(AppInfo t) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected AppInfo rowMapper(ResultSet rs, int rowNum) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(AppInfo appInfo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AppInfo queryById(Long appKey) {
		// TODO Auto-generated method stub
		return null;
	}
}
