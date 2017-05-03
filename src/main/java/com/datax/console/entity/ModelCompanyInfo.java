package com.datax.console.entity;

import lombok.Data;

/**
 * 企业信息
 * Created by wangzk on 17/5/3.
 */
@Data
public class ModelCompanyInfo {

  private String modelId;
  private String company;
  private String scale;
  private String industry;
  private String product;
  private String comment;

}
