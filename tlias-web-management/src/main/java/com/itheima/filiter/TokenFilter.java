package com.itheima.filiter;

/*

     token令牌检验的过滤器
 */

import cn.hutool.core.text.AntPathMatcher;
import cn.hutool.core.util.StrUtil;
import com.itheima.utils.CurrentHolder;
import com.itheima.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;

import java.io.IOException;


@Slf4j
//@WebFilter(urlPatterns = "/*")//拦截所有请求
public class TokenFilter implements Filter {
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        //1.先获取请求的uri
        String uri = request.getRequestURI();
        log.info("请求的uri:{}",uri);
        // 2.判断请求uri是否包含login,包含说明是登录操作，放行

        if (antPathMatcher.match("/login", uri)){
            log.info("登录操作，放行...");
            chain.doFilter(request, response);
            return;

        }
        //3.获取请求头的token
        String token = request.getHeader("token");
        log.info("获取请求头中的token:{}",token);
        //4.判断token是否存在，
         if(StrUtil.isBlank( token)){
             response.setStatus(HttpStatus.SC_UNAUTHORIZED);
             return;
         }
         //5.校验token，如果正确，放行，如果错误，返回401状态码
        try {
            Claims claims = JwtUtils.parseJWT(token);
            Integer empId = Integer.valueOf(claims.get("id").toString());
            CurrentHolder.setCurrentId(empId);
            log.info("当前用户id为:{}将其存入进去",empId);
        } catch (Exception e) {
           e.printStackTrace();
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return;
        }
        log.info("token有效，放行...");
        chain.doFilter(request, response);
        CurrentHolder.remove();
    }
}