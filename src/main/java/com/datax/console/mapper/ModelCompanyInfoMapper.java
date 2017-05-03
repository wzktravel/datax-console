package com.datax.console.mapper;

import com.datax.console.entity.ModelCompanyInfo;
import com.github.mybatis.mapper.ICrudMapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

/**
 * ModelCompanyInfoMapper
 * Created by wangzk on 17/5/3.
 */
public interface ModelCompanyInfoMapper extends ICrudMapper<ModelCompanyInfo> {

  @Select("select * from model_company_info where model_id = #{modelId}")
  ModelCompanyInfo getByModel(@Param("modelId") String modelId);

  @Update("update model_company_info set company = #{company}, scale = #{scale}, industry = #{industry}, product = #{product}, comment = #{comment} where model_id = #{modelId}")
  @Override int update(ModelCompanyInfo info);

}
