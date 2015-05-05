package com.song.account.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.song.account.dao.AppInfoDao;
import com.song.account.dao.Database.AppInfoF;
import com.song.account.entity.AppInfo;
import com.song.commons.Sequence;
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
		t.init();
		if (t.getAppKey() == null) {
			t.setAppKey(Sequence.getInstance().getSequence(16));
		}
	}

	@Override
	protected AppInfo rowMapper(ResultSet rs, int rowNum) throws SQLException {
		AppInfo app = new AppInfo();
		app.setAddTime(rs.getTimestamp(AppInfoF.ADD_TIME.name()));
		app.setAppKey(rs.getString(AppInfoF.APP_KEY.name()));
		app.setAppSecret(rs.getString(AppInfoF.APP_SECRET.name()));
		return app;
	}

	@Override
	public int insert(AppInfo appInfo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public AppInfo queryById(String appKey) {
		// TODO Auto-generated method stub
		return null;
	}
}
