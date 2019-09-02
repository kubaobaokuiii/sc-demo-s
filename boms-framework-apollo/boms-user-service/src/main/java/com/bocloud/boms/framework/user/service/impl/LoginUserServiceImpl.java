package com.bocloud.boms.framework.user.service.impl;


import com.bocloud.boms.framework.user.mapper.LoginUserMapper;
import com.bocloud.boms.framework.user.model.LoginUser;
import com.bocloud.boms.framework.user.service.LoginUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 郭双
 */
@RestController
public class LoginUserServiceImpl implements LoginUserService {

    @Autowired
    private LoginUserMapper loginUserMapper;



    //@Override
    public LoginUser getUserById(@PathVariable("id") Integer id) {
        return loginUserMapper.findById(id);
    }

    /**
     * 订单服务调用用户服务测试
     * @param id
     * @return
     */
    @Override
    public List<LoginUser> getOrderByUserId(Integer id) {
        return null;
    }

    //@Override
    public List<LoginUser> listUsers() {
        return loginUserMapper.findAll();
    }

    //@Override
    public void deleteUserById(@PathVariable("id") Integer id) {
        loginUserMapper.deleteById(id);
    }

    //@Override
    public void updateUser(@RequestBody LoginUser loginUser) {
       loginUserMapper.update(loginUser);
    }

    //@Override
    public LoginUser insertUser(@RequestBody LoginUser user) {
        Integer userId = loginUserMapper.insert(user);
        user.setId(userId);
        return user;
    }

    //根据用户名和密码查询用户
    //@Override
    @GetMapping("/user/{username}")
    public LoginUser selectByUserNameAndPwd(String username) {

        return loginUserMapper.selectByUserNameAndPwd(username);
    }

    //根据用户名查找用户

    //根据用户名和密码查询用户
    //@Override
    @GetMapping("/userName/{username}")
    public LoginUser selectByUserName(String username) {

        return loginUserMapper.selectByUserName(username);
    }

    /**
     * 从机票查看预定此票的所有用户
     */
//    @Override
//    public  List<LoginUser> fightToUser(@PathVariable("id") Integer id) {
//
//        return loginUserMapper.fightToUser(id);
//
//    }

}
