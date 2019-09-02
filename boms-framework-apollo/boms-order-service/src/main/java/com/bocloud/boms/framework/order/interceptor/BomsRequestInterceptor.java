package com.bocloud.boms.framework.order.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: boms-framework
 * @description:
 * @author: kangxzh
 * @create: 2019-05-10 14:21
 **/
//@Component
@Slf4j
public class BomsRequestInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        log.info("拦截到了---url [{}]", request.getRequestURI());
        return true;
    }
}
