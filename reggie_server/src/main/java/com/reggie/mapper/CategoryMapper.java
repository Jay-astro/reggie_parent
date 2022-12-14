package com.reggie.mapper;

import com.github.pagehelper.Page;
import com.reggie.annotation.AutoFill;
import com.reggie.constant.AutoFillConstant;
import com.reggie.dto.CategoryPageQueryDTO;
import com.reggie.entity.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

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

    /**
     * 分类状态变更
     * @param status
     * @param id
     */
    void updateStatusById(Integer status, long id);

    /**
     * 编辑分类
     * @param category
     */
    @AutoFill(type = AutoFillConstant.UPDATE)
    void update(Category category);

    /**
     * 根据类型查询
     * @param type
     */
    List<Category> list(Integer type);
}
