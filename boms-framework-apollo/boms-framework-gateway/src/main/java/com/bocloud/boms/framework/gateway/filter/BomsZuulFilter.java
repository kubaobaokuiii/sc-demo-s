//package com.bocloud.boms.framework.gateway.filter;
//
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.http.HttpServletRequest;
//
///**
// * @program: boms-framework
// * @description:
// * @author: kangxzh
// * @create: 2019-05-09 10:42
// **/
//@Component
//public class BomsZuulFilter extends ZuulFilter {
//
//    @Value("${govern.auth.authHeader}")
//    private String authHeader;
//
//    @Value("${spring.application.name}")
//    private String appName;
//
//    @Override
//    public String filterType() {
//        return "pre";
//    }
//
//    @Override
//    public int filterOrder() {
//        return 1;
//    }
//
//    @Override
//    public boolean shouldFilter() {
//        return true;
//    }
//
//    @Override
//    public Object run() throws ZuulException {
//        RequestContext ctx = RequestContext.getCurrentContext();
//        HttpServletRequest request = ctx.getRequest();
//        ctx.addZuulRequestHeader(authHeader, appName);
//        return null;
//    }
//}
