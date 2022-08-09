package com.reggie.mapper;

import com.github.pagehelper.Page;
import com.reggie.annotation.AutoFill;
import com.reggie.constant.AutoFillConstant;
import com.reggie.dto.CategoryPageQueryDTO;
import com.reggie.entity.Category;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CategoryMapper {
    /**
     * 新增分类
     * @param category
     */
    @AutoFill(type = AutoFillConstant.INSERT)
    void insert(Category category);

    /**
     * 分页查询
     * @param pageQueryDTO
     * @return
     */
    Page<Category> pageQuery(CategoryPageQueryDTO pageQueryDTO);


    /**
     * 删除
     * @param id
     */
    void deleteBuId(Long id);
}
