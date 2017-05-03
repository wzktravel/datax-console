package com.datax.console.entity;

import lombok.Data;

/**
 * 预测模型
 * Created by wangzk on 17/3/4.
 */
@Data
public class PredictionModel {

  private String id;
  private String name;
  private String userId;
  private String comment;
  private long date;

}
