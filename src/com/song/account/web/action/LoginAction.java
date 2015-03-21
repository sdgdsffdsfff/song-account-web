package com.song.account.web.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import weibo4j.model.WeiboException;

import com.opensymphony.xwork2.Action;
import com.qq.connect.QQConnectException;
import com.qq.connect.api.OpenID;
import com.qq.connect.javabeans.AccessToken;
import com.qq.connect.oauth.Oauth;
import com.song.account.entity.BindSite;
import com.song.account.entity.User;
import com.song.account.service.ErrService;
import com.song.account.web.config.ConfigManager;
import com.song.commons.StringUtil;
import com.song.commons.service.ServiceException;
import com.song.commons.web.RandomValidateCode;
import com.song.commons.web.SSOAuth;

public class LoginAction extends BasicAction {

	private static Logger logger = Logger.getLogger(LoginAction.class);

	/**
	 * 到登入页面
	 * 
	 * @return
	 * @throws IOException
	 */
	public String toLoginPage() throws IOException {
		User u = this.getCurrUser();
		if (u != null) {
			return "toUserBasicPage";
		}
		return Action.SUCCESS;
	}

	/**
	 * 到注册页面
	 * 
	 * @return
	 * @throws IOException
	 */
	public String toRegisterPage() throws IOException {
		User u = this.getCurrUser();
		if (u != null) {
			return "toUserBasicPage";
		}
		return Action.SUCCESS;
	}

	/**
	 * 注册添加一个用户
	 * 
	 * @return
	 */
	public String register() {
		HttpServletRequest request = getRequest();
		String account = request.getParameter("account");
		String nickName = request.getParameter("nickName");
		String password = request.getParameter("password");
		String idCode = request.getParameter("idcode");

		// 验证码判断
		String randomString = (String) request.getSession().getAttribute(
				RandomValidateCode.RANDOMCODEKEY);
		if (!idCode.equalsIgnoreCase(randomString)) {
			this.put("errInfo", "验证码输入错误！");
			return Action.ERROR;
		}

		try {
			userService.register(account, password, nickName);
			// 将用户账号放入SESSION
			SSOAuth<User> ia = new SSOAuth<User>(request, getResponse(),
					ssoAuthService);
			ia.login(account, password);
		} catch (ServiceException e) {
			logger.error("LoginAction.register();", e);
			if (e.getErrInfo().equals(ErrService.UserS.ERR_100_001)) {
				this.put("errInfo", "账号已经被使用过了");
			} else if (e.getErrInfo().equals(ErrService.UserS.ERR_100_002)) {
				this.put("errInfo", "昵称太受欢迎了，请另外选一个");
			} else if (e.getErrInfo().equals(ErrService.UserS.ERR_100_005)) {
				this.put("errInfo", "账号格式错误");
			} else if (e.getErrInfo().equals(ErrService.UserS.ERR_100_006)) {
				this.put("errInfo", "昵称格式错误");
			} else if (e.getErrInfo().equals(ErrService.UserS.ERR_100_007)) {
				this.put("errInfo", "密码格式错误");
			}
			return Action.ERROR;
		} catch (Exception e) {
			logger.error("LoginAction.register();", e);
			this.put("errInfo", "用户非法操作，请不要提交恶意信息！");
			return Action.ERROR;
		}

		return Action.SUCCESS;
	}

	/**
	 * 注册成功后的页面
	 * 
	 * @return
	 */
	public String regSuccess() {
		// Long userId = (Long) getRequest().getSession().getAttribute(
		// ConstantVal.SESSION_USERID_KEY);
		// if (userId == null || userId.equals(0)) {
		// // 如果没有登入成功，返回登入页面
		// return "toLoginPage";
		// }
		//
		// PageInfo<Product> pageInfo = new PageInfo<Product>(1, 50, 50);
		// pageInfo.setResult(productCoreService.getProListSuccess(pageInfo
		// .getPageSize()));
		// this.put("pageInfo", pageInfo);
		// 登入成后，进入登入成功页面
		return Action.SUCCESS;
	}

	/**
	 * 判断账号是否已经存在
	 * 
	 * @return
	 * @throws IOException
	 */
	public String isExitAccount() throws IOException {
		String account = getRequest().getParameter("account");
		HttpServletResponse response = getResponse();
		// response.setDateHeader("Expires", -10);
		// response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		if (userService.verifyAccountRep(account)) {
			out.print(true);
		} else {
			out.print(false);
		}

		return null;
	}

	/**
	 * 用户昵称是否存在
	 * 
	 * @return
	 * @throws IOException
	 */
	public String isExitNickName() throws IOException {
		String nickName = getRequest().getParameter("nickName");
		HttpServletResponse response = getResponse();
		// response.setDateHeader("Expires", -10);
		// response.setContentType("text/html;charset=UTF-8");

		PrintWriter out = response.getWriter();
		if (userService.verifyNickRep(nickName)) {
			out.print(true);
		} else {
			out.print(false);
		}

		return null;
	}

	/**
	 * 验证登入信息
	 * 
	 * @return
	 * @throws IOException
	 */
	public String checkLogin() throws IOException {
		HttpServletRequest request = getRequest();
		String account = request.getParameter("account");
		String password = request.getParameter("password");

		this.put("account", account);
		if ("".equals(account)) {
			this.put("errInfo", "账号不能为空");
			return Action.INPUT;
		}

		try {
			userService.checkLogin(account, password);
		} catch (ServiceException e) {
			logger.debug("account: "+account, e);
			this.put("errInfo", e.getErrNotice());
			return Action.INPUT;
		}

		// 将用户账号放入SESSION
		// ActionContext.getContext().getSession().put(
		// ConstantVal.SESSION_USERID_KEY, user.getUserId());
		SSOAuth<User> ia = new SSOAuth<User>(request, getResponse(),
				ssoAuthService);
		ia.login(account, password);

		return Action.SUCCESS;

	}

	/**
	 * 退出登入
	 * 
	 * @return
	 * @throws IOException
	 */
	public String logout() throws IOException {
		SSOAuth<User> ia = new SSOAuth<User>(getRequest(), getResponse(),
				ssoAuthService);
		ia.logout();

		// getRequest().getSession().invalidate();

		getResponse().sendRedirect(
				ConfigManager.getInstance().getWebsiteAccountUri()
						+ "/login.html");

		return null;
	}

	/**
	 * 获取用户ID
	 * 
	 * @return
	 * @throws IOException
	 */
	public String getUserIdOnline() throws IOException {
		Long userId = this.getCurrUid();
		HttpServletResponse response = getResponse();
		PrintWriter out = response.getWriter();
		out.print(userId);
		return null;
	}

	/**
	 * QQ登入回调方法
	 * 
	 * @return
	 * @throws QQConnectException
	 */
	public String loginQqCallback() throws QQConnectException {
		HttpServletRequest request = this.getRequest();
		AccessToken accessTokenObj = (new Oauth())
				.getAccessTokenByRequest(request);
		long tokenExpireIn = accessTokenObj.getExpireIn();
		String accessToken = accessTokenObj.getAccessToken();
		OpenID openIdObj = new OpenID(accessToken);
		String openId = openIdObj.getUserOpenID();

		User user = this.getCurrUser();
		if (user == null) {
			BindSite bs = userService.checkLogin(BindSite.SiteMark.QQ, openId,
					accessToken, tokenExpireIn);
			// 将用户账号放入SESSION
			// ActionContext.getContext().getSession().put(
			// ConstantVal.SESSION_USERID_KEY, bs.getUserId());
			SSOAuth<User> ia = new SSOAuth<User>(request, getResponse(),
					ssoAuthService);
			user = userService.getUserById(bs.getUserId());
			//ia.createSession(user);
		} else {
			try {
				userService.bindSiteUser(user.getUserId(),
						BindSite.SiteMark.QQ, openId, accessToken,
						tokenExpireIn, false);
			} catch (ServiceException e) {
				logger.error("LoginAction.loginQqCallback();", e);
				this.put("errInfo", e.getErrNotice());
				return Action.ERROR;
			}
		}

		return Action.SUCCESS;
	}

	/**
	 * QQ登入
	 * 
	 * @return
	 * @throws IOException
	 */
	public String loginQq() throws IOException {
		HttpServletRequest request = this.getRequest();
		HttpServletResponse response = this.getResponse();
		try {
			response.sendRedirect(new Oauth().getAuthorizeURL(request));
		} catch (QQConnectException e) {
			logger.error("LoginAction.loginQq();", e);
			this.put("errInfo", "第三方信息调用失败，请从新尝试！");
			return Action.ERROR;
		}
		return null;
	}

	/**
	 * 新浪登入回调方法
	 * 
	 * @return
	 */
	public String loginSinaCallback() {
		HttpServletRequest request = this.getRequest();
		String code = request.getParameter("code");
		weibo4j.Oauth oauth = new weibo4j.Oauth();
		weibo4j.http.AccessToken at = null;
		try {
			at = oauth.getAccessTokenByCode(code);
		} catch (WeiboException e) {
			logger.error("LoginAction.loginSinaCallback();", e);
			this.put("errInfo", "第三方信息调用失败，请从新尝试！");
			return Action.ERROR;
		}

		String tokenExpire = at.getExpireIn();
		Long tokenExpireIn = StringUtil.parseLong(tokenExpire);
		String accessToken = at.getAccessToken();
		String openId = at.getUid();
		User user = this.getCurrUser();
		if (user == null) {
			BindSite bs = userService.checkLogin(BindSite.SiteMark.SINA,
					openId, accessToken, tokenExpireIn);
			// 将用户账号放入SESSION
			// ActionContext.getContext().getSession().put(
			// ConstantVal.SESSION_USERID_KEY, bs.getUserId());
			SSOAuth<User> ia = new SSOAuth<User>(request, getResponse(),
					ssoAuthService);
			user = userService.getUserById(bs.getUserId());
			//ia.createSession(user);
		} else {
			try {
				userService.bindSiteUser(user.getUserId(),
						BindSite.SiteMark.SINA, openId, accessToken, 0, false);
			} catch (ServiceException e) {
				logger.error("LoginAction.loginSinaCallback();", e);
				this.put("errInfo", e.getErrNotice());
				return Action.ERROR;
			}
		}

		return Action.SUCCESS;
	}

	/**
	 * 新浪登入
	 * 
	 * @return
	 * @throws IOException
	 */
	public String loginSina() throws IOException {
		// HttpServletRequest request = this.getRequest();
		HttpServletResponse response = this.getResponse();
		weibo4j.Oauth oauth = new weibo4j.Oauth();

		try {
			response.sendRedirect(oauth.authorize("code", "song", ""));
		} catch (WeiboException e) {
			logger.error("LoginAction.loginSina();", e);
			this.put("errInfo", "第三方信息调用失败，请从新尝试！");
			return Action.ERROR;
		}

		return null;
	}
}