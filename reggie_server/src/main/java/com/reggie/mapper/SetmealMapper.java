package com.reggie.mapper;

import com.github.pagehelper.Page;
import com.reggie.annotation.AutoFill;
import com.reggie.constant.AutoFillConstant;
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
}
