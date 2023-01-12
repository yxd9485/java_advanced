package com.example.multithreading_all.common.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * module: 应用模块名称<br/>
 * <p>
 * description: 描述<br/>
 *
 * @author XiaoDong.Yang
 * @date: 2023/1/12 17:28
 */
@WebFilter(urlPatterns = "/api/*", filterName = "loginFilter")
@Slf4j
public class CustomizeLoginFilter implements Filter {

    /**
     * 容器加载的时候调用
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("=================init loginFilter=============");
    }


    /**
     * 请求被拦截的时候进行调用
     */
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("doFilter loginFilter");

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;
        String username = req.getParameter("userName");
        log.info("获取用户名:"+username);
        if ("xm".equals(username)) {
            log.info("======");
            filterChain.doFilter(servletRequest,servletResponse);
        } else {
            resp.sendRedirect("/login.html");
        }
    }

    /**
     * 容器被销毁的时候被调用
     */
    @Override
    public void destroy() {
        log.info("=========================destroy loginFilter=====================");
    }

}
