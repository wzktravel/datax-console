package com.datax.console.service;

import com.datax.console.entity.User;
import com.datax.console.mapper.UserMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

/**
 * UserService
 * Created by wangzk on 17/3/3.
 */
@Slf4j
@Service
public class UserService {

  private final UserMapper userMapper;

  @Autowired
  public UserService(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  public User getById(String id) {
    return userMapper.findById(id);
  }

  public User getByEmail(String email) {
    return userMapper.getByEmail(email);
  }

  public int insert(User user) {
    return userMapper.insert(user);
  }

}
