package com.song.account.api;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.song.account.api.result.UserPages;
import com.song.account.entity.User;
import com.song.account.service.UserService;
import com.song.commons.PageInfo;
import com.song.commons.StringUtil;
import com.song.commons.api.Result;
import com.song.commons.entity.EntityUtil;
import com.song.commons.service.General;
import com.song.commons.service.ServiceException;

public class UserServlet extends HttpServlet {

	private static final long serialVersionUID = -8924919322384086101L;

	private static String GET_USER_JSON = "getUser.json";
	private static String GET_USER_LIST_JSON = "getUserList.json";
	private static String REGISTER_JSON = "register.json";

	private static Logger logger = Logger.getLogger(UserServlet.class);

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse rsp)
			throws ServletException, IOException {
		String pathInfo = req.getPathInfo();
		logger.debug("pathInfo: " + pathInfo);
		if (!req.getMethod().equalsIgnoreCase("post")) {
			Result rt = new Result();
			rt.setErrCode(General.GEN_003.getErrCode());
			rt.setErrDesc("HTTP method is not suported for this request.");
			ServletUtil.print(rsp, rt, Result.class);
			return;
		}

		if (pathInfo.endsWith(GET_USER_JSON)) {
			getUser(req, rsp);
			return;
		}
		if (pathInfo.endsWith(GET_USER_LIST_JSON)) {
			getUserList(req, rsp);
			return;
		}
		if (pathInfo.endsWith(REGISTER_JSON)) {
			register(req, rsp);
			return;
		}
	}

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void getUser(HttpServletRequest req, HttpServletResponse rsp)
			throws IOException {
		Long userId = StringUtil.parseLong(req.getParameter("userId"));

		User user = null;
		user = userService.getUserById(userId);
		user.setPassword(null);
		EntityUtil.resetLazyLoaderManager(user, null);

		ServletUtil.print(rsp, user, User.class);
	}

	public void getUserList(HttpServletRequest req, HttpServletResponse rsp)
			throws IOException {
		String nickName = req.getParameter("nickName");
		int currPage = StringUtil.parseInt(req.getParameter("currPage"));
		int pageSize = StringUtil.parseInt(req.getParameter("pageSize"));

		UserPages ups = new UserPages();
		PageInfo<User> userPage = userService.getUserList(nickName, currPage,
				pageSize);
		EntityUtil.resetLazyLoaderManager(userPage.getResult(), null);

		org.springframework.beans.BeanUtils.copyProperties(userPage, ups);
		ups.setUserList(userPage.getResult());

		ServletUtil.print(rsp, ups, UserPages.class);
	}

	public void register(HttpServletRequest req, HttpServletResponse rsp)
			throws IOException {
		String account = req.getParameter("account");
		String password = req.getParameter("password");
		String nick = req.getParameter("nick");

		User user = null;
		try {
			user = userService.register(account, password, nick);
		} catch (ServiceException e) {
			user = new User();
			user.setErrCode(e.getErrCode());
			user.setErrDesc(e.getErrDesc());
			user.setErrNotice(e.getErrNotice());
		} catch (Exception e) {
			user = new User();
			user.setErrCode(General.GEN_001.getErrCode());
			user.setErrDesc("未知异常");
		}

		ServletUtil.print(rsp, user, user.getClass());
	}
}
