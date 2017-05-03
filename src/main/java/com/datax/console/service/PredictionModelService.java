package com.datax.console.service;

import com.datax.console.entity.PredictionModel;
import com.datax.console.mapper.PredictionModelMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * PredictionModelService
 * Created by wangzk on 17/3/4.
 */
@Slf4j
@Service
public class PredictionModelService {

  private final PredictionModelMapper predictionModelMapper;

  @Autowired
  public PredictionModelService(PredictionModelMapper predictionModelMapper) {
    this.predictionModelMapper = predictionModelMapper;
  }

  public void insert(PredictionModel m) {
    predictionModelMapper.insert(m);
  }

  public List<PredictionModel> getByUser(String userId) {
    return predictionModelMapper.getByUser(userId);
  }

  public PredictionModel getById(String modelId) {
    return predictionModelMapper.findById(modelId);
  }

  public PredictionModel getByUserAndName(String userId, String name) {
    return predictionModelMapper.getByUserAndName(userId, name);
  }

}
