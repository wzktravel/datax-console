package com.datax.console.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * IndexController
 * Created by wangzk on 17/3/3.
 */
@Controller
public class IndexController {

  @RequestMapping(value = {"/", "/home", "/index"})
  public String index() {
    return "index";
  }

  @RequestMapping("/dash")
  public String dash() {
    return "dashboard";
  }

  @RequestMapping("/model")
  public String model() {
    return "model";
  }



}
