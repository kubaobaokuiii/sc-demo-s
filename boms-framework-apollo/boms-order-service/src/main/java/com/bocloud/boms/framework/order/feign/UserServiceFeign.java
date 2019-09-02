package com.bocloud.boms.framework.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: boms-framework
 * @description: 订单服务调用用户服务
 * @author: guo
 * @create: 2019-05-21
 **/
@FeignClient(value = "boms-user-service")
public interface UserServiceFeign {

    @RequestMapping(value = "/user/order/count/user/{id}")
    public String getOrderCountByUserId(@PathVariable(value = "id") Integer id);

}
