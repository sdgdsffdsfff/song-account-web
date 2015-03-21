package com.song.account.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.song.account.dao.IdentifyCodeDao;
import com.song.account.dao.Database.Account;
import com.song.account.dao.Database.IdentifyCodeF;
import com.song.account.entity.IdentifyCode;
import com.song.account.entity.IdentifyCode.VerifyState;
import com.song.commons.dao.BasicDao;
import com.song.commons.dao.DaoUtils;

public class IdentifyCodeDaoImpl extends BasicDao<IdentifyCode> implements IdentifyCodeDao {

	@Override
	protected String getFields(String tableName) {
		String tabPoint = null;
		if (tableName == null || "".equals(tableName)) {
			tabPoint = "";
		} else {
			tabPoint = tableName + ".";
		}
		
		StringBuffer fields = new StringBuffer();
		fields.append(tabPoint).append(IdentifyCodeF.ADD_TIME).append(",");
		fields.append(tabPoint).append(IdentifyCodeF.CODE).append(",");
		fields.append(tabPoint).append(IdentifyCodeF.END_TIME).append(",");
		fields.append(tabPoint).append(IdentifyCodeF.IC_ID).append(",");
		fields.append(tabPoint).append(IdentifyCodeF.START_TIME).append(",");
		fields.append(tabPoint).append(IdentifyCodeF.TEXT_DESC).append(",");
		fields.append(tabPoint).append(IdentifyCodeF.DATA_ID).append(",");
		fields.append(tabPoint).append(IdentifyCodeF.VERIFY_STATE).append(",");
		fields.append(tabPoint).append(IdentifyCodeF.VERIFY_TIME).append(",");
		fields.append(tabPoint).append(IdentifyCodeF.TABLE_NAME).append(",");
		fields.append(tabPoint).append(IdentifyCodeF.FIELD_NAME).append("");
		return fields.toString();
	}

	@Override
	protected String getParamMarks() {
		return "?,?,?,?,?,?,?,?,?,?,?";
	}

	@Override
	protected Object[] getParams(IdentifyCode t) {
		return new Object[] { t.getAddTime(), t.getCode(), t.getEndTime(),
				t.getIcId(), t.getStartTime(), t.getTextDesc(), t.getDataId(),
				t.getVerifyState().getValue(), t.getVerifyTime(),
				t.getTableName(), t.getFieldName() };
	}

	@Override
	protected List<Object> getParams(IdentifyCode t, StringBuffer sqlWhere) {
		if (t == null) {
			return new ArrayList<Object>();
		}
		
		List<Object> params = new ArrayList<Object>();
		sqlWhere.append(" where 1=1 ");
		if (t.getTextDesc() != null) {
			sqlWhere.append(" and ").append(IdentifyCodeF.TEXT_DESC).append(
					" like ?");
			params.add("%" + t.getTextDesc() + "%");
		}
		if (t.getDataId() != null) {
			sqlWhere.append(" and ").append(IdentifyCodeF.DATA_ID).append(
					"=?");
			params.add(t.getDataId());
		}
		if (t.getTableName() != null) {
			sqlWhere.append(" and ").append(IdentifyCodeF.TABLE_NAME).append(
					"=?");
			params.add(t.getTableName());
		}
		if (t.getFieldName() != null) {
			sqlWhere.append(" and ").append(IdentifyCodeF.FIELD_NAME).append(
					"=?");
			params.add(t.getFieldName());
		}
		return params;
	}

	@Override
	protected void init(IdentifyCode t) {
		t.init();
		if (t.getIcId() == null) {
			t.setIcId(this.getId(Account.ACC_IDENTIFY_CODE));
		}
	}

	@Override
	protected IdentifyCode rowMapper(ResultSet rs, int rowNum)
			throws SQLException {
		IdentifyCode ic = new IdentifyCode();
		ic.setAddTime(rs.getTimestamp(IdentifyCodeF.ADD_TIME.name()));
		ic.setCode(rs.getString(IdentifyCodeF.CODE.name()));
		ic.setEndTime(rs.getTimestamp(IdentifyCodeF.END_TIME.name()));
		ic.setIcId(rs.getLong(IdentifyCodeF.IC_ID.name()));
		ic.setStartTime(rs.getTimestamp(IdentifyCodeF.START_TIME.name()));
		ic.setTextDesc(rs.getString(IdentifyCodeF.TEXT_DESC.name()));
		ic.setDataId(rs.getLong(IdentifyCodeF.DATA_ID.name()));
		ic.setVerifyState(IdentifyCode.VerifyState.getInstance(rs
				.getInt(IdentifyCodeF.VERIFY_STATE.name())));
		ic.setVerifyTime(rs.getTimestamp(IdentifyCodeF.VERIFY_TIME.name()));
		ic.setTableName(rs.getString(IdentifyCodeF.TABLE_NAME.name()));
		ic.setFieldName(rs.getString(IdentifyCodeF.FIELD_NAME.name()));
		
		return ic;
	}

	@Override
	public int insert(IdentifyCode ic) {
		String sql = DaoUtils.insertSql(Account.ACC_IDENTIFY_CODE, getFields(null),
				getParamMarks());
		this.init(ic);
		Object[] params = getParams(ic);
		return this.addEntity(sql.toString(), params);
	}

	private List<IdentifyCode> query(IdentifyCode ic,
			IdentifyCodeF field, DaoUtils.Order order) {
		StringBuffer sqlWhere = new StringBuffer();
		List<Object> params = getParams(ic, sqlWhere);

		StringBuffer sqlList = new StringBuffer();
		sqlList.append("select ").append(getFields(null));
		sqlList.append(" from ").append(Account.ACC_IDENTIFY_CODE);
		sqlList.append(sqlWhere.toString());
		if (field != null) {
			sqlList.append(" order by ").append(field).append(" ")
					.append(order);
		}

		return this.getEntityList(sqlList.toString(), params.toArray());
	}
	
	@Override
	public List<IdentifyCode> queryByDataId(Long dataId, Account table,
			IdentifyCodeF field) {
		IdentifyCode ic = new IdentifyCode();
		ic.setDataId(dataId);
		ic.setTableName(table.name());
		ic.setFieldName(field.name());
		
		return this.query(ic, IdentifyCodeF.ADD_TIME, DaoUtils.Order.DESC);
	}

	@Override
	public IdentifyCode queryById(Long icId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select ").append(getFields(null));
		sql.append(" from ").append(Account.ACC_IDENTIFY_CODE).append(" where ");
		sql.append(IdentifyCodeF.IC_ID).append("=?");

		return this.getEntity(sql.toString(), icId);
	}

	private int update(IdentifyCode ic) {
		if (ic.getIcId() <= 0) {
			throw new IllegalArgumentException();
		}
		
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("update ").append(Account.ACC_IDENTIFY_CODE);
		sql.append(" set ").append(IdentifyCodeF.IC_ID).append("=").append(
				IdentifyCodeF.IC_ID);
		if (ic.getVerifyState() != null) {
			sql.append(",").append(IdentifyCodeF.VERIFY_STATE).append("=?");
			params.add(ic.getVerifyState().getValue());
		}
		if (ic.getVerifyTime() != null) {
			sql.append(",").append(IdentifyCodeF.VERIFY_TIME).append("=?");
			params.add(ic.getVerifyTime());
		}
		sql.append(" where ").append(IdentifyCodeF.IC_ID).append("=?");
		params.add(ic.getIcId());
		
		return updateEntity(sql.toString(), params.toArray());
	}
	
	@Override
	public int updateVerifyResult(Long icId, VerifyState state, Date now) {
		IdentifyCode ic = new IdentifyCode();
		ic.setIcId(icId);
		ic.setVerifyState(state);
		ic.setVerifyTime(now);
		return this.update(ic);
	}

}
