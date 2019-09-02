package com.bocloud.boms.framework.user.service;

import com.bocloud.boms.framework.user.model.LoginUser;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "user-service")
public interface LoginUserService {

    /**
     * 根据用户ID获得用户信息
     * @param id
     * @return
     */
    @GetMapping("/user/test/{id}")
    LoginUser getUserById(@PathVariable("id") Integer id);

    /**
     * 根据用户Id获取用户信息(调用测试)
     */
    @GetMapping("/user/test/order/{id}")
    List<LoginUser> getOrderByUserId(@PathVariable("id") Integer id);


    /**
     * 获得所有的用户
     * @return
     */
    @GetMapping("/user")
    List<LoginUser> listUsers();

    /**
     * 根据用户ID删除用户
     * @param id
     */
    @DeleteMapping("/user/{id}")
    void deleteUserById(@PathVariable("id") Integer id);

    /**
     * 更新用户
     * @param
     */
    @PutMapping("/updateUser")
    void updateUser(LoginUser loginUser);

    /**
     * 添加新用户
     * @param user
     */
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    LoginUser insertUser(@RequestBody LoginUser user);

    //根据用户名和密码找用户
    @GetMapping("/user/{username}")
    LoginUser selectByUserNameAndPwd(@PathVariable("username") String username);

    //查询还是修改方法
//    @GetMapping("user/saveOrUpdate")
//    void saveOrUpdate(LoginUser loginUser,Integer id);

    //根据用户名查找用户
    @GetMapping("/userName/{username}")
    LoginUser selectByUserName(@PathVariable("username") String username);

    //查看预定机票的所有用户
//    @GetMapping("/user/fight/{id}")
//    List<LoginUser> fightToUser(@PathVariable("id") Integer id);


}
