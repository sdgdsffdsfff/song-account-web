package com.song.account.entity;

import java.util.Date;

import com.song.commons.entity.LazyLoadEntity;

/**
 * 客户端唯一标识
 * 
 * @author songzigw
 *
 */
public class ClientSession extends LazyLoadEntity {

	private static final long serialVersionUID = -1869615492657208450L;

	@Override
	public void init() {
		if (this.creationTime == null) {
			this.creationTime = new Date();
		}
		if (this.lastAccessedTime == null) {
			this.lastAccessedTime = this.creationTime;
		}
		if (this.validFlag == null) {
			this.validFlag = true;
		}
	}

	/** 客户端唯一标识ID */
	private String clientId;
	/** 创建时间 */
	private Date creationTime;
	/** 最近访问时间 */
	private Date lastAccessedTime;
	/** 客户端对应用户ID */
	private Long userId;
	/** SESSION是否有效（超时） */
	private Boolean validFlag;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getLastAccessedTime() {
		return lastAccessedTime;
	}

	public void setLastAccessedTime(Date lastAccessedTime) {
		this.lastAccessedTime = lastAccessedTime;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Boolean getValidFlag() {
		return validFlag;
	}

	public void setValidFlag(Boolean validFlag) {
		this.validFlag = validFlag;
	}

}
