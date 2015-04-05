package com.song.account.api;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.song.account.entity.FriendApply;
import com.song.account.service.FriendService;
import com.song.account.service.SSOAuthService;
import com.song.commons.StringUtil;
import com.song.commons.api.util.GsonUtil;
import com.song.commons.entity.EntityUtil;
import com.song.commons.service.General;
import com.song.commons.service.ServiceException;

public class FriendServlet extends HttpServlet {

	private static final long serialVersionUID = 763318798368883059L;

	private static String SEND_FRIEND_APPLY_JSON = "sendFriendApply.json";

	private static Logger logger = Logger.getLogger(FriendServlet.class);

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse rsp)
			throws ServletException, IOException {
		String pathInfo = req.getPathInfo();
		logger.debug("pathInfo: " + pathInfo);

		if (pathInfo.endsWith(SEND_FRIEND_APPLY_JSON)) {
			sendFriendApply(req, rsp);
			return;
		}
	}

	private FriendService friendService;
	private SSOAuthService ssoAuthService;

	public void setFriendService(FriendService friendService) {
		this.friendService = friendService;
	}

	public void setSsoAuthService(SSOAuthService ssoAuthService) {
		this.ssoAuthService = ssoAuthService;
	}

	public void sendFriendApply(HttpServletRequest req, HttpServletResponse rsp)
			throws IOException {
		String method = req.getMethod();
		FriendApply fa = null;
		if (method.equalsIgnoreCase("post")) {
			String sessionId = req.getParameter("sessionId");
			Long toUserId = StringUtil.parseLong(req.getParameter("toUserId"));
			String reason = req.getParameter("reason");

			try {
				Long fromUserId = ssoAuthService.getUserId(sessionId);
				fa = friendService
						.sendFriendApply(fromUserId, toUserId, reason);
			} catch (ServiceException e) {
				fa = new FriendApply();
				fa.setErrCode(e.getErrCode());
				fa.setErrDesc(e.getErrDesc());
				fa.setErrNotice(e.getErrNotice());
			} catch (Exception e) {
				fa = new FriendApply();
				fa.setErrCode(General.GEN_001.getErrCode());
				fa.setErrNotice("未知异常");
			}

			EntityUtil.resetLazyLoaderManager(fa, null);
		} else {
			fa = new FriendApply();
			fa.setErrCode(General.GEN_003.getErrCode());
			fa.setErrDesc("not count get method.");
		}

		rsp.setContentType("text/json;charset=UTF-8");
		PrintWriter out = rsp.getWriter();
		out.print(GsonUtil.toJson(fa, FriendApply.class));
		out.flush();
		out.close();
	}

}
