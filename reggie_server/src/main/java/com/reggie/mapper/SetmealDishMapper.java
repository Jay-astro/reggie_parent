package com.reggie.mapper;

import com.reggie.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    /**
     *
     * @param dishIds
     * @return
     */
    List<Long> getSetmealIdsByDishIds(List<Long> dishIds);

    /**
     *
     * @param setmealId
     */
    void deleteBySetmealId(Long setmealId);

    /**
     * 通过套餐Id获取菜品Id集合
     * @param id
     * @return
     */
    List<Long> getDishIdsBySetmealId(Long id);

    /**
     * 新增套餐
     * @param setmealDishes
     */
    void insertBatch(List<SetmealDish> setmealDishes);
}
