package com.reggie.service;

import com.reggie.dto.SetmealDTO;
import com.reggie.dto.SetmealPageQueryDTO;
import com.reggie.entity.Setmeal;
import com.reggie.result.PageResult;
import com.reggie.vo.DishItemVO;
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

    /**
     * 根据分类id查询套餐
     * @param setmeal
     * @return
     */
    List<Setmeal> list(Setmeal setmeal);

    /**
     * 根据套餐id查询包含的菜品列表
     * @param id
     * @return
     */
    List<DishItemVO> getDishItemById(Long id);
}
