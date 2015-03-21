package com.song.account.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.song.account.dao.BindSiteDao;
import com.song.account.dao.Database.Account;
import com.song.account.dao.Database.BindSiteF;
import com.song.account.entity.BindSite;
import com.song.account.entity.BindSite.SiteMark;
import com.song.commons.dao.BasicDao;
import com.song.commons.dao.DaoUtils;

public class BindSiteDaoImpl extends BasicDao<BindSite> implements
		BindSiteDao {

	@Override
	protected String getFields(String tableName) {
		String tabPoint = null;
		if (tableName == null || "".equals(tableName)) {
			tabPoint = "";
		} else {
			tabPoint = tableName + ".";
		}

		StringBuffer fields = new StringBuffer();
		fields.append(tabPoint).append(BindSiteF.ADD_TIME).append(",");
		fields.append(tabPoint).append(BindSiteF.BIND_SITE_ID).append(",");
		fields.append(tabPoint).append(BindSiteF.ACCESS_TOKEN).append(",");
		fields.append(tabPoint).append(BindSiteF.NICK_NAME).append(",");
		fields.append(tabPoint).append(BindSiteF.OPEN_ID).append(",");
		fields.append(tabPoint).append(BindSiteF.REG_FLAG).append(",");
		fields.append(tabPoint).append(BindSiteF.SITE_MARK).append(",");
		fields.append(tabPoint).append(BindSiteF.USER_ID).append(",");
		fields.append(tabPoint).append(BindSiteF.START_DATE).append(",");
		fields.append(tabPoint).append(BindSiteF.END_DATE).append(",");
		fields.append(tabPoint).append(BindSiteF.PHOTO_PATH).append(",");
		fields.append(tabPoint).append(BindSiteF.UPD_TIME).append("");
		return fields.toString();
	}

	@Override
	protected String getParamMarks() {
		return "?,?,?,?,?,?,?,?,?,?,?,?";
	}

	@Override
	protected Object[] getParams(BindSite t) {
		return new Object[] { t.getAddTime(), t.getBindSiteId(),
				t.getAccessToken(), t.getNickName(), t.getOpenId(),
				t.getRegFlag(), t.getSiteMark().getValue(), t.getUserId(),
				t.getStartDate(), t.getEndDate(), t.getPhotoPath(),
				t.getUpdTime() };
	}

	@Override
	protected List<Object> getParams(BindSite t, StringBuffer sqlWhere) {
		if (t == null) {
			return new ArrayList<Object>();
		}

		List<Object> params = new ArrayList<Object>();
		sqlWhere.append(" where 1=1 ");
		if (t.getRegFlag() != null) {
			sqlWhere.append(" and ").append(BindSiteF.REG_FLAG).append("=?");
			params.add(t.getRegFlag());
		}
		if (t.getSiteMark() != null) {
			sqlWhere.append(" and ").append(BindSiteF.SITE_MARK).append("=?");
			params.add(t.getSiteMark().getValue());
		}
		if (t.getOpenId() != null) {
			sqlWhere.append(" and ").append(BindSiteF.OPEN_ID).append("=?");
			params.add(t.getOpenId());
		}
		if (t.getUserId() != null) {
			sqlWhere.append(" and ").append(BindSiteF.USER_ID).append("=?");
			params.add(t.getUserId());
		}
		return params;
	}

	@Override
	protected void init(BindSite t) {
		t.init();
		if (t.getBindSiteId() == null) {
			t.setBindSiteId(this.getId(Account.ACC_BIND_SITE));
		}
	}

	@Override
	protected BindSite rowMapper(ResultSet rs, int rowNum) throws SQLException {
		BindSite bs = new BindSite();
		bs.setAddTime(rs.getTimestamp(BindSiteF.ADD_TIME.name()));
		bs.setBindSiteId(rs.getLong(BindSiteF.BIND_SITE_ID.name()));
		bs.setAccessToken(rs.getString(BindSiteF.ACCESS_TOKEN.name()));
		bs.setNickName(rs.getString(BindSiteF.NICK_NAME.name()));
		bs.setOpenId(rs.getString(BindSiteF.OPEN_ID.name()));
		bs.setRegFlag(rs.getBoolean(BindSiteF.REG_FLAG.name()));
		bs.setSiteMark(BindSite.SiteMark.getInstance(rs
				.getInt(BindSiteF.SITE_MARK.name())));
		bs.setUserId(rs.getLong(BindSiteF.USER_ID.name()));
		bs.setStartDate(rs.getTimestamp(BindSiteF.START_DATE.name()));
		bs.setEndDate(rs.getTimestamp(BindSiteF.END_DATE.name()));
		bs.setPhotoPath(rs.getString(BindSiteF.PHOTO_PATH.name()));
		bs.setUpdTime(rs.getTimestamp(BindSiteF.UPD_TIME.name()));
		return bs;
	}

	@Override
	public int insert(BindSite bindSite) {
		String sql = DaoUtils.insertSql(Account.ACC_BIND_SITE, getFields(null),
				getParamMarks());
		this.init(bindSite);
		Object[] params = getParams(bindSite);
		return this.addEntity(sql.toString(), params);
	}

	@Override
	public int deletById(Long bindSiteId) {
		StringBuffer delSql = new StringBuffer();
		delSql.append("delete from ");
		delSql.append(Account.ACC_BIND_SITE);
		delSql.append(" where ").append(BindSiteF.BIND_SITE_ID).append("=?");

		return this.delEntity(delSql.toString(), bindSiteId);
	}

	@Override
	public BindSite queryById(Long bindSiteId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select ").append(this.getFields(null));
		sql.append(" from ").append(Account.ACC_BIND_SITE);
		sql.append(" where ").append(BindSiteF.BIND_SITE_ID).append("=?");
		return this.getEntity(sql.toString(), bindSiteId);
	}

	@Override
	public BindSite queryByOpenId(SiteMark siteMark, String openId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select ").append(this.getFields(null));
		sql.append(" from ").append(Account.ACC_BIND_SITE);
		
		BindSite bs = new BindSite();
		bs.setSiteMark(siteMark);
		bs.setOpenId(openId);
		
		StringBuffer sqlWhere = new StringBuffer();
		List<Object> params = this.getParams(bs, sqlWhere);
		sql.append(sqlWhere.toString());
		
		return this.getEntity(sql.toString(), params.toArray());
	}

	@Override
	public BindSite queryByUserId(SiteMark siteMark, Long userId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select ").append(this.getFields(null));
		sql.append(" from ").append(Account.ACC_BIND_SITE);
		
		BindSite bs = new BindSite();
		bs.setSiteMark(siteMark);
		bs.setUserId(userId);
		
		StringBuffer sqlWhere = new StringBuffer();
		List<Object> params = this.getParams(bs, sqlWhere);
		sql.append(sqlWhere.toString());
		
		return this.getEntity(sql.toString(), params.toArray());
	}

	@Override
	public List<BindSite> queryListByUserId(Long userId) {
		StringBuffer sql = new StringBuffer();
		sql.append("select ").append(this.getFields(null));
		sql.append(" from ").append(Account.ACC_BIND_SITE);
		StringBuffer sqlWhere = new StringBuffer();
		BindSite bs = new BindSite();
		bs.setUserId(userId);
		List<Object> params = this.getParams(bs, sqlWhere);
		sql.append(sqlWhere.toString());
		
		return this.getEntityList(sql.toString(), params.toArray());
	}

	private int update(BindSite bs) {
		if (bs.getBindSiteId() <= 0) {
			throw new IllegalArgumentException();
		}
		
		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("update ").append(Account.ACC_BIND_SITE);
		sql.append(" set ").append(BindSiteF.UPD_TIME).append("=?");
		params.add(new Date());
		
		if (bs.getAccessToken() != null) {
			sql.append(",").append(BindSiteF.ACCESS_TOKEN).append("=?");
			params.add(bs.getAccessToken());
		}
		if (bs.getNickName() != null) {
			sql.append(",").append(BindSiteF.NICK_NAME).append("=?");
			params.add(bs.getNickName());
		}
		if (bs.getPhotoPath() != null) {
			sql.append(",").append(BindSiteF.PHOTO_PATH).append("=?");
			params.add(bs.getPhotoPath());
		}
		if (bs.getStartDate() != null) {
			sql.append(",").append(BindSiteF.START_DATE).append("=?");
			params.add(bs.getStartDate());
		}
		if (bs.getEndDate() != null) {
			sql.append(",").append(BindSiteF.END_DATE).append("=?");
			params.add(bs.getEndDate());
		}
		
		sql.append(" where ").append(BindSiteF.BIND_SITE_ID).append("=?");
		params.add(bs.getBindSiteId());
		
		return updateEntity(sql.toString(), params.toArray());
	}
	
	@Override
	public int updateToken(Long bindSiteId, String accessToken,
			String nickName, String photoPath, Date startDate, Date endDate) {
		BindSite bs = new BindSite();
		bs.setBindSiteId(bindSiteId);
		bs.setAccessToken(accessToken);
		bs.setNickName(nickName);
		bs.setPhotoPath(photoPath);
		bs.setStartDate(startDate);
		bs.setEndDate(endDate);
		return this.update(bs);
	}

}
