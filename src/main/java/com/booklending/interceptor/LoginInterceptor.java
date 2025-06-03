package com.booklending.interceptor;

import com.booklending.model.User;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor implements HandlerInterceptor {
    
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        
        // 如果用户未登录，重定向到登录页面
        if (user == null) {
            response.sendRedirect(request.getContextPath() + "/user/login");
            return false;
        }
        
        // 检查管理员权限
        String requestURI = request.getRequestURI();
        if ((requestURI.contains("/user/list") || 
             requestURI.contains("/user/add") || 
             requestURI.contains("/book/add") || 
             requestURI.contains("/book/edit") || 
             requestURI.contains("/book/delete")) && 
            user.getRole() != 1) {
            response.sendRedirect(request.getContextPath() + "/");
            return false;
        }
        
        return true;
    }
}