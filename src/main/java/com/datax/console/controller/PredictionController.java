package com.datax.console.controller;

import com.google.common.base.Strings;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.datax.console.entity.PredictionModel;
import com.datax.console.entity.PredictionResult;
import com.datax.console.helper.DataxContext;
import com.datax.console.service.PredictionModelService;
import com.datax.console.service.PredictionResultService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * PredictionController
 * Created by wangzk on 17/3/4.
 */
@Controller
@RequestMapping("/prediction")
public class PredictionController {

  private final PredictionModelService modelService;
  private final PredictionResultService resultService;

  @Autowired
  public PredictionController(PredictionModelService modelService,
                              PredictionResultService resultService) {
    this.modelService = modelService;
    this.resultService = resultService;
  }

  @RequestMapping("/model/{id}")
  public String model(@PathVariable("id") String id,
                      Model model) {
    if (Strings.isNullOrEmpty(id)) {
      //TODO report error msg
      return "error";
    }
    PredictionModel predictionModel = modelService.getById(id);
    model.addAttribute("model", predictionModel);
    return "prediction/model";
  }

  @RequestMapping("/model/{id}/potentials")
  public String potentials(@PathVariable("id") String id,
                           Model model) {
    if (Strings.isNullOrEmpty(id)) {
      //TODO report error msg
      return "error";
    }
    List<PredictionResult> results = resultService.getResultsByModel(id);
    SimplePropertyPreFilter filter = DataxContext.getPredictionResultCompanyFilter();
    String json = JSON.toJSONString(results, filter);
    model.addAttribute("results", Strings.isNullOrEmpty(json) ? "[]" : json);
    return "prediction/potentials";
  }

  @RequestMapping("/model/{modelId}/company/{companyId}")
  public String companyDetail(@PathVariable("modelId") String modelId,
                       @PathVariable("companyId") String companyId,
                       Model model) {
    if (Strings.isNullOrEmpty(modelId)) {
      //TODO report error msg
      return "error";
    }
    if (Strings.isNullOrEmpty(companyId)) {
      //TODO report error msg
      return "error";
    }

    PredictionResult result = resultService.getResult(modelId, companyId);
    model.addAttribute("result", result);
    //TODO show
    return "prediction/company_detail";

  }


}
