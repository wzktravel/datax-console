package com.datax.console.helper;

import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.datax.console.entity.Company;
import com.github.autoconf.ConfigFactory;

import org.apache.commons.lang3.StringUtils;

import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

/**
 * Context
 * Created by wangzk on 17/3/4.
 */
@Slf4j
@UtilityClass
public class DataxContext {

  private SimplePropertyPreFilter predictionResultCompanyFilter;

  static {
    ConfigFactory.getConfig("datax-console-config", config -> {
      String companyFilterStr = config.get("result.company.filter", "id,name,website");
      String[] companyFilter = StringUtils.split(companyFilterStr, ',');
      predictionResultCompanyFilter = new SimplePropertyPreFilter(Company.class, companyFilter);
    });
  }

  public SimplePropertyPreFilter getPredictionResultCompanyFilter() {
    return predictionResultCompanyFilter;
  }

}
