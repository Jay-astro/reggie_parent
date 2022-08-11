package com.reggie.service;


public interface ShopService {

    /**
     * 店铺营业状态
     * @param status
     */
    public void setShopStatus(Integer status);

    /**
     * 获取店铺营业状态
     * @return
     */
    public Integer getShopStatus();
}
