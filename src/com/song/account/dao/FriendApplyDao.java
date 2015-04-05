package com.song.account.dao;

import java.util.List;

import com.song.account.entity.FriendApply;

public interface FriendApplyDao {

	/**
	 * 插入一条数据
	 * @param friendApply
	 * @return
	 */
	public int inster(FriendApply friendApply);
	
	/**
	 * 根据ID（主键）查询
	 * @param fayId
	 * @return
	 */
	public FriendApply queryById(long fayId);
	
	/**
	 * 查询好友间最终请求状态
	 * @param aUserId
	 * @param bUserId
	 * @return
	 */
	public FriendApply queryBoth(long aUserId, long bUserId);
	
	/**
	 * 查询用户发出的好友请求，和收到的好友请求
	 * 
	 * @param userId
	 * @return
	 */
	public List<FriendApply> queryByUserId(long userId);
	
	/**
	 * 修改好友申请结果
	 * @param fayId
	 * @param resultStatus
	 * @return
	 */
	public int updateResultStatus(long fayId, int resultStatus);
	
	/**
	 * 修改好友申请消息
	 * @param fayId
	 * @param friendMessage
	 * @return
	 */
	public int updateFriendMessage(long fayId, String friendMessage);

	public int updateFromDelFlag(long fayId, boolean fromDelFlag);

	public int updateToDelFlag(long fayId, boolean toDelFlag);
}
