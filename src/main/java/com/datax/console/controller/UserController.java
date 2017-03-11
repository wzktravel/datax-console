package com.datax.console.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

/**
 * UserController
 * Created by wangzk on 17/3/8.
 */
@Slf4j
@Controller
public class UserController {

  @RequestMapping("/login")
  public String login() {
    return "login";
  }

  @RequestMapping("/overview")
  public String overview() {
    //TODO 获取用户信息

    return "overview";
  }

}
