package com.bocloud.boms.framework.gateway.filter;

//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;
//import com.netflix.zuul.exception.ZuulException;

import com.ctrip.framework.apollo.core.spi.Ordered;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

//import javax.servlet.http.HttpServletRequest;
import java.awt.image.DataBuffer;
import java.util.List;

/**
 * @program: boms-framework
 * @description:
 * @author: guo
 * @create: 2019-05-31
 **/
@Component
public class BomsGateWayFilter implements GlobalFilter, Ordered {

    @Value("${govern.auth.authHeader}")
    private String authHeader;

    @Value("${spring.application.name}")
    private String appName;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        //获取header
        ServerHttpRequest request = exchange.getRequest();
        ServerHttpRequest.Builder mutate = request.mutate();
        mutate.header(authHeader, appName);
        ServerHttpRequest build = mutate.build();
        return chain.filter(exchange.mutate().request(build).build());
    }

    @Override
    public int getOrder() {

        return 1;

    }


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


}
