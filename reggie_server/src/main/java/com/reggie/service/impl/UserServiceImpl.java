package com.reggie.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.reggie.constant.MessageConstant;
import com.reggie.dto.UserLoginDTO;
import com.reggie.entity.User;
import com.reggie.exception.LoginFailedException;
import com.reggie.mapper.UserMapper;
import com.reggie.service.UserService;
import com.reggie.utils.HttpClientUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    public static final String LOGIN_URL = "https://api.weixin.qq.com/sns/jscode2session";

    @Autowired
    private UserMapper userMapper;

    @Value("${reggie.wechat.appid}")
    private String appid;

    @Value("${reggie.wechat.secret}")
    private String secret;

    /**
     * @param userLoginDTO
     * @return
     */
    @Override
    public User wxLogin(UserLoginDTO userLoginDTO) {
        log.info("微信用户登录，授权码:{}", userLoginDTO.getCode());
        String code = userLoginDTO.getCode();
        String openid = getOpenid(code);
        if (openid == null) {
            throw new LoginFailedException(MessageConstant.LOGIN_FAILED);
        }

        User user = userMapper.getByOpenid(openid);
        if (user == null) {
            user = new User();
            user.setOpenid(openid);
            user.setCreateTime(LocalDate.now());
            userMapper.insert(user);
        }
        return user;
    }

    /**
     * 查询到用户openid
     *
     * @param code
     * @return
     */
    private String getOpenid(String code) {
        Map<String, String> param = new HashMap<>();
        param.put("appid", appid);
        param.put("secret", secret);
        param.put("js_code", code);
        param.put("grant_type", "authorization_code");

        String res = HttpClientUtil.doGet(LOGIN_URL, param);
        JSONObject jsonObject = JSON.parseObject(res);
        String openid = jsonObject.getString("openid");

        log.info("查询到用户openid:{}", openid);
        return openid;
    }
}
