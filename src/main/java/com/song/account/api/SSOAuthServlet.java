package com.song.account.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.song.account.entity.User;
import com.song.account.service.SSOAuthService;
import com.song.commons.api.Result;
import com.song.commons.api.StringResult;
import com.song.commons.entity.EntityUtil;
import com.song.commons.service.General;
import com.song.commons.service.ServiceException;

public class SSOAuthServlet extends HttpServlet {

	private static final long serialVersionUID = -8924919322384086101L;

	private static String GET_USER_JSON = "getUser.json";
	private static String CREATE_CLIENT_SESSION_JSON = "createClientSession.json";
	private static String IS_CLIENT_SESSION_ID_JSON = "isClientSessionId.json";
	private static String LOGIN_JSON = "login.json";
	private static String LOGOUT_JSON = "logout.json";
	private static String GET_RONG_TOKEN_JSON = "getRongToken.json";

	private static Logger logger = Logger.getLogger(SSOAuthServlet.class);

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse rsp)
			throws ServletException, IOException {
		String pathInfo = req.getPathInfo();
		logger.debug("pathInfo: " + pathInfo);
		if (!req.getMethod().equalsIgnoreCase("post")) {
			Result rt = new Result();
			rt.setErrCode(General.GEN_003.getErrCode());
			rt.setErrDesc("not count get method.");
			ServletUtil.print(rsp, rt, Result.class);
			return;
		}

		if (pathInfo.endsWith(GET_USER_JSON)) {
			getUser(req, rsp);
		} else if (pathInfo.endsWith(CREATE_CLIENT_SESSION_JSON)) {
			createClientSession(req, rsp);
		} else if (pathInfo.endsWith(CREATE_CLIENT_SESSION_JSON)) {
			createClientSession(req, rsp);
		} else if (pathInfo.endsWith(IS_CLIENT_SESSION_ID_JSON)) {
			isClientSessionId(req, rsp);
		} else if (pathInfo.endsWith(LOGIN_JSON)) {
			login(req, rsp);
		} else if (pathInfo.endsWith(LOGOUT_JSON)) {
			logout(req, rsp);
		} else if (pathInfo.endsWith(GET_RONG_TOKEN_JSON)) {
			getRongToken(req, rsp);
		}
	}

	private SSOAuthService ssoAuthService;

	public void setSsoAuthService(SSOAuthService ssoAuthService) {
		this.ssoAuthService = ssoAuthService;
	}

	private void isClientSessionId(HttpServletRequest req,
			HttpServletResponse rsp) throws IOException {
		String sessionId = req.getParameter("sessionId");

		StringResult sr = new StringResult();
		try {
			boolean flag = ssoAuthService.isClientSessionId(sessionId);
			if (flag) {
				sr.setValue("1");
			} else {
				sr.setValue("0");
			}
		} catch (ServiceException e) {
			sr.setErrCode(e.getErrCode());
			sr.setErrDesc(e.getErrNotice());
		}

		ServletUtil.print(rsp, sr, StringResult.class);
	}

	public void getUser(HttpServletRequest req, HttpServletResponse rsp)
			throws IOException {
		String sessionId = req.getParameter("sessionId");

		User user = null;
		try {
			user = ssoAuthService.getAuth(sessionId);
		} catch (ServiceException e) {
			user = new User();
			user.setErrCode(e.getErrCode());
			user.setErrDesc(e.getErrNotice());
		}
		EntityUtil.resetLazyLoaderManager(user, null);

		ServletUtil.print(rsp, user, User.class);
	}

	public void createClientSession(HttpServletRequest req,
			HttpServletResponse rsp) throws IOException {
		StringResult sr = new StringResult();
		sr.setValue(ssoAuthService.createClientSession());

		ServletUtil.print(rsp, sr, StringResult.class);
	}

	public void login(HttpServletRequest req, HttpServletResponse rsp)
			throws IOException {
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		String sessionId = req.getParameter("sessionId");

		StringResult sr = new StringResult();
		try {
			sessionId = ssoAuthService.login(account, password, sessionId);
			sr.setValue(sessionId);
		} catch (ServiceException e) {
			sr.setErrCode(e.getErrCode());
			sr.setErrDesc(e.getErrNotice());
		}

		ServletUtil.print(rsp, sr, StringResult.class);
	}

	public void logout(HttpServletRequest req, HttpServletResponse rsp)
			throws IOException {
		String sessionId = req.getParameter("sessionId");

		Result sr = new Result();
		ssoAuthService.logout(sessionId);

		ServletUtil.print(rsp, sr, Result.class);
	}

	public void getRongToken(HttpServletRequest req, HttpServletResponse rsp)
			throws IOException {
		String sessionId = req.getParameter("sessionId");
		String resAccountUri = req.getParameter("resAccountUri");

		StringResult sr = new StringResult();
		String value = ssoAuthService.getRongToken(sessionId, resAccountUri);
		sr.setValue(value);

		ServletUtil.print(rsp, sr, StringResult.class);
	}

}
