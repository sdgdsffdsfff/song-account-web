package com.song.account.web.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.song.account.entity.User;
import com.song.account.service.SSOAuthService;
import com.song.account.web.config.ConfigManager;
import com.song.commons.web.Context;
import com.song.commons.web.SSOAuth;

public class CheckLoginFilter implements Filter {

	private static Logger logger = Logger.getLogger(CheckLoginFilter.class);
	
	private String[] urlPaths;

	public void destroy() {

	}

	public void doFilter(ServletRequest sRequest, ServletResponse sResponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) sRequest;
		HttpServletResponse response = (HttpServletResponse) sResponse;

		String url = request.getServletPath();
		logger.debug("url: " + url);
		for (String urlPath : urlPaths) {
			if (url.equals(urlPath)) {
				chain.doFilter(request, response);
				return;
			}
		}

		SSOAuthService ssoaService = (SSOAuthService) Context.getContext(
				request).getBean("ssoAuthService");
		// 如果账号没有登入，跳转到登入页面
		SSOAuth<User> ia = new SSOAuth<User>(request, response, ssoaService);
		User user = ia.getCurrAuth();
		if (user == null) {
			response.sendRedirect(ConfigManager.getInstance()
					.getWebsiteAccountUri() + "/login.html");
			return;
		}
		logger.debug("user: " + user.toString());
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		String noFilterUrl = filterConfig.getInitParameter("no_filter_url");
		urlPaths = noFilterUrl.split(",");
	}
}
