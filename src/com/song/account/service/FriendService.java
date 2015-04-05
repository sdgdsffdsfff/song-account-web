package com.song.account.service;

import java.util.List;

import com.song.account.entity.Friend;
import com.song.account.entity.FriendApply;
import com.song.account.entity.FriendMessage;

/**
 * 好友业务逻辑
 * 
 * @author songzigw
 *
 */
public interface FriendService {
	/**
	 * 根据ID查询一条好友请求
	 * 
	 * @param fayId
	 * @return
	 */
	public FriendApply getFriendApplyById(long fayId);

	/**
	 * 发送一条好友请求
	 * 
	 * @param fromUserId
	 * @param toUserId
	 * @param reason
	 * @return
	 */
	public FriendApply sendFriendApply(long fromUserId, long toUserId,
			String reason);

	/**
	 * 同意加为好友
	 * 
	 * @param fayId
	 */
	public void agreeFriendApply(long fayId);

	/**
	 * 删除好友请求
	 * 
	 * @param fayId
	 *            被删除的好友请求
	 * @param opnUserId
	 *            当前操作者
	 */
	public void delFriendApplyById(long fayId, long opnUserId);

	/**
	 * 查询用户好友请求（发出和收到的）
	 * 
	 * @param userId
	 * @return
	 */
	public List<FriendApply> getFriendApplyList(long userId);

	/**
	 * 查询用户之间最新好友申请状况
	 * 
	 * @param aUserId
	 * @param bUserId
	 * @return
	 */
	public FriendApply getFriendApplyBoth(long aUserId, long bUserId);

	/**
	 * 发送好友请求时的来往消息
	 * 
	 * @param fayId
	 * @param fromUserId
	 * @param toUserId
	 * @param message
	 */
	public FriendMessage sendFriendMessage(long fayId, long fromUserId, long toUserId,
			String message);

	/**
	 * 查询好友请求过程中的来往消息
	 * 
	 * @param fayId
	 * @return
	 */
	public List<FriendMessage> getFriendMessageListByFayId(long fayId);

	/**
	 * 获取一个用户的好友列表（联系人）
	 * 
	 * @param userId
	 * @return
	 */
	public List<Friend> getFriendListByUserId(long userId);

	/**
	 * 删除好友联系人
	 * 
	 * @param userId
	 * @param friendId
	 */
	public void delFriendById(long userId, long friendId);

	/**
	 * 判断俩个人是否互为好友
	 * 
	 * @param aUserId
	 * @param bUserId
	 */
	public boolean isFriend(long aUserId, long bUserId);
}
