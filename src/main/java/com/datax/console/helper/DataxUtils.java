package com.datax.console.helper;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * DataxUtils
 * Created by wangzk on 17/3/8.
 */
public class DataxUtils {

  public static boolean isNullOrEmpty(Collection c) {
    return c == null || c.isEmpty();
  }

  public static boolean isNullOrEmpty(Map map) {
    return map == null || map.isEmpty();
  }

  public static String getUUID() {
    String uuid = UUID.randomUUID().toString();
    return uuid.substring(0, 8) + uuid.substring(9, 13) + uuid.substring(14, 18) + uuid.substring(19, 23) + uuid.substring(24);
  }
}
