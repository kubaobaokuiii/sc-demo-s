package com.bocloud.boms.framework.user.fallback;

import com.bocloud.boms.framework.user.feign.OrderServiceFeign;
import org.springframework.stereotype.Service;

/**
 * @program: boms-framework
 * @description:
 * @author: kangxzh
 * @create: 2019-05-08 17:07
 **/
@Service
public class OrderServiceImpl implements OrderServiceFeign {
    @Override
    public String getFightCountByUserId(Integer id) {
        return null;
    }
}
