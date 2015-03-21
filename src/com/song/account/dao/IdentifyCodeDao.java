package com.song.account.dao;

import java.util.Date;
import java.util.List;

import com.song.account.dao.Database.Account;
import com.song.account.dao.Database.IdentifyCodeF;
import com.song.account.entity.IdentifyCode;
import com.song.account.entity.IdentifyCode.VerifyState;

/**
 * 数据验证持久层
 * 
 * @author 张松
 * 
 */
public interface IdentifyCodeDao {

	/**
	 * 插入数据
	 * 
	 * @param ic
	 * @return
	 */
	public int insert(IdentifyCode ic);

	/**
	 * 根据主键查询
	 * 
	 * @param icId
	 * @return
	 */
	IdentifyCode queryById(Long icId);

	/**
	 * 查询待验证数据的验证码
	 * 
	 * @param dataId
	 * @param table
	 * @param field
	 * @return
	 */
	List<IdentifyCode> queryByDataId(Long dataId, Account table,
			IdentifyCodeF field);

	/**
	 * 修改验证结果
	 * @param icId
	 * @param state
	 * @param now
	 */
	public int updateVerifyResult(Long icId, VerifyState state, Date now);
}
