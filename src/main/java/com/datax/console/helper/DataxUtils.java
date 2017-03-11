package com.datax.console.helper;

import java.util.List;
import java.util.Map;

/**
 * DataxUtils
 * Created by wangzk on 17/3/8.
 */
public class DataxUtils {

  public static boolean isNullOrEmpty(List list) {
    return list == null || list.isEmpty();
  }

  public static boolean isNullOrEmpty(Map map) {
    return map == null || map.isEmpty();
  }

}
