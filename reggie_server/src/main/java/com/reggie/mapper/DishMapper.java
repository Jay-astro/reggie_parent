package com.reggie.mapper;

import com.github.pagehelper.Page;
import com.reggie.annotation.AutoFill;
import com.reggie.constant.AutoFillConstant;
import com.reggie.dto.DishPageQueryDTO;
import com.reggie.entity.Dish;
import com.reggie.vo.DishVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DishMapper {


    /**
     *
     * @param id
     * @return
     */
    Long countByCategoryId(Long id);

    /**
     *
     * @param dish
     */
    @AutoFill(type = AutoFillConstant.INSERT)
    void insert(Dish dish);

    /**
     *
     * @param pageQueryDTO
     * @return
     */
    Page<DishVO> pageQuery(DishPageQueryDTO pageQueryDTO);

    /**
     *
     * @param id
     * @return
     */
    Dish getById(Long id);

    /**
     *
     * @param dishId
     */
    void deleteById(Long dishId);

    /**
     *
     * @param id
     * @return
     */
    DishVO getByIdWithFlavor(Long id);

    /**
     *
     * @param dish
     */
    void update(Dish dish);

    /**
     *
     * @param status
     * @param id
     */
    void updateStatusById(Integer status, long id);

    /**
     * 根据分类Id查询菜品
     * @param dish
     * @return
     */
    List<Dish> list(Dish dish);
}
