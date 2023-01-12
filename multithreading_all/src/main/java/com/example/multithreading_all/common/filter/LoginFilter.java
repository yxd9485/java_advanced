package com.example.multithreading_all.common.filter;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import java.io.IOException;
import java.util.logging.LogRecord;

/**
 * module: 应用模块名称<br/>
 * <p>
 * description: 描述<br/>
 *
 * @author XiaoDong.Yang
 * @date: 2023/1/12 15:27
 */
@WebFilter(filterName = "FirstFilter",urlPatterns = "/first")
public class LoginFilter implements Filter {

    public boolean isLoggable(LogRecord record) {
        return false;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        System.out.println("进入Filter");
        filterChain.doFilter(servletRequest, servletResponse);
        System.out.println("离开Filter");
    }

    @Override
    public void destroy() {

    }

}
