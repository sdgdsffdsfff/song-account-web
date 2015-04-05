package com.song.account.api;

import java.io.IOException;
import java.io.PrintWriter;

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
import com.song.commons.api.util.GsonUtil;
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

		if (pathInfo.endsWith(GET_USER_JSON)) {
			getUser(req, rsp);
		} else if (pathInfo.endsWith(GET_USER_LIST_JSON)) {
			getUserList(req, rsp);
		} else if (pathInfo.endsWith(REGISTER_JSON)) {
			register(req, rsp);
		}
	}

	private UserService userService;

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void getUser(HttpServletRequest req, HttpServletResponse rsp)
			throws IOException {
		String method = req.getMethod();
		User user = null;
		if (method.equalsIgnoreCase("post")) {
			Long userId = StringUtil.parseLong(req.getParameter("userId"));
			user = userService.getUserById(userId);
			user.setPassword(null);
			EntityUtil.resetLazyLoaderManager(user, null);
		} else {
			user = new User();
			user.setErrCode(General.GEN_003.getErrCode());
			user.setErrDesc("not count get method.");
		}

		rsp.setContentType("text/json;charset=UTF-8");
		PrintWriter out = rsp.getWriter();
		out.print(GsonUtil.toJson(user, User.class));
		out.flush();
		out.close();
	}

	public void getUserList(HttpServletRequest req, HttpServletResponse rsp)
			throws IOException {
		String method = req.getMethod();
		UserPages ups = new UserPages();
		if (method.equalsIgnoreCase("post")) {
			String nickName = req.getParameter("nickName");
			int currPage = StringUtil.parseInt(req.getParameter("currPage"));
			int pageSize = StringUtil.parseInt(req.getParameter("pageSize"));
			PageInfo<User> userPage = userService.getUserList(nickName,
					currPage, pageSize);
			EntityUtil.resetLazyLoaderManager(userPage.getResult(), null);

			org.springframework.beans.BeanUtils.copyProperties(userPage, ups);
			ups.setUserList(userPage.getResult());
		} else {
			ups.setErrCode(General.GEN_003.getErrCode());
			ups.setErrDesc("not count get method.");
		}

		rsp.setContentType("text/json;charset=UTF-8");
		PrintWriter out = rsp.getWriter();
		out.print(GsonUtil.toJson(ups, ups.getClass()));
		out.flush();
		out.close();
	}

	public void register(HttpServletRequest req, HttpServletResponse rsp)
			throws IOException {
		String method = req.getMethod();
		User user = null;
		if (method.equalsIgnoreCase("post")) {
			String account = req.getParameter("account");
			String password = req.getParameter("password");
			String nick = req.getParameter("nick");
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
		} else {
			user = new User();
			user.setErrCode(General.GEN_003.getErrCode());
			user.setErrDesc("not count get method.");
		}

		rsp.setContentType("text/json;charset=UTF-8");
		PrintWriter out = rsp.getWriter();
		out.print(GsonUtil.toJson(user, user.getClass()));
		out.flush();
		out.close();
	}
}
