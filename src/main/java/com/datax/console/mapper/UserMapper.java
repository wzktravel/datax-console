package com.datax.console.mapper;

import com.datax.console.entity.User;
import com.github.mybatis.mapper.ICrudMapper;

import org.apache.ibatis.annotations.Select;

/**
 * UserMapper
 * Created by wangzk on 17/3/3.
 */
public interface UserMapper extends ICrudMapper<User> {

  @Select("select * from user where username=#{email}")
  User getByEmail(String email);

  @Select("select * from user where mobile=#{mobile}")
  User getByMobile(String mobile);

}
