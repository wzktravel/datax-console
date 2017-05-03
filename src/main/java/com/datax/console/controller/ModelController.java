package com.datax.console.controller;

import com.google.common.base.Strings;

import com.datax.console.entity.ModelCompanyInfo;
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

  @RequestMapping("/{id}/wizard")
  public String modelWizard(@PathVariable String id, Model m, RedirectAttributes r) {
    PredictionModel predictionModel = modelService.getById(id);
    if (predictionModel == null) {
      m.addAttribute("message", "此模型不存在。");
    } else {
      m.addAttribute("model", predictionModel);
    }
    return "model/model_wizard";
  }

  @RequestMapping("/{id}/companyinfo")
  public String companyinfo(@PathVariable String id, Model m, RedirectAttributes r) {
    PredictionModel predictionModel = modelService.getById(id);
    ModelCompanyInfo companyInfo = modelService.getCompanyInfoByModel(id);
    if (predictionModel == null) {
      m.addAttribute("message", "此模型不存在。");
    } else {
      m.addAttribute("model", predictionModel);
      m.addAttribute("companyInfo", companyInfo);
    }
    return "model/company_info";
  }

  @RequestMapping("/{id}/customerlist")
  public String customerlist(@PathVariable String id, Model m, RedirectAttributes r) {
    PredictionModel predictionModel = modelService.getById(id);
    if (predictionModel == null) {
      m.addAttribute("message", "此模型不存在。");
    } else {
      m.addAttribute("model", predictionModel);
    }
    return "model/customer_list";
  }

  @RequestMapping(value = "/create", method = RequestMethod.POST)
  public String create(String id, String name, String comment, Model m, RedirectAttributes r, HttpSession session) {
    if (Strings.isNullOrEmpty(id)) {  //第一次创建
      User user = (User)session.getAttribute("user");
      String userId = user.getId();

      PredictionModel model = new PredictionModel();
      model.setUserId(userId);
      model.setName(name);
      model.setComment(comment);

      PredictionModel xx = modelService.getByUserAndName(userId, name);
      if (xx != null) {
        r.addFlashAttribute("message", "此模型已经存在。");
        r.addFlashAttribute("model", model);
        return "redirect:/model/wizard";
      }

      String newId = DataxUtils.getUUID();
      long now = DataxUtils.getTimeMillis();
      model.setId(newId);
      model.setDate(now);

      modelService.insert(model);
      r.addFlashAttribute("model", model);
      return "redirect:/model/" + newId + "/companyinfo";
    } else {  // 更新，只能更新comment
      PredictionModel model = modelService.getById(id);
      model.setComment(comment);
      modelService.update(model);
      r.addFlashAttribute("model", model);
      return "redirect:/model/" + id + "/companyinfo";
    }
  }

  @RequestMapping(value = "/companyinfo", method = RequestMethod.POST)
  public String addCompanyInfo(ModelCompanyInfo companyInfo, Model m, RedirectAttributes r) {
    log.info("Model Company Info: {}", companyInfo);
    String modelId = companyInfo.getModelId();
    modelService.insertOrUpdateCompanyInfo(companyInfo);

    PredictionModel model = modelService.getById(modelId);
    r.addFlashAttribute("model", model);
    return "redirect:/model/" + modelId + "/customerlist";
  }
}
