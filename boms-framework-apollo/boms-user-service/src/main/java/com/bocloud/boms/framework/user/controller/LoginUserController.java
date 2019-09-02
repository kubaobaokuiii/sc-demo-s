package com.bocloud.boms.framework.user.controller;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.ObjectUtil;
import com.bocloud.boms.framework.user.dto.UserDto;
import com.bocloud.boms.framework.user.feign.OrderServiceFeign;
import com.bocloud.boms.framework.user.model.LoginUser;
import com.bocloud.boms.framework.user.service.LoginUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.InternalResourceView;
import org.springframework.web.servlet.view.RedirectView;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/user")
@Slf4j
@Api(value = "user", description = "boms-user-service服务")
@ApiIgnore
public class LoginUserController {

    @Autowired
    LoginUserService loginUserService;

    @Autowired
    OrderServiceFeign orderServiceFeign;

    @Autowired
    Environment environment;


    public static final String LOGIN_USER = "username";

    /**
     * 判断是添加还是修改的界面
     */
//    @RequestMapping("/saveOrUpdate")
//    public void saveOrUpdate(LoginUser loginUser){
//
//        //判断是否添加还是修改
//        if((Long)loginUser.getId() != null){
//
//            //修改
//            loginUserService.updateUser();
//
//        }else {
//
//            //添加
//            loginUserService.insertUser(loginUser);
//
//        }
//
//    }

//    /**
//     * 进入saveuser.html界面
//     */
//    @RequestMapping("saveUser")
//    public ModelAndView saveUser(){
//
//        return new ModelAndView(new InternalResourceView("/saveuser.jsp"));
//    }

    /**
     * 去订单服务调用用户服务
     */
    @RequestMapping(value = "/order/count/user/{id}")
    @ApiOperation(value = "order to user", notes = "订单服务调用用户服务")
    public String getOrderCountByUserId(@PathVariable(value = "id") Integer id){
        log.info("getOrderCountByUserId id [{}]",id);
        List<LoginUser> orders = loginUserService.getOrderByUserId(id);
        String result = "当前服务端口["+environment.getProperty("local.server.port")+"],订单数";
        if(CollUtil.isEmpty(orders)){
            result = result + 0;
        }else{
            result = result + orders.size();
        }
//        log.info("测试代码[{}]",1/0);
        return result;
    }

    /**
     * 查询所有用户(right)
     */
    @RequestMapping("/allUser")
    @ApiOperation(value = "select all users", notes = "查询所有用户")
    public List<UserDto> listUsers(Model model) {
        List<LoginUser> list =  loginUserService.listUsers();
        List<UserDto> userDtos = CollUtil.newArrayList();
        if (!CollUtil.isEmpty(list)) {
            list.forEach(userModel -> {
                UserDto userDto = UserDto.copy(userModel);
                String number = orderServiceFeign.getFightCountByUserId((int)userModel.getId());
                userDto.setNumber(number);
                userDtos.add(userDto);
            });
        }

        model.addAttribute("loginUser",userDtos);

        return userDtos;
    }


    /**
     * 根据id查询用户(right)
     */

    @RequestMapping("/allUser/{id}")
    @ApiOperation(value = "select user", notes = "根据id查看用户")
    public LoginUser getUserById(@PathVariable("id") Integer id,Model model) {

        LoginUser loginUser = loginUserService.getUserById(id);

        model.addAttribute("loginUser",loginUser);

        System.out.println(loginUser);

        return loginUser;
    }

    /**
     * 增加新用户(right)
     */
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ApiOperation(value = "add user", notes = "新增用户")
    public LoginUser insertUser(@RequestBody LoginUser user) {
        LoginUser userId = loginUserService.insertUser(user);
        return userId;
    }

    /**
     * 修改新用户
     */
    @RequestMapping("/updateUser")
    @ApiOperation(value = "update user", notes = "修改用户信息")
    public void updateUser(@RequestBody LoginUser loginUser) {

         loginUserService.updateUser(loginUser);
    }

    /**
     * 删除用户(right)
     */
    @RequestMapping("/deleteUser/{id}")
    @ApiOperation(value = "delete user", notes = "删除用户")
    public void deleteUserById(@PathVariable("id") Integer id) {

        loginUserService.deleteUserById(id);
    }

    /**
     * 用户的登录与注册退出
     */
    @RequestMapping("login")
    public ModelAndView login(){

        return new ModelAndView(new InternalResourceView("/login.html"));

    }

    //进入登录成功列表界面
    @RequestMapping("list")
    public ModelAndView list(){

        return new ModelAndView(new InternalResourceView("/index.html"));
    }

    @RequestMapping("saveLogin/{username}")
    public ModelAndView saveLogin(@PathVariable("username") String username, HttpSession session){

        ModelAndView modelAndView = new ModelAndView();

        LoginUser user = loginUserService.selectByUserNameAndPwd(username);

        if(user != null){

            //登录成功
            session.setAttribute(LOGIN_USER, user);

//            //重定向
//            modelAndView.setView(new RedirectView("/list",true));
//
//            return modelAndView;

        }

        modelAndView.addObject("msg", "用户名或密码错误！");
//
//        modelAndView.setView(new InternalResourceView("/login.html"));

        return modelAndView;
    }

    @RequestMapping("logout")
    public ModelAndView logout(HttpSession session){

        session.removeAttribute(LOGIN_USER);

        return new ModelAndView(new RedirectView("/login",true));

    }

    /**
     * 查询用户的列表部分(接前台方法)
     */
    @RequestMapping("/userList")
    public ModelAndView list(Model model){

        List<LoginUser> list = loginUserService.listUsers();

        model.addAttribute("userList",list);

        return new ModelAndView(new RedirectView("/list",true));


    }

    /**
     * 根据机票查看预定此用户的所有信息
     */

//    @RequestMapping("/fightToUser/{id}")
//    public  List<LoginUser> userToFight(@PathVariable("id") Integer id, Model model) {
//
//        List<LoginUser> userToFight = loginUserService.fightToUser(id);
//
//        model.addAttribute("userToFight",userToFight);
//
//        return userToFight;
//
//    }






}
