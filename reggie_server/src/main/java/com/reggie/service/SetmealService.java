package com.reggie.service;

import com.reggie.dto.SetmealDTO;
import com.reggie.dto.SetmealPageQueryDTO;
import com.reggie.result.PageResult;
import com.reggie.vo.SetmealVO;

import java.util.List;

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

    /**
     * 查询套餐
     * @param id
     * @return
     */
    SetmealVO getByIdWithDish(Long id);

    /**
     * 套餐状态变更
     * @param status
     * @param id
     */
    void allowOrBan(Integer status, Long id);

    /**
     * 批量删除套餐
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     * 新增套餐
     * @param setmealDTO
     */
    void saveWithDishs(SetmealDTO setmealDTO);
}
