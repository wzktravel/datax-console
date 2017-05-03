package com.datax.console.service;

import com.datax.console.entity.ModelCompanyInfo;
import com.datax.console.entity.PredictionModel;
import com.datax.console.mapper.ModelCompanyInfoMapper;
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
  private final ModelCompanyInfoMapper companyMapper;

  @Autowired
  public PredictionModelService(PredictionModelMapper predictionModelMapper,
                                ModelCompanyInfoMapper companyMapper) {
    this.predictionModelMapper = predictionModelMapper;
    this.companyMapper = companyMapper;
  }

  public void insert(PredictionModel m) {
    predictionModelMapper.insert(m);
  }

  public void update(PredictionModel m) {
    predictionModelMapper.update(m);
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

  public ModelCompanyInfo getCompanyInfoByModel(String modelId) {
    return companyMapper.getByModel(modelId);
  }

  public void insertOrUpdateCompanyInfo(ModelCompanyInfo c) {
    String modelId = c.getModelId();

    ModelCompanyInfo info = companyMapper.getByModel(modelId);
    if (info == null) {
      companyMapper.insert(c);
    } else {
      companyMapper.update(c);
    }
  }

}
