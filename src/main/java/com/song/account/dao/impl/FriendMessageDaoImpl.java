package com.song.account.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.song.account.dao.Database.Account;
import com.song.account.dao.Database.FriendMessageF;
import com.song.account.dao.FriendMessageDao;
import com.song.account.entity.FriendMessage;
import com.song.commons.dao.BasicDao;
import com.song.commons.dao.DaoUtils;

public class FriendMessageDaoImpl extends BasicDao<FriendMessage> implements
		FriendMessageDao {

	@Override
	protected FriendMessage rowMapper(ResultSet rs, int rowNum)
			throws SQLException {
		FriendMessage fm = new FriendMessage();
		fm.setAddTime(rs.getTimestamp(FriendMessageF.ADD_TIME.name()));
		fm.setFayId(rs.getLong(FriendMessageF.FAY_ID.name()));
		fm.setFromUserId(rs.getLong(FriendMessageF.FROM_USER_ID.name()));
		fm.setMessage(rs.getString(FriendMessageF.MESSAGE.name()));
		fm.setFmeId(rs.getLong(FriendMessageF.FME_ID.name()));
		fm.setToUserId(rs.getLong(FriendMessageF.TO_USER_ID.name()));
		return fm;
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
		fields.append(tabPoint).append(FriendMessageF.ADD_TIME).append(",");
		fields.append(tabPoint).append(FriendMessageF.FAY_ID).append(",");
		fields.append(tabPoint).append(FriendMessageF.FROM_USER_ID).append(",");
		fields.append(tabPoint).append(FriendMessageF.MESSAGE).append(",");
		fields.append(tabPoint).append(FriendMessageF.FME_ID).append(",");
		fields.append(tabPoint).append(FriendMessageF.TO_USER_ID).append("");
		return fields.toString();
	}

	@Override
	protected String getParamMarks() {
		return "?,?,?,?,?,?";
	}

	@Override
	protected Object[] getParams(FriendMessage t) {
		return new Object[] { t.getAddTime(), t.getFayId(), t.getFromUserId(),
				t.getMessage(), t.getFmeId(), t.getToUserId() };
	}

	@Override
	protected List<Object> getParams(FriendMessage t, StringBuffer sqlWhere) {
		if (t == null) {
			return new ArrayList<Object>();
		}
		List<Object> params = new ArrayList<Object>();
		sqlWhere.append(" where 1=1 ");
		if (t.getFayId() != null) {
			sqlWhere.append(" and ").append(FriendMessageF.FAY_ID).append("=?");
			params.add(t.getFayId());
		}
		return params;
	}

	@Override
	protected void init(FriendMessage t) {
		t.init();
		if (t.getFmeId() == null) {
			t.setFmeId(this.getId(Account.ACC_FRIEND_MESSAGE));
		}
	}

	@Override
	public int inster(FriendMessage fm) {
		String sql = DaoUtils.insertSql(Account.ACC_FRIEND_MESSAGE,
				getFields(null), getParamMarks());
		this.init(fm);
		Object[] params = getParams(fm);
		return this.addEntity(sql.toString(), params);
	}

	@Override
	public FriendMessage queryById(long fmeId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select ").append(getFields(null));
		sql.append(" from ").append(Account.ACC_FRIEND_MESSAGE)
				.append(" where ");
		sql.append(FriendMessageF.FME_ID).append("=?");

		return this.getEntity(sql.toString(), fmeId);
	}

	@Override
	public List<FriendMessage> queryByFayId(long fayId) {
		FriendMessage fm = new FriendMessage();
		fm.setFayId(fayId);

		StringBuffer sql = new StringBuffer();
		sql.append("select ").append(getFields(null));
		sql.append(" from ").append(Account.ACC_FRIEND_MESSAGE);

		StringBuffer sqlWhere = new StringBuffer();
		List<Object> params = getParams(fm, sqlWhere);
		sql.append(sqlWhere);
		sql.append(" order by ").append(FriendMessageF.ADD_TIME)
				.append(" desc ");

		return this.getEntityList(sql.toString(), params.toArray());
	}

	// private int update(FriendMessage fm) {
	// if (fm.getFmeId() == null) {
	// throw new IllegalArgumentException();
	// }
	//
	// List<Object> params = new ArrayList<Object>();
	// StringBuffer sql = new StringBuffer();
	// sql.append("update ").append(Account.ACC_FRIEND_MESSAGE);
	// sql.append(" set ").append(FriendMessageF.FME_ID).append("=")
	// .append(FriendMessageF.FME_ID);
	// if (fm.getMessage() != null) {
	// sql.append(",").append(FriendMessageF.MESSAGE).append("=?");
	// params.add(fm.getMessage());
	// }
	//
	// sql.append(" where ").append(FriendMessageF.FME_ID).append("=?");
	// params.add(fm.getFmeId());
	//
	// return updateEntity(sql.toString(), params.toArray());
	// }

}
