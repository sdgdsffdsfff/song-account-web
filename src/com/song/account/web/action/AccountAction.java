package com.song.account.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionContext;
import com.song.account.entity.BindSite;
import com.song.account.entity.User;
import com.song.account.service.ErrService;
import com.song.account.service.IdentifyCodeService;
import com.song.account.web.bean.ConstantVal;
import com.song.account.web.config.ConfigManager;
import com.song.commons.StringUtil;
import com.song.commons.service.ServiceException;

/**
 * 账号管理
 * 
 * @author 张松
 * 
 */
public class AccountAction extends BasicAction {

	public static Logger logger = Logger.getLogger(AccountAction.class);

	private IdentifyCodeService identifyCodeService;

	public void setIdentifyCodeService(IdentifyCodeService identifyCodeService) {
		this.identifyCodeService = identifyCodeService;
	}

	/**
	 * 到用户基本信息设置页
	 * 
	 * @return
	 */
	public String toUserBasicEdit() {
		User user = this.getCurrUser();
		this.put("user", user);

		List<Integer> years = User.YEARS;
		List<Integer> months = User.MONTHS;
		List<Integer> days = User.DAYS;
		this.put("years", years);
		this.put("months", months);
		this.put("days", days);
		return Action.SUCCESS;
	}

	/**
	 * 编辑用户基本信息
	 * 
	 * @return
	 */
	public String editUserBasic() {
		Long userId = this.getCurrUser().getUserId();
		HttpServletRequest request = this.getRequest();
		String nickName = request.getParameter("nickName").trim();
		String userName = request.getParameter("userName").trim();
		Integer sex = StringUtil.parseInt(request.getParameter("sex"));
		Integer birthdayYear = StringUtil.parseInt(request
				.getParameter("birthdayYear"));
		Integer birthdayMonth = StringUtil.parseInt(request
				.getParameter("birthdayMonth"));
		Integer birthdayDay = StringUtil.parseInt(request
				.getParameter("birthdayDay"));
		String summary = request.getParameter("summary");

		// 通信工具
		String wangwangId = request.getParameter("wangwangId").trim();
		String qqId = request.getParameter("qqId").trim();

		try {
			userService.editUserBasic(userId, nickName, userName, sex,
					birthdayYear, birthdayMonth, birthdayDay, summary, qqId,
					wangwangId);
		} catch (ServiceException e) {
			logger.error("AccountAction.editUserBasic();", e);
			if (e.getErrInfo().equals(ErrService.UserS.ERR_100_002)) {
				this.put("errInfo", "昵称太受欢迎了，请另外选一个");
			} else if (e.getErrInfo().equals(ErrService.UserS.ERR_100_004)) {
				this.put("errInfo", "您的生日填写错误");
			} else if (e.getErrInfo().equals(ErrService.UserS.ERR_100_006)) {
				this.put("errInfo", "昵称格式错误");
			}
			return Action.ERROR;
		} catch (Exception e) {
			logger.error("AccountAction.editUserBasic();", e);
			this.put("errInfo", "用户非法操作，请不要提交恶意信息！");
			return Action.ERROR;
		}

		ActionContext.getContext().getSession()
				.put(ConstantVal.SESSION_OPERATION, "ok");
		this.put("result", "ok");
		return Action.SUCCESS;
	}

	/**
	 * 到用户标签修改页
	 * 
	 * @return
	 */
	public String toUserTagEdit() {
		return Action.SUCCESS;
	}

	/**
	 * 到用户密码修改页
	 * 
	 * @return
	 */
	public String toUserPaswEdit() {
		this.put("currUser", getCurrUser());
		return Action.SUCCESS;
	}

	/**
	 * 编辑用户密码
	 * 
	 * @return
	 */
	public String editUserPasw() {
		HttpServletRequest request = this.getRequest();
		String oldPsw = request.getParameter("paswOld").trim();
		String newPsw = request.getParameter("paswNew").trim();
		String newPsw2 = request.getParameter("paswNew2").trim();
		if (!newPsw.equals(newPsw2)) {
			this.put("errInfo", "确认密码与新密码不一致");
			return Action.INPUT;
		}

		User user = this.getCurrUser();
		try {
			userService.editUserPasswore(user.getUserId(), oldPsw, newPsw);
		} catch (ServiceException e) {
			logger.error("AccountAction.editUserPasw();", e);
			if (e.getErrInfo().equals(ErrService.UserS.ERR_100_007)) {
				this.put("errInfo", "新密码格式错误");
			} else if (e.getErrInfo().equals(ErrService.UserS.ERR_100_003)) {
				this.put("errInfo", "当前密码输入错误");
			}
			return Action.INPUT;
		}

		ActionContext.getContext().getSession()
				.put(ConstantVal.SESSION_OPERATION, "ok");
		return Action.SUCCESS;
	}

	/**
	 * 到用户绑定各大平台的页面
	 * 
	 * @return
	 */
	public String toUserBindSynch() {
		User user = getCurrUser();
		this.put("user", user);

		List<BindSite> bindSiteList = userService.getBindSiteListByUser(user
				.getUserId());
		BindSite biQq = null;
		BindSite biSina = null;
		for (BindSite bi : bindSiteList) {
			if (bi.getSiteMark().equals(BindSite.SiteMark.QQ)) {
				biQq = bi;
			} else if (bi.getSiteMark().equals(BindSite.SiteMark.SINA)) {
				biSina = bi;
			}
		}
		this.put("bindSiteQq", biQq);
		this.put("bindSiteSina", biSina);
		return Action.SUCCESS;
	}

	/**
	 * 到用户头像修改页
	 * 
	 * @return
	 */
	public String toUserPhotoEdit() {
		return Action.SUCCESS;
	}

	/**
	 * 修改用户头像
	 * 
	 * @return
	 * @throws IOException
	 */
	public String editUserPhoto() throws IOException {
		String photoPath = this.getRequest().getParameter("photoPath");
		Long userId = this.getCurrUser().getUserId();
		this.userService.editUserPhoto(userId, photoPath);

		JSONObject jsonObject = new JSONObject();
		PrintWriter out = this.getResponse().getWriter();
		jsonObject.put("result", 1);
		out.print(jsonObject.toString());
		return null;
	}

	/**
	 * 到用户邮箱修改页
	 * 
	 * @return
	 */
	public String toUserEmailEdit() {
		User user = this.getCurrUser();
		if ("edit".equals(this.getRequest().getParameter("result"))) {
			this.put("emailState", 1);
			return Action.SUCCESS;
		}

		if (user.getEmail() == null || user.getEmail().equals("")) {
			// 如果EMAIL为空则没有设置EMAIL
			this.put("emailState", 1);
		} else if (!user.getEmail().equals(user.getEnEmail())) {
			// 如果EMAIL与EN_EMAIL不等
			this.put("emailState", 2);
		} else if (user.getEmail().equals(user.getEnEmail())) {
			// 如果EMAIL与EN_EMAIL相等
			this.put("emailState", 3);
		}

		return Action.SUCCESS;
	}

	/**
	 * 修改用户邮箱
	 * 
	 * @return
	 */
	public String editUserEmail() {
		HttpServletRequest request = this.getRequest();
		String result = request.getParameter("result");
		if ("edit".equals(result)) {
			this.put("result", "edit");
		} else {
			User user = this.getCurrUser();
			String email = request.getParameter("email");
			try {
				userService.editUserEmail(user.getUserId(), email);
			} catch (ServiceException e) {
				logger.error("AccountAction.editUserEmail();", e);
				this.put("errInfo", e.getErrNotice());
				return Action.ERROR;
			}
			ConfigManager cm = ConfigManager.getInstance();
			identifyCodeService.sendEmailIdCode(user.getUserId(),
					cm.getWebsiteAccountUri() + "/member/verifyEmail.html");
		}

		return Action.SUCCESS;
	}

	/**
	 * 验证电子邮件
	 * 
	 * @return
	 */
	public String verifyEmail() {
		HttpServletRequest request = this.getRequest();
		String code = request.getParameter("code");
		User user = this.getCurrUser();
		try {
			identifyCodeService.verifyEmail(user.getUserId(), code);
		} catch (ServiceException e) {
			logger.error("AccountAction.verifyEmail();", e);
			this.put("errInfo", e.getErrNotice());
			return Action.ERROR;
		} catch (Exception e) {
			logger.error("AccountAction.verifyEmail();", e);
			this.put("errInfo", "链接请求错误");
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}

	/**
	 * 删除绑定关系（解除绑定）
	 * 
	 * @return
	 */
	public String delBindSite() {
		HttpServletRequest req = getRequest();
		Long bindSiteId = StringUtil.parseLong(req.getParameter("bindSiteId"));
		try {
			userService.cancelBindSite(bindSiteId);
		} catch (ServiceException e) {
			logger.error("AccountAction.delBindSite();", e);
			this.put("errInfo", e.getErrNotice());
			return Action.ERROR;
		}
		return Action.SUCCESS;
	}
}
