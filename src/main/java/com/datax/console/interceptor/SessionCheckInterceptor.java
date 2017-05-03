package com.datax.console.interceptor;

import com.google.common.base.Splitter;
import com.google.common.collect.Lists;

import com.datax.console.entity.User;
import com.github.autoconf.ConfigFactory;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import java.net.URLEncoder;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lombok.extern.slf4j.Slf4j;

/**
 * SessionCheckInterceptor
 */
@Slf4j
public class SessionCheckInterceptor extends HandlerInterceptorAdapter {

  private List<String> ignoredUrlList = Lists.newArrayList();

  @PostConstruct
  void init() {
    ConfigFactory.getConfig("datax-console-config", config -> {
      String ignoredUrls = config.get("ignoredUrls");
      ignoredUrlList = Splitter.on(',').omitEmptyStrings().trimResults().splitToList(ignoredUrls);
    });
  }

  @Override
  public boolean preHandle(HttpServletRequest request,
                           HttpServletResponse response, Object handler) throws Exception {
    request.setCharacterEncoding("UTF-8");
    String url = request.getServletPath();
    log.info("Current URL: {}", url);

    if (toValidate(url)) {
      //判断是否已登录
      User user = (User)request.getSession().getAttribute("user");
      if (user == null) {
        log.info(">>> No session, please login. <<<");
        response.sendRedirect(request.getContextPath() + "/login?url=" + URLEncoder.encode(url, "UTF-8"));
        return false;
      }
    }
    return true;
  }

  private boolean toValidate(String url) {
    for (String s : ignoredUrlList) {
      if (StringUtils.endsWith(url, s)) {
        return false;
      }
    }
    return true;
  }

}
