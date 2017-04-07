package com.datax.console.controller;

import com.google.common.collect.ImmutableMap;

import com.datax.console.entity.PredictionModel;
import com.datax.console.entity.User;
import com.datax.console.service.PredictionModelService;
import com.datax.console.service.UserService;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

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

  @RequestMapping("/register")
  public String register() {
    return "user/register";
  }

  @RequestMapping("/login")
  public String login() {
    return "user/login";
  }

  @RequestMapping("/changepassword")
  public String changePassword() {
    return "user/changepassword";
  }

  @RequestMapping(value = "/validateRegister", method = RequestMethod.POST)
  public String validateRegister(Model m, RedirectAttributes r) {
    return "success";
  }

  @RequestMapping(value = "/validateLogin", method = RequestMethod.POST)
  @ResponseBody
  public Map validateLogin(String email, String password, Model m, RedirectAttributes r, HttpSession session) {
    //TODO email和password需要加密处理
    User user = userService.getByEmail(email);
    if (user == null) {
      r.addFlashAttribute("message", "此用户不存在。");
      return ImmutableMap.of("status", "0", "message", "此用户不存在。");
    }

    if (!StringUtils.equals(password, user.getPassword())) {
      r.addFlashAttribute("message", "用户名密码不匹配。");
      return ImmutableMap.of("status", "0", "message", "用户名密码不匹配。");
    }

    session.setAttribute("user", user);
    session.setMaxInactiveInterval(600); //TODO 配置化

    return ImmutableMap.of("status", "1");
  }

  private void validateUserInfo(User user) {

  }

  @RequestMapping("/overview")
  public String overview(Model m, RedirectAttributes r, HttpSession session) {
    User user = (User)session.getAttribute("user");
    String userId = user.getId();
    List<PredictionModel> models = modelService.getByUser(userId);

    m.addAttribute("user", user);
    m.addAttribute("models", models);

    return "user/overview";
  }

}
