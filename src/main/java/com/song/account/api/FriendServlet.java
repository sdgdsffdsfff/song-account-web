package com.song.account.api;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.song.account.api.result.FriendApplyItems;
import com.song.account.api.result.FriendItems;
import com.song.account.api.result.FriendMessageItems;
import com.song.account.entity.Friend;
import com.song.account.entity.FriendApply;
import com.song.account.entity.FriendMessage;
import com.song.account.service.FriendService;
import com.song.account.service.SSOAuthService;
import com.song.commons.StringUtil;
import com.song.commons.api.Result;
import com.song.commons.api.StringResult;
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
		if (!req.getMethod().equalsIgnoreCase("post")) {
			Result rt = new Result();
			rt.setErrCode(General.GEN_003.getErrCode());
			rt.setErrDesc("not count get method.");
			ServletUtil.print(rsp, rt, Result.class);
			return;
		}

		if (pathInfo.endsWith(SEND_FRIEND_APPLY_JSON)) {
			sendFriendApply(req, rsp);
			return;
		}
		if (pathInfo.endsWith("getFriendApplyById.json")) {
			getFriendApplyById(req, rsp);
			return;
		}
		if (pathInfo.endsWith("agreeFriendApply.json")) {
			agreeFriendApply(req, rsp);
			return;
		}
		if (pathInfo.endsWith("delFriendApplyById.json")) {
			delFriendApplyById(req, rsp);
			return;
		}
		if (pathInfo.endsWith("getFriendApplyList.json")) {
			getFriendApplyList(req, rsp);
			return;
		}
		if (pathInfo.endsWith("getFriendApplyBoth.json")) {
			getFriendApplyBoth(req, rsp);
			return;
		}
		if (pathInfo.endsWith("sendFriendMessage.json")) {
			sendFriendMessage(req, rsp);
			return;
		}
		if (pathInfo.endsWith("getFriendMessageListByFayId.json")) {
			getFriendMessageListByFayId(req, rsp);
			return;
		}
		if (pathInfo.endsWith("getFriendListByUserId.json")) {
			getFriendListByUserId(req, rsp);
			return;
		}
		if (pathInfo.endsWith("delFriendById.json")) {
			delFriendById(req, rsp);
			return;
		}
		if (pathInfo.endsWith("isFriend.json")) {
			isFriend(req, rsp);
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
		String sessionId = req.getParameter("sessionId");
		Long toUserId = StringUtil.parseLong(req.getParameter("toUserId"));
		String reason = req.getParameter("reason");

		FriendApply fa = null;
		try {
			Long fromUserId = ssoAuthService.getUserId(sessionId);
			fa = friendService.sendFriendApply(fromUserId, toUserId, reason);
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

		ServletUtil.print(rsp, fa, FriendApply.class);
	}

	public void getFriendApplyById(HttpServletRequest req,
			HttpServletResponse rsp) throws IOException {
		String sessionId = req.getParameter("sessionId");
		Long fayId = StringUtil.parseLong(req.getParameter("fayId"));

		FriendApply fa = null;
		try {
			ssoAuthService.getUserId(sessionId);
			fa = friendService.getFriendApplyById(fayId);
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

		ServletUtil.print(rsp, fa, FriendApply.class);
	}

	public void agreeFriendApply(HttpServletRequest req, HttpServletResponse rsp)
			throws IOException {
		String sessionId = req.getParameter("sessionId");
		Long fayId = StringUtil.parseLong(req.getParameter("fayId"));

		Result sr = new Result();
		try {
			ssoAuthService.getUserId(sessionId);
			friendService.agreeFriendApply(fayId);
		} catch (ServiceException e) {
			sr.setErrCode(e.getErrCode());
			sr.setErrDesc(e.getErrDesc());
			sr.setErrNotice(e.getErrNotice());
		} catch (Exception e) {
			sr.setErrCode(General.GEN_001.getErrCode());
			sr.setErrNotice("未知异常");
		}

		ServletUtil.print(rsp, sr, sr.getClass());
	}

	public void delFriendApplyById(HttpServletRequest req,
			HttpServletResponse rsp) throws IOException {
		String sessionId = req.getParameter("sessionId");
		Long fayId = StringUtil.parseLong(req.getParameter("fayId"));

		Result sr = new Result();
		try {
			long opnUserId = ssoAuthService.getUserId(sessionId);
			friendService.delFriendApplyById(fayId, opnUserId);
		} catch (ServiceException e) {
			sr.setErrCode(e.getErrCode());
			sr.setErrDesc(e.getErrDesc());
			sr.setErrNotice(e.getErrNotice());
		} catch (Exception e) {
			sr.setErrCode(General.GEN_001.getErrCode());
			sr.setErrNotice("未知异常");
		}

		ServletUtil.print(rsp, sr, sr.getClass());
	}

	public void getFriendApplyList(HttpServletRequest req,
			HttpServletResponse rsp) throws IOException {
		String sessionId = req.getParameter("sessionId");

		FriendApplyItems fais = new FriendApplyItems();
		try {
			long userId = ssoAuthService.getUserId(sessionId);
			List<FriendApply> faisList = friendService
					.getFriendApplyList(userId);
			fais.setFriendApplyList(faisList);
		} catch (ServiceException e) {
			fais.setErrCode(e.getErrCode());
			fais.setErrDesc(e.getErrDesc());
			fais.setErrNotice(e.getErrNotice());
		} catch (Exception e) {
			fais.setErrCode(General.GEN_001.getErrCode());
			fais.setErrNotice("未知异常");
		}

		ServletUtil.print(rsp, fais, fais.getClass());
	}

	public void getFriendApplyBoth(HttpServletRequest req,
			HttpServletResponse rsp) throws IOException {
		String sessionId = req.getParameter("sessionId");
		Long toUserId = StringUtil.parseLong(req.getParameter("toUserId"));

		FriendApply fa = null;
		try {
			long userId = ssoAuthService.getUserId(sessionId);
			fa = friendService.getFriendApplyBoth(userId, toUserId);
		} catch (ServiceException e) {
			fa.setErrCode(e.getErrCode());
			fa.setErrDesc(e.getErrDesc());
			fa.setErrNotice(e.getErrNotice());
		} catch (Exception e) {
			fa.setErrCode(General.GEN_001.getErrCode());
			fa.setErrNotice("未知异常");
		}

		ServletUtil.print(rsp, fa, fa.getClass());
	}

	public void sendFriendMessage(HttpServletRequest req,
			HttpServletResponse rsp) throws IOException {
		String sessionId = req.getParameter("sessionId");
		Long fayId = StringUtil.parseLong(req.getParameter("fayId"));
		Long toUserId = StringUtil.parseLong(req.getParameter("toUserId"));
		String message = req.getParameter("message");

		FriendMessage fm = null;
		try {
			long fromUserId = ssoAuthService.getUserId(sessionId);
			fm = friendService.sendFriendMessage(fayId, fromUserId, toUserId,
					message);
		} catch (ServiceException e) {
			fm.setErrCode(e.getErrCode());
			fm.setErrDesc(e.getErrDesc());
			fm.setErrNotice(e.getErrNotice());
		} catch (Exception e) {
			fm.setErrCode(General.GEN_001.getErrCode());
			fm.setErrNotice("未知异常");
		}

		ServletUtil.print(rsp, fm, fm.getClass());
	}

	public void getFriendMessageListByFayId(HttpServletRequest req,
			HttpServletResponse rsp) throws IOException {
		String sessionId = req.getParameter("sessionId");
		Long fayId = StringUtil.parseLong(req.getParameter("fayId"));

		FriendMessageItems fmis = new FriendMessageItems();
		try {
			ssoAuthService.getUserId(sessionId);
			List<FriendMessage> fmisList = friendService
					.getFriendMessageListByFayId(fayId);
			fmis.setFriendMessageList(fmisList);
		} catch (ServiceException e) {
			fmis.setErrCode(e.getErrCode());
			fmis.setErrDesc(e.getErrDesc());
			fmis.setErrNotice(e.getErrNotice());
		} catch (Exception e) {
			fmis.setErrCode(General.GEN_001.getErrCode());
			fmis.setErrNotice("未知异常");
		}

		ServletUtil.print(rsp, fmis, fmis.getClass());
	}

	public void getFriendListByUserId(HttpServletRequest req,
			HttpServletResponse rsp) throws IOException {
		String sessionId = req.getParameter("sessionId");

		FriendItems fmis = new FriendItems();
		try {
			long userId = ssoAuthService.getUserId(sessionId);
			List<Friend> fmisList = friendService.getFriendListByUserId(userId);
			fmis.setFriendList(fmisList);
		} catch (ServiceException e) {
			fmis.setErrCode(e.getErrCode());
			fmis.setErrDesc(e.getErrDesc());
			fmis.setErrNotice(e.getErrNotice());
		} catch (Exception e) {
			fmis.setErrCode(General.GEN_001.getErrCode());
			fmis.setErrNotice("未知异常");
		}

		ServletUtil.print(rsp, fmis, fmis.getClass());
	}

	public void delFriendById(HttpServletRequest req, HttpServletResponse rsp)
			throws IOException {
		String sessionId = req.getParameter("sessionId");
		Long friendId = StringUtil.parseLong(req.getParameter("friendId"));

		Result sr = new Result();
		try {
			long userId = ssoAuthService.getUserId(sessionId);
			friendService.delFriendById(userId, friendId);
		} catch (ServiceException e) {
			sr.setErrCode(e.getErrCode());
			sr.setErrDesc(e.getErrDesc());
			sr.setErrNotice(e.getErrNotice());
		} catch (Exception e) {
			sr.setErrCode(General.GEN_001.getErrCode());
			sr.setErrNotice("未知异常");
		}

		ServletUtil.print(rsp, sr, sr.getClass());
	}

	public void isFriend(HttpServletRequest req, HttpServletResponse rsp)
			throws IOException {
		String sessionId = req.getParameter("sessionId");
		Long aUserId = StringUtil.parseLong(req.getParameter("aUserId"));
		Long bUserId = StringUtil.parseLong(req.getParameter("bUserId"));

		StringResult sr = new StringResult();
		try {
			ssoAuthService.getUserId(sessionId);
			boolean b = friendService.isFriend(aUserId, bUserId);
			if (b) {
				sr.setValue("1");
			} else {
				sr.setValue("0");
			}
		} catch (ServiceException e) {
			sr.setErrCode(e.getErrCode());
			sr.setErrDesc(e.getErrDesc());
			sr.setErrNotice(e.getErrNotice());
		} catch (Exception e) {
			sr.setErrCode(General.GEN_001.getErrCode());
			sr.setErrNotice("未知异常");
		}

		ServletUtil.print(rsp, sr, sr.getClass());
	}
}
