package com.bocloud.boms.framework.flight.fallback;

import com.bocloud.boms.framework.flight.feign.OrderServiceFeign;
import org.springframework.stereotype.Component;

/**
 * @program: boms-framework
 * @description: 航班服务调用订单服务
 * @author: guo
 * @create: 2019-05-21
 **/
@Component
public class OrderServiceImpl implements OrderServiceFeign {
    @Override
    public String getUserCountByFightId(Integer id) throws Exception {
        throw new Exception("发生熔断");
    }

    @Override
    public String hystrix1(Integer num) throws Exception {
        throw new Exception("发生熔断");
    }
}
