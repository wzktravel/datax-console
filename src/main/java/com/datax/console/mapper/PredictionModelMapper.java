package com.datax.console.mapper;

import com.datax.console.entity.PredictionModel;
import com.github.mybatis.mapper.ICrudMapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * PredictionModelMapper
 * Created by wangzk on 17/3/4.
 */
public interface PredictionModelMapper extends ICrudMapper<PredictionModel> {

  @Select("select * from prediction_model where user_id = #{userId}")
  List<PredictionModel> getByUser(@Param("userId") String userId);

  @Select("select * from prediction_model where user_id = #{userId} and name = #{name}")
  PredictionModel getByUserAndName(@Param("userId") String userId, @Param("name") String name);

}
