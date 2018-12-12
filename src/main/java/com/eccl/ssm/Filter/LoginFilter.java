/**
 * 
 */
package com.eccl.ssm.Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * TODO 添加本类的描述，可多行，内容包含功能、使用条件等
 *
 * @author wzc
 * @date 2017年12月8日 下午1:43:37
 *
 */
public class LoginFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		System.out.println("111");
		String requestURI = req.getRequestURI();
		System.out.println("请求路径:" + requestURI);
		if (!requestURI.equals("/login.action")) {
			System.out.println(req.getSession().getAttribute("user"));
			if (null == req.getSession().getAttribute("user")) {
				resp.sendRedirect(req.getContextPath() + "/index.jsp");
			}
		}
		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
	}
}
