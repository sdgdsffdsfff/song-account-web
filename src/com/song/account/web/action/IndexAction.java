package com.song.account.web.action;

import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.opensymphony.xwork2.Action;
import com.song.commons.web.RandomValidateCode;

/**
 * 首页管理
 * 
 * @author 张松
 * 
 */
public class IndexAction extends BasicAction {

	public static Logger logger = Logger.getLogger(IndexAction.class);

	/**
	 * 到首页
	 * 
	 * @return
	 */
	public String toIndexPage() {
		return Action.SUCCESS;
	}

	/**
	 * 到不存在的页面
	 * 
	 * @return
	 */
	public String toNotexist() {
		return Action.SUCCESS;
	}

	/**
	 * 显示验证码
	 * 
	 * @return
	 */
	public String showValidateCode() {
		HttpServletResponse response = this.getResponse();
		// 设置相应类型,告诉浏览器输出的内容为图片
		response.setContentType("image/jpeg");
		// 设置响应头信息，告诉浏览器不要缓存此内容
		response.setHeader("Pragma", "No-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expire", 0);

		RandomValidateCode randomValidateCode = new RandomValidateCode();
		// 输出图片方法
		randomValidateCode.getRandcode(this.getRequest(), response);
		return null;
	}

}
