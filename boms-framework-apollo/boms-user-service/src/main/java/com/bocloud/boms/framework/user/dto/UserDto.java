package com.bocloud.boms.framework.user.dto;

import com.bocloud.boms.framework.user.model.LoginUser;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @program: boms-framework
 * @description: 用户业务类
 * @author: kangxzh
 * @create: 2019-05-08 16:00
 **/
@Setter
@Getter
@ToString
public class UserDto extends LoginUser {
    String number;


    public static UserDto copy(LoginUser loginUser) {
        UserDto userDto = new UserDto();
        userDto.setId(loginUser.getId());
        userDto.setAdress(loginUser.getAdress());
        userDto.setAge(loginUser.getAge());
        userDto.setBirthday(loginUser.getBirthday());
        userDto.setCard(loginUser.getCard());
        userDto.setPassword(loginUser.getPassword());
        userDto.setSex(loginUser.getSex());
        userDto.setUsername(loginUser.getUsername());

        return userDto;
    }
}
