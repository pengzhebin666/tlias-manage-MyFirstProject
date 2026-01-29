package com.itheima.config;

import com.itheima.interceptors.DemoInterceptor;
import com.itheima.interceptors.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {
   /* @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")          // 允许所有接口路径
                .allowedOriginPatterns("*") // 允许所有域名访问（生产环境要写具体域名）
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS") // 允许所有请求方法
                .allowedHeaders("*")        // 允许所有请求头
                .allowCredentials(true);    // 允许携带 Cookie（如果需要登录态的话）
    }

    // 你的拦截器注册代码（保留）
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/login");
    }*/

    /* @Autowired
        private DemoInterceptor demoInterceptor;*/
    @Autowired
    private TokenInterceptor tokenInterceptor;
   /* @Override
    public void addInterceptors(InterceptorRegistry  registry) {
       // registry.addInterceptor(tokenInterceptor).addPathPatterns("/**");
    }*/
}
