//package com.guo.exception;
//
//import com.guo.Model.FightManagement;
//import com.guo.Service.OrderDiaoHangBanService;
//import feign.hystrix.FallbackFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestMapping;
//
///**
// * 订单服务调用航班服务中根据id确定航班信息
// */
//@Component
//public class OrderDiaoHangBanException implements FallbackFactory<OrderDiaoHangBanService> {
//
//
//    @Override
//    @RequestMapping("/allFight/{id}")
//    public OrderDiaoHangBanService create(Throwable throwable) {
//        /**
//         * 根据id查询航班信息的匿名类
//         */
//        return new OrderDiaoHangBanService() {
//            @Override
//            public FightManagement fightId(Integer id) {
//
//                System.out.println("根据id查询航班信息出现错误");
//                FightManagement fightManagement = new FightManagement();
//                fightManagement.setId(9);
//                fightManagement.setNumber("根据id查询航班号错误");
//                return fightManagement;
//
//            }
//        };
//
//    }
//}
