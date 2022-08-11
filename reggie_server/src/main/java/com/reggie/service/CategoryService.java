package com.reggie.service;

import com.reggie.dto.CategoryDTO;
import com.reggie.dto.CategoryPageQueryDTO;
import com.reggie.result.PageResult;

public interface CategoryService {
    /**
     * 新增分类
     * @param categoryDTO
     */
    void save(CategoryDTO categoryDTO);

    /**
     * 分页查询
     * @param pageQueryDTO
     * @return
     */
    PageResult pageQuery(CategoryPageQueryDTO pageQueryDTO);

    /**
     * 删除
     * @param id
     */
    void deleteById(Long id);

    /**
     * 分类状态变更
     * @param status
     * @param id
     */
    void allowOrBan(Integer status, long id);

    /**
     * 编辑分类
     * @param categoryDTO
     */
    void update(CategoryDTO categoryDTO);
}
