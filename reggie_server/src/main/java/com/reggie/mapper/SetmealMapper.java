package com.reggie.mapper;

import com.github.pagehelper.Page;
import com.reggie.annotation.AutoFill;
import com.reggie.constant.AutoFillConstant;
import com.reggie.dto.SetmealDTO;
import com.reggie.dto.SetmealPageQueryDTO;
import com.reggie.entity.Setmeal;
import com.reggie.vo.SetmealVO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SetmealMapper {
    /**
     *
     * @param id
     * @return
     */
    Long countByCategoryId(Long id);

    /**
     *
     * @param setmeal
     */
    @AutoFill(type = AutoFillConstant.UPDATE)
    void update(Setmeal setmeal);

    /**
     *
     * @param setmealPageQueryDTO
     * @return
     */
    Page<SetmealVO> pageQuery(SetmealPageQueryDTO setmealPageQueryDTO);

    /**
     *
     * @param id
     * @return
     */
    SetmealVO getByIdWithDish(Long id);

    /**
     * 套餐状态变更
     * @param status
     * @param id
     */
    void updateStatusById(Integer status, Long id);

    /**
     * 通过Id查询套餐
     * @param id
     * @return
     */
    Setmeal getById(Long id);

    /**
     * 删除套餐
     * @param id
     */
    void deleteById(Long id);



    /**
     * 新增套餐
     * @param setmeal
     */
    @AutoFill(type = AutoFillConstant.INSERT)
    void insert(Setmeal setmeal);
}
