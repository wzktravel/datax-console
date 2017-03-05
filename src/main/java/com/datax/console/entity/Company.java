package com.datax.console.entity;

import lombok.Data;

/**
 * Company
 * Created by wangzk on 17/3/3.
 */
@Data
public class Company {

  private String id;
  private String name;
  private String registeredDate;
  private String registeredCapital;
  private String address;
  private String city;
  private String mainCategory;
  private String bigCategory;
  private String website = "";
  //  private String province;
  //  private String creditCode;
  //  private String registeredNumber;

}