package com.young.interceptor;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author young
 * @Description   拦截器
 * @date 2020-05-01 21:37
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {
    //目标方法执行之前进行权限检查
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) throws Exception {
        if(request.getSession().getAttribute("user")==null) {
            response.sendRedirect("/admin");
            return false;
        }
        return true;
    }
}
