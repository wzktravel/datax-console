package com.datax.console.controller;

import com.alibaba.fastjson.JSON;
import com.datax.console.entity.PredictionModel;
import com.datax.console.entity.User;
import com.datax.console.service.PredictionModelService;
import com.datax.console.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * UserController
 * Created by wangzk on 17/3/8.
 */
@Slf4j
@Controller
public class UserController {

  private final UserService userService;
  private final PredictionModelService modelService;

  @Autowired
  public UserController(UserService userService, PredictionModelService modelService) {
    this.userService = userService;
    this.modelService = modelService;
  }

  @RequestMapping("/login")
  public String login() {
    return "login";
  }

  @RequestMapping("/overview")
  public String overview(Model m, RedirectAttributes r) {
    //TODO 获取用户信息，暂时假设用户id是1

    String userId = "1";
    User user = userService.getById(userId);
    List<PredictionModel> models = modelService.getByUser(userId);

    m.addAttribute("user", user);
    m.addAttribute("models", models);

    return "user/overview";
  }

}
