package com.reggie.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SetmealMapper {
    /**
     *
     * @param id
     * @return
     */
    Long countByCategoryId(Long id);
}