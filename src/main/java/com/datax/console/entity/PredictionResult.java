package com.datax.console.entity;

import lombok.Data;

/**
 * 预测结果
 * Created by wangzk on 17/3/4.
 */
@Data
public class PredictionResult {

  private String id;
  private String modelId;
  private Company company;
  private double score;
  private double prediction;

}
