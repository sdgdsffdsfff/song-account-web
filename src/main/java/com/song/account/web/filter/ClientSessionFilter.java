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
import com.song.commons.web.Context;
import com.song.commons.web.SSOAuth;

public class ClientSessionFilter implements Filter {

	private static Logger logger = Logger.getLogger(ClientSessionFilter.class);

	private String[] urlPaths;

	public void destroy() {

	}

	public void doFilter(ServletRequest sRequest, ServletResponse sResponse,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) sRequest;
		HttpServletResponse response = (HttpServletResponse) sResponse;

		String url = request.getServletPath();
		logger.info("url: " + url
				+ (request.getPathInfo() != null ? request.getPathInfo() : ""));
		for (String urlPath : urlPaths) {
			if (url.equals(urlPath)) {
				chain.doFilter(request, response);
				return;
			}
		}

		// 更新SESSION的最近访问时间
		SSOAuthService ssoaService = (SSOAuthService) Context.getContext(request).getBean("ssoAuthService");
		SSOAuth<User> ssoa = new SSOAuth<User>(request, response, ssoaService);
		ssoa.updateSessionTime();
		logger.info("sessionId: " + ssoa.getSessionId());
		chain.doFilter(request, response);
	}

	public void init(FilterConfig filterConfig) throws ServletException {
		String noFilterUrl = filterConfig.getInitParameter("no_filter_url");
		urlPaths = noFilterUrl.split(",");
	}
}
