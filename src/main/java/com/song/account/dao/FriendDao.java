package com.song.account.dao;

import java.util.List;

import com.song.account.entity.Friend;

public interface FriendDao {
	/**
	 * 插入一条数据
	 * 
	 * @param friend
	 * @return
	 */
	public int inster(Friend friend);

	/**
	 * 根据ID（主键）查询
	 * @param userId
	 * @param friendUserId
	 * @return
	 */
	public Friend queryById(long userId, long friendUserId);
	
	/**
	 * 删除好友
	 * @param userId
	 * @param friendUserId
	 * @return
	 */
	public int delById(long userId, long friendUserId);
	
	/**
	 * 获取一个用户的好友列表（联系人）
	 * @param userId
	 * @return
	 */
	public List<Friend> queryFriendList(long userId);
}
