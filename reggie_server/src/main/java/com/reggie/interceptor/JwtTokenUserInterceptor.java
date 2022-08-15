package com.reggie.interceptor;

import com.reggie.annotation.IgnoreToken;
import com.reggie.constant.JwtClaimsConstant;
import com.reggie.context.BaseContext;
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
public class JwtTokenUserInterceptor implements HandlerInterceptor {

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

        //对加入了Ignore注解的直接放行
        HandlerMethod handlerMethod = (HandlerMethod) handler;
        boolean hasMethodAnnotation = handlerMethod.hasMethodAnnotation(IgnoreToken.class);
        if (hasMethodAnnotation){
            return true;
        }

        String token = request.getHeader(jwtProperties.getUserTokenName());

        try {
            Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(),token);
            Long userId = Long.valueOf(claims.get(JwtClaimsConstant.USER_ID).toString());
            BaseContext.setCurrentId(userId);
            return true;
        } catch (Exception e) {
            log.error("jwt令牌解析失败！");
            //没有访问权限
            response.setStatus(401);
            return false;
        }
    }
}
