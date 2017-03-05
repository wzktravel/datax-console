package com.datax.console.mapper;

import com.datax.console.entity.PredictionResult;
import com.github.mybatis.mapper.ICrudMapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * PredictionResultMapper
 * Created by wangzk on 17/3/4.
 */
public interface PredictionResultMapper extends ICrudMapper<PredictionResult> {

  @Select("select r.*, "
          + "c.id as \"company.id\", c.`name` as \"company.name\", c.registered_date as \"company.registered_date\", c.registered_capital as \"company.registered_capital\", c.address as \"company.address\", c.city as \"company.city\", c.main_category as \"company.main_category\", c.big_category as \"company.big_category\" "
          + "from prediction_result as r, company as c "
          + "where r.company_id = c.id "
          + "and r.model_id = #{modelId} ")
  List<PredictionResult> getResultsByModel(@Param("modelId") String modelId);

  @Select("select r.*, "
          + "c.id as \"company.id\", c.`name` as \"company.name\", c.registered_date as \"company.registered_date\", c.registered_capital as \"company.registered_capital\", c.address as \"company.address\", c.city as \"company.city\", c.main_category as \"company.main_category\", c.big_category as \"company.big_category\" "
          + "from prediction_result as r, company as c "
          + "where r.company_id = c.id "
          + "and r.model_id = #{modelId} and c.id = #{companyId}")
  PredictionResult getResult(@Param("modelId") String modelId, @Param("companyId") String companyId);

}
