package com.bocloud.boms.framework.order.fallback;

import com.bocloud.boms.framework.order.feign.UserServiceFeign;
import org.springframework.stereotype.Service;

/**
 * @program: boms-framework
 * @description: 订单服务调用用户服务
 * @author: guo
 * @create: 2019-05-21
 **/
@Service
public class UserServiceImpl implements UserServiceFeign {

    @Override
    public String getOrderCountByUserId(Integer id) {

        return null;
    }


}
