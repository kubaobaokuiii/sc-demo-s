package com.bocloud.boms.framework.user.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @program: boms-framework
 * @description:
 * @author: kangxzh
 * @create: 2019-05-08 16:37
 **/
@FeignClient(value = "boms-order-service")
public interface OrderServiceFeign {
    @RequestMapping(value = "flight/count/user/{id}")
    public String getFightCountByUserId(@PathVariable(value = "id") Integer id);
}
