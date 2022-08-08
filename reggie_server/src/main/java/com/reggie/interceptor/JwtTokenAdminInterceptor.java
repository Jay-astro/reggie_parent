package com.reggie.interceptor;

import com.reggie.properties.JwtProperties;
import com.reggie.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class JwtTokenAdminInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtProperties jwtProperties;


    /**
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("拦截到请求:{}",request.getRequestURI());

        if (!(handler instanceof HandlerMethod)){
            //不是映射到controller某个方法的请求直接放行
            return true;
        }

        String token = request.getHeader(jwtProperties.getAdminTokenName());

        try {
            Claims claims = JwtUtil.parseJWT(jwtProperties.getAdminSecretKey(),token);
            return true;
        } catch (Exception e) {
            log.error("jwt令牌解析失败！");
            //没有访问权限
            response.setStatus(401);
            return false;
        }
    }
}
