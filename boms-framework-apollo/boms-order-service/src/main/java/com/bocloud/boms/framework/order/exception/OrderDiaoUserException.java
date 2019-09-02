//package com.guo.exception;
//
//import com.guo.Model.LoginUser;
//import com.guo.Service.OrderDiaoUserService;
//import feign.hystrix.FallbackFactory;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.RequestMapping;
//
///**
// * 在订单服务调用用户接口（谁调用在哪里处理异常信息）
// * 处理异常信息方法
// */
//@Component
//public class OrderDiaoUserException implements FallbackFactory<OrderDiaoUserService> {
//
//    @Override
//    public OrderDiaoUserService create(Throwable throwable) {
//        return new OrderDiaoUserService() {
//
//            @Override
//            @RequestMapping("/allUser/{id}")
//            public LoginUser getUserById(Integer id) {
//
//                System.out.println("根据id查询用户失败");
//
//                LoginUser loginUser = new LoginUser();
//
//                loginUser.setId(9);
//
//                loginUser.setUsername("根据id查询用户失败");
//
//                return loginUser;
//            }
//        };
//
//
//    }
//}
