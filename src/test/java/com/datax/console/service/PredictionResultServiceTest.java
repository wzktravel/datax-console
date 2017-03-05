package com.datax.console.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;
import com.datax.console.entity.PredictionResult;
import com.datax.console.helper.DataxContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * PredictionResultServiceTest
 * Created by wangzk on 17/3/4.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class PredictionResultServiceTest {

  @Autowired
  private PredictionResultService predictionResultService;

  @Test
  public void getResultsByModel() throws Exception {
    List<PredictionResult> results = predictionResultService.getResultsByModel("1");
    SimplePropertyPreFilter filter = DataxContext.getPredictionResultCompanyFilter();
    String s = JSON.toJSONString(results, filter);
    log.info("{}", s);
  }

}