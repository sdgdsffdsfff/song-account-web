package com.song.account.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.song.account.dao.FriendApplyDao;
import com.song.account.dao.FriendDao;
import com.song.account.dao.FriendMessageDao;
import com.song.account.entity.Friend;
import com.song.account.entity.FriendApply;
import com.song.account.entity.FriendApply.ResultStatus;
import com.song.account.entity.FriendMessage;
import com.song.account.service.ErrService;
import com.song.account.service.FriendService;
import com.song.commons.service.ServiceException;

/**
 * 好友业务逻辑
 * 
 * @author songzigw
 *
 */
public class FriendServiceImpl implements FriendService {

	private FriendDao friendDao;
	private FriendMessageDao friendMessageDao;
	private FriendApplyDao friendApplyDao;

	public void setFriendDao(FriendDao friendDao) {
		this.friendDao = friendDao;
	}

	public void setFriendMessageDao(FriendMessageDao friendMessageDao) {
		this.friendMessageDao = friendMessageDao;
	}

	public void setFriendApplyDao(FriendApplyDao friendApplyDao) {
		this.friendApplyDao = friendApplyDao;
	}

	@Override
	public FriendApply getFriendApplyById(long fayId) {
		return friendApplyDao.queryById(fayId);
	}

	@Override
	public List<FriendApply> getFriendApplyList(long userId) {
		List<FriendApply> faList = new ArrayList<FriendApply>();
		List<FriendApply> fayList = friendApplyDao.queryByUserId(userId);
		for (FriendApply fay : fayList) {
			if (fay.getFromUserId().equals(userId)) {
				if (!fay.getFromDelFlag()) {
					faList.add(fay);
				}
			} else if (fay.getToUserId().equals(userId)) {
				if (!fay.getToDelFlag()) {
					faList.add(fay);
				}
			}
		}
		return faList;
	}

	@Override
	public FriendApply getFriendApplyBoth(long aUserId, long bUserId) {
		return friendApplyDao.queryBoth(aUserId, bUserId);
	}

	@Override
	public List<Friend> getFriendListByUserId(long userId) {
		return friendDao.queryFriendList(userId);
	}

	@Override
	public void delFriendById(long userId, long friendId) {
		friendDao.delById(userId, friendId);
	}

	@Override
	public boolean isFriend(long aUserId, long bUserId) {
		Friend f1 = friendDao.queryById(aUserId, bUserId);
		Friend f2 = friendDao.queryById(bUserId, aUserId);
		if (f1 != null && f2 != null) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void delFriendApplyById(long fayId, long opnUserId) {
		FriendApply fa = friendApplyDao.queryById(fayId);
		// 好友请求是否存在，不存在直接返回，后面的逻辑不用考虑
		if (fa == null) {
			return;
		}

		if (fa.getFromUserId().equals(opnUserId)) {
			// 如果是发起者删除好友请求
			friendApplyDao.updateFromDelFlag(fayId, true);
		} else if (fa.getToUserId().equals(opnUserId)) {
			// 如果是被请求者删除好友请求
			friendApplyDao.updateToDelFlag(fayId, true);
		}
	}

	private void delFriendApplyById(long fayId) {
		friendApplyDao.updateFromDelFlag(fayId, true);
		friendApplyDao.updateToDelFlag(fayId, true);
	}

	@Override
	public FriendApply sendFriendApply(long fromUserId, long toUserId,
			String reason) {
		boolean flag = isFriend(fromUserId, toUserId);
		if (flag) {
			throw new ServiceException(ErrService.FriendE.ACC_302,
					"彼此已经互为好友，无需发送好友请求！");
		}

		// 不能自己給自己发送好友请求
		if (fromUserId == toUserId) {
			throw new ServiceException(ErrService.FriendE.ACC_301,
					"不能自己給自己发送好友请求");
		}
		FriendApply fa = friendApplyDao.queryBoth(fromUserId, toUserId);
		// fa为null创建一个
		if (fa == null) {
			fa = createFriendApply(fromUserId, toUserId, reason);
			return fa;
		}

		// 双方都已删除好友请求 or 验证好友请求已经通过 ，创建一个新的好友请求
		if ((fa.getFromDelFlag() && fa.getFromDelFlag())
				|| fa.getResultStatus().equals(
						FriendApply.ResultStatus.AGREE.getValue())) {
			// 隐藏（删除）之前的好友请求
			delFriendApplyById(fa.getFayId());
			fa = createFriendApply(fromUserId, toUserId, reason);
			return fa;
		}

		if (fa.getFromUserId().equals(toUserId)) {
			throw new ServiceException(ErrService.FriendE.ACC_303,
					"对方已经向你发出好友邀请，您无需发送。");
		}

		sendFriendMessage(fa.getFayId(), fromUserId, toUserId, reason);
		fa.setFriendMessage(reason);
		return fa;
	}

	private FriendApply createFriendApply(long fromUserId, long toUserId,
			String reason) {
		FriendApply fa = new FriendApply();
		fa.setFromUserId(fromUserId);
		fa.setToUserId(toUserId);
		fa.setFriendMessage(reason);
		friendApplyDao.inster(fa);
		sendFriendMessage(fa.getFayId(), fromUserId, toUserId,
				fa.getFriendMessage());

		return fa;
	}

	@Override
	public void agreeFriendApply(long fayId) {
		FriendApply fa = this.getFriendApplyById(fayId);
		if (fa == null) {
			return;
		}
		// 验证好友请求已经通过
		if (fa.getResultStatus().equals(
				FriendApply.ResultStatus.AGREE.getValue())) {
			return;
		}
		// 双方都已删除好友请求
		if (fa.getFromDelFlag() && fa.getFromDelFlag()) {
			return;
		}

		// 好友请求通过
		friendApplyDao.updateResultStatus(fayId, ResultStatus.AGREE.getValue());
		Friend friendA = new Friend();
		friendA.setUserId(fa.getFromUserId());
		friendA.setFriendUserId(fa.getToUserId());
		Friend friendB = new Friend();
		friendB.setUserId(fa.getToUserId());
		friendB.setFriendUserId(fa.getFromUserId());
		friendDao.inster(friendA);
		friendDao.inster(friendB);
		// 发送聊天界面的小灰条
	}

	@Override
	public FriendMessage sendFriendMessage(long fayId, long fromUserId,
			long toUserId, String message) {
		// 不能自己給自己发送信息
		if (fromUserId == toUserId) {
			throw new ServiceException(ErrService.Common.ACC_001,
					"不能自己給自己发送信息");
		}
		FriendApply fa = this.getFriendApplyById(fayId);
		if (fa == null) {
			return null;
		}
		// 验证好友请求通过，无需发送消息
		if (fa.getResultStatus().equals(
				FriendApply.ResultStatus.AGREE.getValue())) {
			return null;
		}
		// 双方都已删除好友请求，无需发送消息
		if (fa.getFromDelFlag() && fa.getFromDelFlag()) {
			return null;
		}

		FriendMessage fm = new FriendMessage();
		fm.setFayId(fayId);
		fm.setFromUserId(fromUserId);
		fm.setToUserId(toUserId);
		fm.setMessage(message);
		this.friendMessageDao.inster(fm);
		// 修改好友请求中的信息
		friendApplyDao.updateFriendMessage(fayId, message);
		// 显示双方的好友请求
		showFriendApply(fayId);
		// 发送好友请求往来消息

		return fm;
	}

	private void showFriendApply(long fayId) {
		friendApplyDao.updateFromDelFlag(fayId, false);
		friendApplyDao.updateToDelFlag(fayId, false);
	}

	@Override
	public List<FriendMessage> getFriendMessageListByFayId(long fayId) {
		return friendMessageDao.queryByFayId(fayId);
	}

}
