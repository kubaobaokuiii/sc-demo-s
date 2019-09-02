package com.bocloud.boms.framework.order.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpRequestInterceptor;
import org.apache.http.protocol.HttpContext;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @program: boms-framework
 * @description:
 * @author: kangxzh
 * @create: 2019-05-10 14:55
 **/
//@Component
@Slf4j
public class HttpRequestInterceptorTest implements HttpRequestInterceptor {
    @Override
    public void process(HttpRequest httpRequest, HttpContext httpContext) throws HttpException, IOException {
        log.info("HttpRequestInterceptorTest interceptor [{}]", httpRequest.getRequestLine().getUri());
    }
}
