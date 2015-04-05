package com.song.account.dao;

import java.util.List;

import com.song.account.entity.FriendMessage;

public interface FriendMessageDao {
	/**
	 * 插入一条数据
	 * 
	 * @param fm
	 * @return
	 */
	public int inster(FriendMessage fm);

	/**
	 * 根据ID（主键）查询
	 * 
	 * @param fmeId
	 * @return
	 */
	public FriendMessage queryById(long fmeId);

	/**
	 * 查询好友请求过程中的来往消息
	 * @param fayId
	 * @return
	 */
	public List<FriendMessage> queryByFayId(long fayId);
	
}
