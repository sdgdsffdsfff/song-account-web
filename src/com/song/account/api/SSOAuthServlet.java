package com.song.account.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.song.account.entity.User;
import com.song.account.service.SSOAuthService;
import com.song.commons.api.Result;
import com.song.commons.api.StringResult;
import com.song.commons.api.util.GsonUtil;
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

		if (pathInfo.endsWith(GET_USER_JSON)) {
			getUser(req, rsp);
		} else if (pathInfo.endsWith(CREATE_CLIENT_SESSION_JSON)) {
			createClientSession(req, rsp);
		} else if (pathInfo.endsWith(CREATE_CLIENT_SESSION_JSON)) {
			createClientSession(req, rsp);
		} else if (pathInfo.endsWith(IS_CLIENT_SESSION_ID_JSON)) {
			//getClientSessionId(req, rsp);
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

	public void getUser(HttpServletRequest req, HttpServletResponse rsp)
			throws IOException {
		String method = req.getMethod();
		User user = null;
		if (method.equalsIgnoreCase("post")) {
			String sessionId = req.getParameter("sessionId");
			user = ssoAuthService.getAuth(sessionId);
			EntityUtil.resetLazyLoaderManager(user, null);
		} else {
			user = new User();
			user.setErrCode(General.GEN_003.getErrCode());
			user.setErrDesc("not count get method.");
		}

		rsp.setContentType("text/json;charset=UTF-8");
		PrintWriter out = rsp.getWriter();
		out.print(GsonUtil.toJson(user, User.class));
		out.close();
	}

	public void createClientSession(HttpServletRequest req,
			HttpServletResponse rsp) throws IOException {
		String method = req.getMethod();
		StringResult sr = new StringResult();
		if (method.equalsIgnoreCase("post")) {
			sr.setValue(ssoAuthService.createClientSession());
		} else {
			sr.setErrCode(General.GEN_003.getErrCode());
			sr.setErrDesc("not count get method.");
		}

		rsp.setContentType("text/json;charset=UTF-8");
		PrintWriter out = rsp.getWriter();
		out.print(GsonUtil.toJson(sr, StringResult.class));
		out.close();
	}

	public void login(HttpServletRequest req, HttpServletResponse rsp)
			throws IOException {
		String method = req.getMethod();
		StringResult sr = new StringResult();
		if (method.equalsIgnoreCase("post")) {
			String account = req.getParameter("account");
			String password = req.getParameter("password");
			String sessionId = req.getParameter("sessionId");
			try {
				sessionId = ssoAuthService.login(account, password, sessionId);
			} catch (ServiceException e) {
				sr.setErrCode(e.getErrCode());
				sr.setErrDesc(e.getErrNotice());
			}
			sr.setValue(sessionId);
		} else {
			sr.setErrCode(General.GEN_003.getErrCode());
			sr.setErrDesc("not count get method.");
		}

		rsp.setContentType("text/json;charset=UTF-8");
		PrintWriter out = rsp.getWriter();
		out.print(GsonUtil.toJson(sr, StringResult.class));
		out.flush();
		out.close();
	}

	public void logout(HttpServletRequest req, HttpServletResponse rsp)
			throws IOException {
		String method = req.getMethod();
		Result sr = new Result();
		if (method.equalsIgnoreCase("post")) {
			String sessionId = req.getParameter("sessionId");
			ssoAuthService.logout(sessionId);
		} else {
			sr.setErrCode(General.GEN_003.getErrCode());
			sr.setErrDesc("not count get method.");
		}

		rsp.setContentType("text/json;charset=UTF-8");
		PrintWriter out = rsp.getWriter();
		out.print(GsonUtil.toJson(sr, Result.class));
		out.flush();
		out.close();
	}
	
	public void getRongToken(HttpServletRequest req, HttpServletResponse rsp)
			throws IOException {
		String method = req.getMethod();
		StringResult sr = new StringResult();
		if (method.equalsIgnoreCase("post")) {
			String sessionId = req.getParameter("sessionId");
			String resAccountUri = req.getParameter("resAccountUri");
			String value = ssoAuthService.getRongToken(sessionId, resAccountUri);
			sr.setValue(value);
		} else {
			sr.setErrCode(General.GEN_003.getErrCode());
			sr.setErrDesc("not count get method.");
		}

		rsp.setContentType("text/json;charset=UTF-8");
		PrintWriter out = rsp.getWriter();
		out.print(GsonUtil.toJson(sr, StringResult.class));
		out.flush();
		out.close();
	}

}
