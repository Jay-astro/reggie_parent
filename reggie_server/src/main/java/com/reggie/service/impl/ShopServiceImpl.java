package com.reggie.service.impl;

import com.reggie.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class ShopServiceImpl implements ShopService {

    public static final String KEY="SHOP:STATUS";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 设置营业状态
     * @param status
     */
    @Override
    public void setShopStatus(Integer status) {
        redisTemplate.opsForValue().set(KEY,status);
        log.info("设置营业状态:{}",status == 1 ? "营业":"打烊");
    }

    @Override
    public Integer getShopStatus() {
        log.info("设置营业状态");
        return (Integer) redisTemplate.opsForValue().get(KEY);
    }
}
