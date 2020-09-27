package com.webischia.api.configurations;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LogAndMetricInterceptor extends HandlerInterceptorAdapter {
    Logger logger = LoggerFactory.getLogger(LogAndMetricInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        logger.info("[preHandle][" + request.getRemoteAddr() + "]" + "[" + request.getMethod()
                + "]" + request.getRequestURI() + " - " + request.getRemoteUser());
        return super.preHandle(request, response, handler);
    }
}
