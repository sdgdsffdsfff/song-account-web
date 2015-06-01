package com.song.account.dao;

import java.util.Date;

import com.song.account.entity.ClientSession;

public interface ClientSessionDao {

	/**
	 * 插入一条数据
	 * 
	 * @param cs
	 * @return
	 */
	public int inster(ClientSession cs);

	/**
	 * 根据ID（主键）查询
	 * 
	 * @param clientId
	 * @return
	 */
	public ClientSession queryById(String clientId);

	/**
	 * 删除SESSION
	 * 
	 * @param clientId
	 * @return
	 */
	public int delById(String clientId);

	/**
	 * 修改最近访问时间
	 * @param clientId
	 * @param date
	 * @return
	 */
	public int updateLastAccessedTime(String clientId, Date date);

	/**
	 * 修改SESSION是否有效
	 * @param clientId
	 * @param validFlag
	 * @return
	 */
	public int updateValidFlag(String clientId, boolean validFlag);
}
