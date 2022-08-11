package com.reggie.service;

import com.reggie.dto.SetmealDTO;
import com.reggie.dto.SetmealPageQueryDTO;
import com.reggie.result.PageResult;

public interface SetmealService {
    /**
     * 修改套餐
     * @param setmealDTO
     */
    void updateById(SetmealDTO setmealDTO);

    /**
     * 套餐分页
     * @param setmealPageQueryDTO
     * @return
     */
    PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);
}
