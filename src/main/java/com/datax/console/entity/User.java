package com.datax.console.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * User
 * Created by wangzk on 17/3/3.
 */
@Data
@NoArgsConstructor
public class User {

  private String id;
  private String name;
  private String company;
  private String website;
  private String mobile;
  private String email;
  private String password;

}
