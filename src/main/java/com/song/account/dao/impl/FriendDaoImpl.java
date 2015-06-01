package com.song.account.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.song.account.dao.Database.Account;
import com.song.account.dao.Database.FriendF;
import com.song.account.dao.FriendDao;
import com.song.account.entity.Friend;
import com.song.commons.dao.BasicDao;
import com.song.commons.dao.DaoUtils;

public class FriendDaoImpl extends BasicDao<Friend> implements FriendDao {

	@Override
	protected Friend rowMapper(ResultSet rs, int rowNum) throws SQLException {
		Friend f = new Friend();
		f.setAddTime(rs.getTimestamp(FriendF.ADD_TIME.name()));
		f.setFriendUserId(rs.getLong(FriendF.FRIEND_USER_ID.name()));
		f.setUserId(rs.getLong(FriendF.USER_ID.name()));
		return f;
	}

	@Override
	protected String getFields(String tableName) {
		String tabPoint = null;
		if (tableName == null || "".equals(tableName)) {
			tabPoint = "";
		} else {
			tabPoint = tableName + ".";
		}

		StringBuffer fields = new StringBuffer();
		fields.append(tabPoint).append(FriendF.ADD_TIME).append(",");
		fields.append(tabPoint).append(FriendF.FRIEND_USER_ID).append(",");
		fields.append(tabPoint).append(FriendF.USER_ID).append("");
		return fields.toString();
	}

	@Override
	protected String getParamMarks() {
		return "?,?,?";
	}

	@Override
	protected Object[] getParams(Friend t) {
		return new Object[] { t.getAddTime(), t.getFriendUserId(),
				t.getUserId() };
	}

	@Override
	protected List<Object> getParams(Friend t, StringBuffer sqlWhere) {
		if (t == null) {
			return new ArrayList<Object>();
		}
		List<Object> params = new ArrayList<Object>();
		sqlWhere.append(" where 1=1 ");
		if (t.getUserId() != null) {
			sqlWhere.append(" and ").append(FriendF.USER_ID).append("=?");
			params.add(t.getUserId());
		}
		return params;
	}

	@Override
	protected void init(Friend t) {
		t.init();
	}

	@Override
	public int inster(Friend friend) {
		String sql = DaoUtils.insertSql(Account.ACC_FRIEND, getFields(null),
				getParamMarks());
		this.init(friend);
		Object[] params = getParams(friend);
		return this.addEntity(sql.toString(), params);
	}

	@Override
	public Friend queryById(long userId, long friendUserId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select ").append(getFields(null));
		sql.append(" from ").append(Account.ACC_FRIEND).append(" where ");
		sql.append(FriendF.USER_ID).append("=? and ");
		sql.append(FriendF.FRIEND_USER_ID).append("=?");

		return this.getEntity(sql.toString(), new Object[] { userId,
				friendUserId });
	}

	@Override
	public List<Friend> queryFriendList(long userId) {
		StringBuffer sqlList = new StringBuffer();
		sqlList.append("select ").append(getFields(null));
		sqlList.append(" from ").append(Account.ACC_FRIEND).append(" where ");
		sqlList.append(FriendF.USER_ID).append("=?");

		Object[] params = new Object[] { userId };
		return this.getEntityList(sqlList.toString(), params);
	}

	@Override
	public int delById(long userId, long friendUserId) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from ").append(Account.ACC_FRIEND);
		sql.append(" where ").append(FriendF.USER_ID).append("=? and ");
		sql.append(FriendF.FRIEND_USER_ID).append("=?");

		return this.delEntity(sql.toString(), new Object[] { userId,
				friendUserId });
	}

}
