package com.song.account.api.result;

import java.util.List;

import com.song.account.entity.Friend;
import com.song.commons.api.Result;

public class FriendItems extends Result {

	private static final long serialVersionUID = 7793822382480978841L;

	List<Friend> friendList;

	public List<Friend> getFriendList() {
		return friendList;
	}

	public void setFriendList(List<Friend> friendList) {
		this.friendList = friendList;
	}

}