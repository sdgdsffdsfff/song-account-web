package com.song.account.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.song.account.dao.Database.Account;
import com.song.account.dao.Database.FriendApplyF;
import com.song.account.dao.FriendApplyDao;
import com.song.account.entity.FriendApply;
import com.song.commons.dao.BasicDao;
import com.song.commons.dao.DaoUtils;

public class FriendApplyDaoImpl extends BasicDao<FriendApply> implements
		FriendApplyDao {

	@Override
	protected FriendApply rowMapper(ResultSet rs, int rowNum)
			throws SQLException {
		FriendApply fa = new FriendApply();
		fa.setAddTime(rs.getTimestamp(FriendApplyF.ADD_TIME.name()));
		fa.setFayId(rs.getLong(FriendApplyF.FAY_ID.name()));
		fa.setFromUserId(rs.getLong(FriendApplyF.FROM_USER_ID.name()));
		fa.setFriendMessage(rs.getString(FriendApplyF.FRIDEND_MESSAGE.name()));
		fa.setResultStatus(rs.getInt(FriendApplyF.RESULT_STATUS.name()));
		fa.setToUserId(rs.getLong(FriendApplyF.TO_USER_ID.name()));
		fa.setFromDelFlag(rs.getBoolean(FriendApplyF.FROM_DEL_FLAG.name()));
		fa.setToDelFlag(rs.getBoolean(FriendApplyF.TO_DEL_FLAG.name()));
		return fa;
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
		fields.append(tabPoint).append(FriendApplyF.ADD_TIME).append(",");
		fields.append(tabPoint).append(FriendApplyF.FAY_ID).append(",");
		fields.append(tabPoint).append(FriendApplyF.FROM_USER_ID).append(",");
		fields.append(tabPoint).append(FriendApplyF.FRIDEND_MESSAGE)
				.append(",");
		fields.append(tabPoint).append(FriendApplyF.RESULT_STATUS).append(",");
		fields.append(tabPoint).append(FriendApplyF.TO_USER_ID).append(",");
		fields.append(tabPoint).append(FriendApplyF.FROM_DEL_FLAG).append(",");
		fields.append(tabPoint).append(FriendApplyF.TO_DEL_FLAG).append("");
		return fields.toString();
	}

	@Override
	protected String getParamMarks() {
		return "?,?,?,?,?,?,?,?";
	}

	@Override
	protected Object[] getParams(FriendApply t) {
		return new Object[] { t.getAddTime(), t.getFayId(), t.getFromUserId(),
				t.getFriendMessage(), t.getResultStatus(), t.getToUserId(),
				t.getFromDelFlag(), t.getToDelFlag() };
	}

	@Override
	protected List<Object> getParams(FriendApply t, StringBuffer sqlWhere) {
		if (t == null) {
			return new ArrayList<Object>();
		}
		List<Object> params = new ArrayList<Object>();
		sqlWhere.append(" where 1=1 ");
		if (t.getFayId() != null) {
			sqlWhere.append(" and ").append(FriendApplyF.FAY_ID).append("=?");
			params.add(t.getFayId());
		}
		if (t.getFromUserId() != null) {
			sqlWhere.append(" and ").append(FriendApplyF.FROM_USER_ID)
					.append("=?");
			params.add(t.getFromUserId());
		}
		if (t.getToUserId() != null) {
			sqlWhere.append(" and ").append(FriendApplyF.TO_USER_ID)
					.append("=?");
			params.add(t.getToUserId());
		}
		return params;
	}

	@Override
	protected void init(FriendApply t) {
		t.init();
		if (t.getFayId() == null) {
			t.setFayId(this.getId(Account.ACC_FRIEND_APPLY));
		}
	}

	@Override
	public int inster(FriendApply friendApply) {
		String sql = DaoUtils.insertSql(Account.ACC_FRIEND_APPLY,
				getFields(null), getParamMarks());
		this.init(friendApply);
		Object[] params = getParams(friendApply);
		return this.addEntity(sql.toString(), params);
	}

	@Override
	public FriendApply queryById(long fayId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select ").append(getFields(null));
		sql.append(" from ").append(Account.ACC_FRIEND_APPLY).append(" where ");
		sql.append(FriendApplyF.FAY_ID).append("=?");

		return this.getEntity(sql.toString(), fayId);
	}

	private int update(FriendApply fa) {
		if (fa.getFayId() == null) {
			throw new IllegalArgumentException();
		}

		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("update ").append(Account.ACC_FRIEND_APPLY);
		sql.append(" set ").append(FriendApplyF.FAY_ID).append("=")
				.append(FriendApplyF.FAY_ID);
		if (fa.getResultStatus() != null) {
			sql.append(",").append(FriendApplyF.RESULT_STATUS).append("=?");
			params.add(fa.getResultStatus());
		}
		if (fa.getFriendMessage() != null) {
			sql.append(",").append(FriendApplyF.FRIDEND_MESSAGE).append("=?");
			params.add(fa.getFriendMessage());
		}
		if (fa.getFromDelFlag() != null) {
			sql.append(",").append(FriendApplyF.FROM_DEL_FLAG).append("=?");
			params.add(fa.getFromDelFlag());
		}
		if (fa.getToDelFlag() != null) {
			sql.append(",").append(FriendApplyF.TO_DEL_FLAG).append("=?");
			params.add(fa.getToDelFlag());
		}

		sql.append(" where ").append(FriendApplyF.FAY_ID).append("=?");
		params.add(fa.getFayId());

		return updateEntity(sql.toString(), params.toArray());
	}

	@Override
	public int updateResultStatus(long fayId, int resultStatus) {
		FriendApply fa = new FriendApply();
		fa.setFayId(fayId);
		fa.setResultStatus(resultStatus);
		return this.update(fa);
	}

	@Override
	public FriendApply queryBoth(long aUserId, long bUserId) {
		StringBuffer sqlList = new StringBuffer();
		sqlList.append("select ").append(getFields(null));
		sqlList.append(" from ").append(Account.ACC_FRIEND_APPLY)
				.append(" where (");
		sqlList.append(FriendApplyF.FROM_USER_ID).append("=? and ");
		sqlList.append(FriendApplyF.TO_USER_ID).append("=?) or (");
		sqlList.append(FriendApplyF.FROM_USER_ID).append("=? and ");
		sqlList.append(FriendApplyF.TO_USER_ID).append("=?)");
		sqlList.append(" order by ").append(FriendApplyF.ADD_TIME)
				.append(" desc ");

		Object[] params = new Object[] { aUserId, bUserId, bUserId, aUserId };
		return this.getEntity(sqlList.toString(), params);
	}

	@Override
	public List<FriendApply> queryByUserId(long userId) {
		StringBuffer sqlList = new StringBuffer();
		sqlList.append("select ").append(getFields(null));
		sqlList.append(" from ").append(Account.ACC_FRIEND_APPLY)
				.append(" where (");
		sqlList.append(FriendApplyF.FROM_USER_ID).append("=? or ");
		sqlList.append(FriendApplyF.TO_USER_ID).append("=?) and (");
		sqlList.append(FriendApplyF.FROM_DEL_FLAG).append("!=1 or ");
		sqlList.append(FriendApplyF.TO_DEL_FLAG).append("!=1)");

		sqlList.append(" order by ").append(FriendApplyF.ADD_TIME)
				.append(" desc ");

		Object[] params = new Object[] { userId, userId };
		return this.getEntityList(sqlList.toString(), params);
	}

	@Override
	public int updateFriendMessage(long fayId, String friendMessage) {
		FriendApply fa = new FriendApply();
		fa.setFayId(fayId);
		fa.setFriendMessage(friendMessage);
		return this.update(fa);
	}

	@Override
	public int updateFromDelFlag(long fayId, boolean fromDelFlag) {
		FriendApply fa = new FriendApply();
		fa.setFayId(fayId);
		fa.setFromDelFlag(fromDelFlag);
		return this.update(fa);
	}

	@Override
	public int updateToDelFlag(long fayId, boolean toDelFlag) {
		FriendApply fa = new FriendApply();
		fa.setFayId(fayId);
		fa.setToDelFlag(toDelFlag);
		return this.update(fa);
	}

}
