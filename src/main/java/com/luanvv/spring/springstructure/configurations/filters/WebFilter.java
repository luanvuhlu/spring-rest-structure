package com.luanvv.spring.springstructure.configurations.filters;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class WebFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Do not need implement
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse response = (HttpServletResponse) res;
		chain.doFilter(req, response);
	}

	public void destroy() {
		// Do not need implement
	}
}
