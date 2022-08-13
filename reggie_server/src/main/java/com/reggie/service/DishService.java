package com.reggie.service;

import com.reggie.dto.DishDTO;
import com.reggie.dto.DishPageQueryDTO;
import com.reggie.entity.Dish;
import com.reggie.result.PageResult;
import com.reggie.vo.DishVO;

import java.util.List;

public interface DishService {


    /**
     * 新增菜品
     * @param dishDTO
     */
    void saveWithFlavor(DishDTO dishDTO);

    /**
     * 菜品分页查询
     * @param pageQueryDTO
     * @return
     */
    PageResult pageQuery(DishPageQueryDTO pageQueryDTO);

    /**
     * 批量删除菜品
     * @param ids
     */
    void deleteBatch(List<Long> ids);

    /**
     *
     * @param id
     * @return
     */
    DishVO getByIdWithFlavor(Long id);

    /**
     *
     * @param dishDTO
     */
    void updateWithFlavor(DishDTO dishDTO);

    /**
     *
     * @param status
     * @param id
     */
    void allowOrBan(Integer status, long id);

    /**
     * 根据分类Id查询菜品
     * @param categoryId
     * @return
     */
    List<Dish> list(Long categoryId);
}
