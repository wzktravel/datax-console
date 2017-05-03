package com.datax.console.controller;

import com.datax.console.entity.PredictionModel;
import com.datax.console.entity.User;
import com.datax.console.helper.DataxUtils;
import com.datax.console.service.PredictionModelService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;

import lombok.extern.slf4j.Slf4j;

/**
 * ModelController
 * Created by wangzk on 17/5/2.
 */
@Slf4j
@Controller
@RequestMapping("/model")
public class ModelController {

  private final PredictionModelService modelService;

  @Autowired
  public ModelController(PredictionModelService modelService) {
    this.modelService = modelService;
  }

  @RequestMapping(value = {"/", ""})
  public String model() {
    return "redirect:/overview";
  }

  @RequestMapping("/wizard")
  public String newModel(RedirectAttributes r) {
    return "model/model_wizard";
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public String create(String name, String comment, Model m, RedirectAttributes r, HttpSession session) {
    User user = (User)session.getAttribute("user");
    String userId = user.getId();

    PredictionModel model = new PredictionModel();
    model.setUserId(userId);
    model.setName(name);
    model.setComment(comment);

    log.info("user id : {}", userId);
    log.info("model name : {}", name);

    PredictionModel xx = modelService.getByUserAndName(userId, name);
    if (xx != null) {
      r.addFlashAttribute("message", "此模型已经存在。");
      r.addFlashAttribute("model", model);
      return "redirect:/model/wizard";
    }

    String id = DataxUtils.getUUID();
    long now = DataxUtils.getTimeMillis();
    model.setId(id);
    model.setDate(now);

    modelService.insert(model);

    r.addFlashAttribute("model", model);
    return "redirect:/model/" + id + "/basicinfo";
  }

  @RequestMapping("/{id}/wizard")
  public String modelInfo(@PathVariable String id, Model m, RedirectAttributes r) {
    PredictionModel predictionModel = modelService.getById(id);
    m.addAttribute("model", predictionModel);
    return "model/model_wizard";
  }

  @RequestMapping("/{id}/basicinfo")
  public String basicinfo(@PathVariable String id, Model m, RedirectAttributes r) {
    PredictionModel predictionModel = modelService.getById(id);
    m.addAttribute("model", predictionModel);
    return "model/basicinfo";
  }

}
