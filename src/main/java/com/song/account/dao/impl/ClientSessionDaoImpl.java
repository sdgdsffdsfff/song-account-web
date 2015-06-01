package com.song.account.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.song.account.dao.ClientSessionDao;
import com.song.account.dao.Database.Account;
import com.song.account.dao.Database.ClientSessionF;
import com.song.account.entity.ClientSession;
import com.song.commons.dao.BasicDao;
import com.song.commons.dao.DaoUtils;

public class ClientSessionDaoImpl extends BasicDao<ClientSession> implements
		ClientSessionDao {

	@Override
	protected ClientSession rowMapper(ResultSet rs, int rowNum)
			throws SQLException {
		ClientSession cs = new ClientSession();
		cs.setClientId(rs.getString(ClientSessionF.CLIENT_ID.name()));
		cs.setCreationTime(rs.getTimestamp(ClientSessionF.CREATION_TIME.name()));
		cs.setLastAccessedTime(rs
				.getTimestamp(ClientSessionF.LAST_ACCESSED_TIME.name()));
		cs.setUserId(rs.getLong(ClientSessionF.USER_ID.name()));
		cs.setValidFlag(rs.getBoolean(ClientSessionF.VALID_FLAG.name()));
		return cs;
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
		fields.append(tabPoint).append(ClientSessionF.CLIENT_ID).append(",");
		fields.append(tabPoint).append(ClientSessionF.CREATION_TIME)
				.append(",");
		fields.append(tabPoint).append(ClientSessionF.LAST_ACCESSED_TIME)
				.append(",");
		fields.append(tabPoint).append(ClientSessionF.USER_ID).append(",");
		fields.append(tabPoint).append(ClientSessionF.VALID_FLAG).append("");
		return fields.toString();
	}

	@Override
	protected String getParamMarks() {
		return "?,?,?,?,?";
	}

	@Override
	protected Object[] getParams(ClientSession t) {
		return new Object[] { t.getClientId(), t.getCreationTime(),
				t.getLastAccessedTime(), t.getUserId(), t.getValidFlag() };
	}

	@Override
	protected List<Object> getParams(ClientSession t, StringBuffer sqlWhere) {
		if (t == null) {
			return new ArrayList<Object>();
		}
		List<Object> params = new ArrayList<Object>();
		sqlWhere.append(" where 1=1 ");
		if (t.getUserId() != null) {
			sqlWhere.append(" and ").append(ClientSessionF.USER_ID)
					.append("=?");
			params.add(t.getUserId());
		}
		if (t.getValidFlag() != null) {
			sqlWhere.append(" and ").append(ClientSessionF.VALID_FLAG)
					.append("=?");
			params.add(t.getValidFlag());
		}
		return params;
	}

	@Override
	protected void init(ClientSession t) {
		t.init();
	}

	@Override
	public int inster(ClientSession cs) {
		String sql = DaoUtils.insertSql(Account.ACC_CLIENT_SESSION,
				getFields(null), getParamMarks());
		this.init(cs);
		Object[] params = getParams(cs);
		return this.addEntity(sql.toString(), params);
	}

	@Override
	public ClientSession queryById(String clientId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select ").append(getFields(null));
		sql.append(" from ").append(Account.ACC_CLIENT_SESSION)
				.append(" where ");
		sql.append(ClientSessionF.CLIENT_ID).append("=?");

		return this.getEntity(sql.toString(), clientId);
	}

	@Override
	public int delById(String clientId) {
		StringBuffer sql = new StringBuffer();
		sql.append("delete from ").append(Account.ACC_CLIENT_SESSION);
		sql.append(" where ").append(ClientSessionF.CLIENT_ID).append("=?");

		return this.delEntity(sql.toString(), clientId);
	}

	@Override
	public int updateLastAccessedTime(String clientId, Date date) {
		StringBuffer sql = new StringBuffer();
		sql.append("update ").append(Account.ACC_CLIENT_SESSION);
		sql.append(" set ").append(ClientSessionF.LAST_ACCESSED_TIME)
				.append("=?");
		sql.append(" where ").append(ClientSessionF.CLIENT_ID).append("=?");
		return this.updateEntity(sql.toString(),
				new Object[] { date, clientId });
	}

	@Override
	public int updateValidFlag(String clientId, boolean validFlag) {
		StringBuffer sql = new StringBuffer();
		sql.append("update ").append(Account.ACC_CLIENT_SESSION);
		sql.append(" set ").append(ClientSessionF.VALID_FLAG).append("=?");
		sql.append(" where ").append(ClientSessionF.CLIENT_ID).append("=?");
		return this.updateEntity(sql.toString(), new Object[] { validFlag,
				clientId });
	}

}
