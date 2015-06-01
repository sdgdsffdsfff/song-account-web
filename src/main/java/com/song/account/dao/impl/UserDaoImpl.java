package com.song.account.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.RowCallbackHandler;

import com.song.account.dao.UserDao;
import com.song.account.dao.Database.Account;
import com.song.account.dao.Database.UserF;
import com.song.account.entity.User;
import com.song.commons.PageInfo;
import com.song.commons.dao.BasicDao;
import com.song.commons.dao.DaoUtils;

public class UserDaoImpl extends BasicDao<User> implements UserDao {

	@Override
	protected User rowMapper(ResultSet rs, int rowNum) throws SQLException {
		User user = new User();
		user.setUserId(rs.getLong(UserF.USER_ID.name()));
		user.setUserName(rs.getString(UserF.USER_NAME.name()));
		user.setAddTime(rs.getTimestamp(UserF.ADD_TIME.name()));
		user.setAccount(rs.getString(UserF.ACCOUNT.name()));
		user.setPassword(rs.getString(UserF.PASSWORD.name()));
		user.setNickName(rs.getString(UserF.NICK_NAME.name()));
		user.setPhotoPath(rs.getString(UserF.PHOTO_PATH.name()));
		user.setSex(rs.getInt(UserF.SEX.name()));
		user.setEmail(rs.getString(UserF.EMAIL.name()));
		user.setBirthdayYear(rs.getInt(UserF.BIRTHDAY_YEAR.name()));
		user.setBirthdayMonth(rs.getInt(UserF.BIRTHDAY_MONTH.name()));
		user.setBirthdayDay(rs.getInt(UserF.BIRTHDAY_DAY.name()));
		user.setSummary(rs.getString(UserF.SUMMARY.name()));
		user.setEaddress(rs.getString(UserF.EADDRESS.name()));
		user.setEnEmail(rs.getString(UserF.EN_EMAIL.name()));
		user.setEmIcId(rs.getLong(UserF.EM_IC_ID.name()));
		user.setRongToken(rs.getString(UserF.RONG_TOKEN.name()));

		return user;
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
		fields.append(tabPoint).append(UserF.USER_ID).append(",");
		fields.append(tabPoint).append(UserF.USER_NAME).append(",");
		fields.append(tabPoint).append(UserF.ADD_TIME).append(",");
		fields.append(tabPoint).append(UserF.ACCOUNT).append(",");
		fields.append(tabPoint).append(UserF.PASSWORD).append(",");
		fields.append(tabPoint).append(UserF.NICK_NAME).append(",");
		fields.append(tabPoint).append(UserF.PHOTO_PATH).append(",");
		fields.append(tabPoint).append(UserF.SEX).append(",");
		fields.append(tabPoint).append(UserF.EMAIL).append(",");
		fields.append(tabPoint).append(UserF.BIRTHDAY_YEAR).append(",");
		fields.append(tabPoint).append(UserF.BIRTHDAY_MONTH).append(",");
		fields.append(tabPoint).append(UserF.BIRTHDAY_DAY).append(",");
		fields.append(tabPoint).append(UserF.SUMMARY).append(",");
		fields.append(tabPoint).append(UserF.EADDRESS).append(",");
		fields.append(tabPoint).append(UserF.EN_EMAIL).append(",");
		fields.append(tabPoint).append(UserF.EM_IC_ID).append(",");
		fields.append(tabPoint).append(UserF.RONG_TOKEN).append("");
		return fields.toString();
	}

	@Override
	protected String getParamMarks() {
		return "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
	}

	@Override
	protected Object[] getParams(final User user) {
		return new Object[] { user.getUserId(), user.getUserName(),
				user.getAddTime(), user.getAccount(), user.getPassword(),
				user.getNickName(), user.getPhotoPath(), user.getSex(),
				user.getEmail(), user.getBirthdayYear(),
				user.getBirthdayMonth(), user.getBirthdayDay(),
				user.getSummary(), user.getEaddress(), user.getEnEmail(),
				user.getEmIcId(), user.getRongToken() };
	}

	@Override
	protected List<Object> getParams(final User user,
			final StringBuffer sqlWhere) {
		if (user == null) {
			return new ArrayList<Object>();
		}

		List<Object> params = new ArrayList<Object>();
		sqlWhere.append(" where 1=1 ");
		if (user.getNickName() != null && !"".equals(user.getNickName())) {
			sqlWhere.append(" and ").append(UserF.NICK_NAME).append(" like ?");
			params.add("%" + user.getNickName() + "%");
		}
		if (user.getUserId() != null) {
			sqlWhere.append(" and ").append(UserF.USER_ID).append("=?");
			params.add(user.getUserId());
		}
		return params;
	}

	private PageInfo<User> queryList(User user, UserF field,
			DaoUtils.Order order, int currPage, int pageSize) {
		StringBuffer sqlWhere = new StringBuffer();
		List<Object> params = getParams(user, sqlWhere);

		StringBuffer sqlList = new StringBuffer();
		sqlList.append("select ").append(getFields(null));
		sqlList.append(" from ").append(Account.ACC_USER);
		sqlList.append(sqlWhere.toString());
		if (field != null) {
			sqlList.append(" order by ").append(field).append(" ")
					.append(order);
		}

		// 求出查询总数
		StringBuffer sqlCount = new StringBuffer();
		sqlCount.append("select count(");
		sqlCount.append(UserF.USER_ID + ")");
		sqlCount.append(" from ").append(Account.ACC_USER);
		sqlCount.append(sqlWhere);
		int totalNum = super.getJdbcTemplate().queryForInt(sqlCount.toString(),
				params.toArray());

		// 查询用户列表
		int startIndex = PageInfo.getSkips(currPage, pageSize);
		int endIndex = startIndex + pageSize;
		String pageSql = this.getSqlStr(sqlList.toString(), startIndex,
				endIndex);
		List<User> userList = this.getEntityList(pageSql, params.toArray());

		// 封装数据分页信息
		PageInfo<User> pageInfo = new PageInfo<User>(currPage, pageSize,
				totalNum);
		// 修改入参currPage信息
		currPage = pageInfo.getCurrPage();
		pageInfo.setResult(userList);
		return pageInfo;
	}

	@Override
	protected void init(final User user) {
		user.init();
		if (user.getUserId() == null) {
			user.setUserId(this.getId());
		}
	}

	@Override
	public int insert(User user) {
		String sql = DaoUtils.insertSql(Account.ACC_USER, getFields(null),
				getParamMarks());
		this.init(user);
		Object[] params = getParams(user);
		return this.addEntity(sql.toString(), params);
	}

	@Override
	public User queryById(Long userId) {
		StringBuilder sql = new StringBuilder();
		sql.append("select ").append(getFields(null));
		sql.append(" from ").append(Account.ACC_USER).append(" where ");
		sql.append(UserF.USER_ID).append("=?");

		return this.getEntity(sql.toString(), userId);
	}

	@Override
	public User queryByAccount(String account) {
		StringBuilder sql = new StringBuilder();
		sql.append("select ").append(getFields(null));
		sql.append(" from ").append(Account.ACC_USER).append(" where ");
		sql.append(UserF.ACCOUNT).append("=?");

		return this.getEntity(sql.toString(), account);
	}

	@Override
	public int countByAccount(String account) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(").append(UserF.ACCOUNT).append(")");
		sql.append(" from ").append(Account.ACC_USER).append(" where ");
		sql.append(UserF.ACCOUNT).append("=?");

		return this.getJdbcTemplate().queryForInt(sql.toString(),
				new Object[] { account });
	}

	@Override
	public int countByNick(String nick) {
		StringBuilder sql = new StringBuilder();
		sql.append("select count(").append(UserF.USER_ID).append(")");
		sql.append(" from ").append(Account.ACC_USER).append(" where ");
		sql.append(UserF.NICK_NAME).append("=?");

		return this.getJdbcTemplate().queryForInt(sql.toString(),
				new Object[] { nick });
	}

	@Override
	public String queryPassByAccount(String account) {
		final StringBuffer password = new StringBuffer();

		StringBuilder sql = new StringBuilder();
		sql.append("select ").append(UserF.PASSWORD);
		sql.append(" from ").append(Account.ACC_USER).append(" where ");
		sql.append(UserF.ACCOUNT).append("=?");

		this.getJdbcTemplate().query(sql.toString(), new Object[] { account },
				new RowCallbackHandler() {
					@Override
					public void processRow(ResultSet rs) throws SQLException {
						password.append(rs.getString(UserF.PASSWORD.name()));
					}
				});

		if ("".equals(password.toString())) {
			return null;
		} else {
			return password.toString();
		}
	}

	@Override
	public PageInfo<User> queryListByKeyword(String keyword, int currPage,
			int pageSize) {
		User user = new User();
		user.setNickName(keyword);
		UserF field = UserF.ADD_TIME;
		DaoUtils.Order order = DaoUtils.Order.DESC;
		return this.queryList(user, field, order, currPage, pageSize);
	}

	private int update(User user) {
		if (user.getUserId() <= 0) {
			throw new IllegalArgumentException();
		}

		List<Object> params = new ArrayList<Object>();
		StringBuffer sql = new StringBuffer();
		sql.append("update ").append(Account.ACC_USER);
		sql.append(" set ").append(UserF.USER_ID).append("=")
				.append(UserF.USER_ID);
		if (user.getNickName() != null) {
			sql.append(",").append(UserF.NICK_NAME).append("=?");
			params.add(user.getNickName());
		}
		if (user.getUserName() != null) {
			sql.append(",").append(UserF.USER_NAME).append("=?");
			params.add(user.getUserName());
		}
		if (user.getSex() != null) {
			sql.append(",").append(UserF.SEX).append("=?");
			params.add(user.getSex());
		}
		if (user.getBirthdayDay() != null) {
			sql.append(",").append(UserF.BIRTHDAY_DAY).append("=?");
			params.add(user.getBirthdayDay());
		}
		if (user.getBirthdayMonth() != null) {
			sql.append(",").append(UserF.BIRTHDAY_MONTH).append("=?");
			params.add(user.getBirthdayMonth());
		}
		if (user.getBirthdayYear() != null) {
			sql.append(",").append(UserF.BIRTHDAY_YEAR).append("=?");
			params.add(user.getBirthdayYear());
		}
		if (user.getEaddress() != null) {
			sql.append(",").append(UserF.EADDRESS).append("=?");
			params.add(user.getEaddress());
		}
		if (user.getSummary() != null) {
			sql.append(",").append(UserF.SUMMARY).append("=?");
			params.add(user.getSummary());
		}
		if (user.getPhotoPath() != null) {
			sql.append(",").append(UserF.PHOTO_PATH).append("=?");
			params.add(user.getPhotoPath());
		}
		if (user.getPassword() != null) {
			sql.append(",").append(UserF.PASSWORD).append("=?");
			params.add(user.getPassword());
		}
		if (user.getEmail() != null) {
			sql.append(",").append(UserF.EMAIL).append("=?");
			params.add(user.getEmail());
		}
		if (user.getEnEmail() != null) {
			sql.append(",").append(UserF.EN_EMAIL).append("=?");
			params.add(user.getEnEmail());
		}
		if (user.getEmIcId() != null) {
			sql.append(",").append(UserF.EM_IC_ID).append("=?");
			params.add(user.getEmIcId());
		}
		if (user.getRongToken() != null) {
			sql.append(",").append(UserF.RONG_TOKEN).append("=?");
			params.add(user.getRongToken());
		}

		sql.append(" where ").append(UserF.USER_ID).append("=?");
		params.add(user.getUserId());

		return updateEntity(sql.toString(), params.toArray());
	}

	@Override
	public int update(Long userId, String nickName, String userName,
			Integer sex, int birthdayYear, int birthdayMonth, int birthdayDay,
			String eaddress, String summary) {
		User user = new User();
		user.setUserId(userId);
		user.setNickName(nickName);
		user.setUserName(userName);
		user.setSex(sex);
		user.setBirthdayDay(birthdayDay);
		user.setBirthdayMonth(birthdayMonth);
		user.setBirthdayYear(birthdayYear);
		user.setEaddress(eaddress);
		user.setSummary(summary);
		User userOld = this.queryById(userId);
		if (userOld != null) {
			String photoPath = userOld.getPhotoPath();
			user.setPhotoPath(photoPath);
		}
		return this.update(user);
	}

	@Override
	public int updatePhoto(Long userId, String photoPath) {
		User user = new User();
		user.setUserId(userId);
		user.setPhotoPath(photoPath);
		return this.update(user);
	}

	@Override
	public int updatePsw(Long userId, String newPsw) {
		User user = new User();
		user.setUserId(userId);
		user.setPassword(newPsw);
		return this.update(user);
	}

	@Override
	public int updateSellerFlag(Long applicantId, boolean sellerFlag) {
		User user = new User();
		user.setUserId(applicantId);
		// user.setSellerFlag(sellerFlag);
		return this.update(user);
	}

	@Override
	public int updateEnEmail(Long userId, String enEmail) {
		User user = new User();
		user.setUserId(userId);
		user.setEnEmail(enEmail);
		return this.update(user);
	}

	@Override
	public int updateEmIcId(Long userId, Long emIcId) {
		User user = new User();
		user.setUserId(userId);
		user.setEmIcId(emIcId);
		return this.update(user);
	}

	@Override
	public int updateEmail(Long userId, String email) {
		User user = new User();
		user.setUserId(userId);
		user.setEmail(email);
		user.setEnEmail("");
		user.setEmIcId(0l);
		return this.update(user);
	}

	@Override
	public User queryByEnEmail(String enEmail) {
		StringBuilder sql = new StringBuilder();
		sql.append("select ").append(getFields(null));
		sql.append(" from ").append(Account.ACC_USER).append(" where ");
		sql.append(UserF.EN_EMAIL).append("=?");

		return this.getEntity(sql.toString(), enEmail);
	}

	@Override
	public long getId() {
		return this.getId(Account.ACC_USER);
	}

	@Override
	public int updateRongToken(long userId, String rongToken) {
		User user = new User();
		user.setUserId(userId);
		user.setRongToken(rongToken);
		return this.update(user);
	}
}
