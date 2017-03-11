package com.datax.console.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.datax.console.entity.PredictionResult;
import com.datax.console.helper.DataxContext;
import com.datax.console.helper.DataxUtils;
import com.datax.console.service.PredictionResultService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

/**
 * PredictionController
 * Created by wangzk on 17/3/4.
 */
@Controller
@RequestMapping("/prediction")
public class PredictionController {

  private final PredictionResultService resultService;

  @Autowired
  public PredictionController(PredictionResultService resultService) {
    this.resultService = resultService;
  }

  @RequestMapping("/model/{id}")
  public String model(@PathVariable String id) {
    return "redirect:/prediction/model/" + id + "/potentials";
  }

  @RequestMapping("/model/{id}/potentials")
  public String potentials(@PathVariable String id, Model m, RedirectAttributes r) {
    List<PredictionResult> results = resultService.getResultsByModel(id);
    if (DataxUtils.isNullOrEmpty(results)) {
      r.addFlashAttribute("message", "未找到此模型对应的结果。");
      return "redirect:/overview";
    }

    SimplePropertyPreFilter filter = DataxContext.getPredictionResultCompanyFilter();
    String json = JSON.toJSONString(results, filter);
    m.addAttribute("results", json);
    return "prediction/potentials";
  }

  @RequestMapping("/model/{modelId}/company/{companyId}")
  public String companyDetail(@PathVariable String modelId,
                              @PathVariable String companyId,
                              Model m, RedirectAttributes r) {
    PredictionResult result = resultService.getResult(modelId, companyId);
    if (result == null) {
      r.addFlashAttribute("message", "找不到此公司详情。");
      return "redirect:/overview";
    }
    m.addAttribute("result", result);
    return "prediction/company_detail";
  }

}
