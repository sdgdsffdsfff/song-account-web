package com.song.account.web.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.song.account.entity.User;
import com.song.account.service.SSOAuthService;
import com.song.account.service.UserService;
import com.song.commons.web.SSOAuth;

public abstract class BasicAction {

	public UserService userService;

	public SSOAuthService ssoAuthService;

	private User currUser;

	public void setErrInfo() {
		this.put("errInfo", "用户非法操作，请不要提交恶意信息！");
	}

	/**
	 * 当前在线用户
	 * 
	 * @return
	 */
	public User getCurrUser() {
		if (currUser != null) {
			return currUser;
		}

		SSOAuth<User> ia = new SSOAuth<User>(getRequest(), getResponse(),
				ssoAuthService);
		currUser = ia.getCurrAuth();
		return currUser;
	}

	/**
	 * 当前在线用户ID
	 * 
	 * @return
	 */
	public Long getCurrUid() {
		User u = this.getCurrUser();
		if (u == null) {
			return null;
		}
		return u.getUserId();
	}

	public void setSsoAuthService(SSOAuthService ssoAuthService) {
		this.ssoAuthService = ssoAuthService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public HttpSession getSession() {
		return getRequest().getSession();
	}

	public ServletContext getApplication() {
		return getSession().getServletContext();
	}

	public void put(String key, Object value) {
		ActionContext.getContext().put(key, value);
	}
}
