package com.datax.console.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import lombok.extern.slf4j.Slf4j;
import lombok.val;

/**
 * UserServiceTest
 * Created by wangzk on 17/4/6.
 */
@Slf4j
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext.xml")
public class UserServiceTest {

  @Autowired
  private UserService userService;

  @Test
  public void getByEmail() throws Exception {
    val user = userService.getByEmail("xxx");
    log.info("user: {}", user);
    log.info("user: {}", user == null);
  }

}