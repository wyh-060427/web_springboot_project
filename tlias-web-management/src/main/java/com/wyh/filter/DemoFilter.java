package com.wyh.filter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
//@WebFilter(urlPatterns = "/*")//拦截所有请求
@Slf4j
public class DemoFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init 初始化方法 ....");

    }
    //拦截到请求之后，执行，会执行对此
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        log.info("拦截到了请求");
        //放行
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        log.info("destroy销毁方法....");
    }
}
