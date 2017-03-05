package com.datax.console.service;

import com.google.common.collect.Lists;

import com.datax.console.entity.PredictionResult;
import com.datax.console.mapper.PredictionResultMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * PredictionResultService
 * Created by wangzk on 17/3/4.
 */
@Slf4j
@Service
public class PredictionResultService {

  private final PredictionResultMapper predictionResultMapper;

  @Autowired
  public PredictionResultService(PredictionResultMapper predictionResultMapper) {
    this.predictionResultMapper = predictionResultMapper;
  }

  public List<PredictionResult> getResultsByModel(String modelId) {
    List<PredictionResult> results = predictionResultMapper.getResultsByModel(modelId);
    return results == null ? Lists.newArrayList() : results;
  }

  public PredictionResult getResult(String modelId, String companyId) {
    return predictionResultMapper.getResult(modelId, companyId);
  }

}
