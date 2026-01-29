package com.itheima.interceptors;

import cn.hutool.core.text.AntPathMatcher;
import cn.hutool.core.util.StrUtil;
import com.itheima.utils.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
@Slf4j
@Component
public class TokenInterceptor implements HandlerInterceptor {
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        log.info("请求的uri:{}",uri);
        // 2.判断请求uri是否包含login,包含说明是登录操作，放行

        if (antPathMatcher.match("/login", uri)){
            log.info("登录操作，放行...");

            return  true;

        }
        //3.获取请求头的token
        String token = request.getHeader("token");
        log.info("获取请求头中的token:{}",token);
        //4.判断token是否存在，
        if(StrUtil.isBlank( token)){
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return  false;
        }
        //5.校验token，如果正确，放行，如果错误，返回401状态码
        try {
            JwtUtils.parseJWT(token);
        } catch (Exception e) {
            e.printStackTrace();
            response.setStatus(HttpStatus.SC_UNAUTHORIZED);
            return  false;
        }
        log.info("token有效，放行...");
        return true;
    }
}
