package com.song.account.api;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

public class ServletToBeanProxy extends GenericServlet {

	private static final long serialVersionUID = 5516756350096993583L;

	private static final String TARGET_BEAN = "targetBean";

	/** 请求的Servlet名字 */
	private String targetBean;
	/** 代理Servlet */
	private Servlet proxy;

	@Override
	public void init() throws ServletException {
		super.init();
		// 初始化Spring容器
		WebApplicationContext wac = WebApplicationContextUtils
				.getRequiredWebApplicationContext(getServletContext());

		this.targetBean = getInitParameter(TARGET_BEAN);
		// 调用ServletBean
		this.proxy = (Servlet) wac.getBean(targetBean);
		// 调用初始化方法将ServletConfig传给Bean
		proxy.init(getServletConfig());
	}

	@Override
	public void service(ServletRequest request, ServletResponse response)
			throws ServletException, IOException {
		// 在service方法中调用bean的service方法，
		// servlet会根据客户的请求去调用相应的请求方法（Get/Post）
		proxy.service(request, response);
	}

}
