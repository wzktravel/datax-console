package com.datax.console.controller;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;

import com.datax.console.entity.PredictionModel;
import com.datax.console.entity.User;
import com.datax.console.helper.DataxUtils;
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
  public String login(String url, Model m) {
    if (!Strings.isNullOrEmpty(url)) {
      m.addAttribute("url", url);
    }
    return "user/login";
  }

  @RequestMapping("/logout")
  public String logout(HttpSession session) {
    User user = (User)session.getAttribute("user");
    if (user != null) {
      session.removeAttribute("user");
    }
    return "redirect:/login";
  }

  @RequestMapping("/changepassword")
  public String changePassword() {
    return "user/changepassword";
  }

  @RequestMapping(value = "/validateRegister", method = RequestMethod.POST)
  @ResponseBody
  public Map validateRegister(User user, String repeatPassword, Model m, RedirectAttributes r, HttpSession session) {
    log.info("register : {}", user);
    log.info("repeatPassword : {}", repeatPassword);

    //验证邮箱
    //验证电话
    //验证网址
    //验证用户是否已经存在
    User userExisted = userService.getByEmail(user.getEmail());
    if (userExisted != null) {
      r.addFlashAttribute("message", "此用户已存在。");
      return ImmutableMap.of("status", "0", "message", "此用户已存在。");
    }

    user.setId(DataxUtils.getUUID());
    setSession(session, user);
    int status = userService.insert(user);

    return ImmutableMap.of("status", status);
  }

  @RequestMapping(value = "/validateLogin", method = RequestMethod.POST)
  @ResponseBody
  public Map validateLogin(String email, String password, Model m, RedirectAttributes r, HttpSession session) {
    if (Strings.isNullOrEmpty(email) || Strings.isNullOrEmpty(password)) {
      return ImmutableMap.of("status", "0", "message", "邮箱地址或密码不能为空。");
    }
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

    setSession(session, user);

    return ImmutableMap.of("status", "1");
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

  private void setSession(HttpSession session, User user) {
    session.setAttribute("user", user);
    session.setMaxInactiveInterval(1800); //TODO 配置化
  }

}
