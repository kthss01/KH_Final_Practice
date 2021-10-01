package com.kh.common.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import com.kh.common.wrapper.EncryptWrapper;

/**
 * Servlet Filter implementation class EncryptFilter
 */
// 이런식으로 작성도 가능한데 web.xml에서 작성하겠다는거
//@WebFilter(filterName="encryptFilter", urlPatterns= {"/insert.me", "/login.me", "/updatePwd.me"})
public class EncryptFilter implements Filter {

    /**
     * Default constructor. 
     */
    public EncryptFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		
		request.setAttribute("originPwd", request.getParameter("userPwd"));

		System.out.println("변경 전 --> " + request.getParameter("userPwd"));

		EncryptWrapper encRequest = new EncryptWrapper((HttpServletRequest) request);
		
		System.out.println("변경 후 --> " + encRequest.getParameter("userPwd"));
		
		// pass the request along the filter chain
		chain.doFilter(encRequest, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
