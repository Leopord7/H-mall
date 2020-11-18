package com.leopord.hmall.config.assist;

import com.alibaba.fastjson.JSON;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @description: 自定义shiro拦截器: 302不进行页面跳转;
 * @author: Leopord
 * @time: 2020/11/18 12:08
 */
public class MyAuthenticationFilter extends FormAuthenticationFilter {

    private static final Logger logger = LoggerFactory.getLogger(FormAuthenticationFilter.class);

    @Override
    protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
        if (isLoginRequest(request, response)) {
            if (isLoginSubmission(request, response)) {
                if (logger.isTraceEnabled()) {
                    logger.trace("Login submission detected.  Attempting to execute login.");
                }
                return executeLogin(request, response);
            } else {
                if (logger.isTraceEnabled()) {
                    logger.trace("Login page view.");
                }
                //allow them to see the login page ;)
                return true;
            }
        } else {
            HttpServletRequest req = (HttpServletRequest)request;
            HttpServletResponse resp = (HttpServletResponse) response;
            if(req.getMethod().equals(RequestMethod.OPTIONS.name())) {
                resp.setStatus(HttpStatus.OK.value());
                return true;
            }
            if (logger.isTraceEnabled()) {
                logger.trace("Attempting to access a path which requires authentication.  Forwarding to the " +
                        "Authentication url [" + getLoginUrl() + "]");
            }
            resp.setHeader("Access-Control-Allow-Origin",  req.getHeader("origin"));
            resp.setHeader("Access-Control-Allow-Credentials", "true");
            resp.setContentType("application/json; charset=utf-8");
            resp.setCharacterEncoding("UTF-8");
            resp.setStatus(HttpServletResponse.SC_OK);
            PrintWriter writer = resp.getWriter();
            Map<String, Object> map= new HashMap<>();
            map.put("code", 402);
            map.put("message", "登录状态无效！");
            writer.write(JSON.toJSONString(map));
            writer.close();
            return false;
        }
    }
}
