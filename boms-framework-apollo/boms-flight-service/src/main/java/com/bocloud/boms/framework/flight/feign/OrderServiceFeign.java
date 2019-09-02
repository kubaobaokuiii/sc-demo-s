package com.bocloud.boms.framework.flight.feign;

import com.bocloud.boms.framework.flight.fallback.OrderServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @program: boms-framework
 * @description: 航班服务调用订单服务
 * @author: guo
 * @create: 2019-05-21
 **/
@FeignClient(value = "boms-order-service", fallback = OrderServiceImpl.class)
public interface OrderServiceFeign {

    @RequestMapping(value = "user/count/flight/{id}")
    public String getUserCountByFightId(@PathVariable(value = "id") Integer id) throws Exception;

    @RequestMapping(value = "hystrix1", method = RequestMethod.GET)
    public String hystrix1(@RequestParam(value = "num") Integer num) throws Exception;


}
