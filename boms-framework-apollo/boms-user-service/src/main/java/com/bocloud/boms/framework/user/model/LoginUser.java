package com.bocloud.boms.framework.user.model;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class LoginUser {

  private String adress;
  private String sex;
  private long age;
  private String birthday;
  private String card;
  private String password;
  private String username;
  private long id;
}
