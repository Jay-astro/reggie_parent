package com.reggie.controller.admin;

import com.reggie.dto.CategoryDTO;
import com.reggie.dto.CategoryPageQueryDTO;
import com.reggie.dto.EmployeeDTO;
import com.reggie.result.PageResult;
import com.reggie.result.R;
import com.reggie.service.CategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin/category")
@Api(tags = "分类接口")
@Slf4j
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    /**
     * 新增分类
     * @param categoryDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增分类")
    public R<String> save(@RequestBody CategoryDTO categoryDTO){
        log.info("新增分类:{}",categoryDTO);
        categoryService.save(categoryDTO);
        return R.success();
    }

    /**
     * 分页查询
     * @param pageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("分页查询")
    public R<PageResult> page(CategoryPageQueryDTO pageQueryDTO){
        PageResult pageResult = categoryService.pageQuery(pageQueryDTO);
        return R.success(pageResult);
    }

    /**
     * 删除
     * @param id
     * @return
     */
    @DeleteMapping
    @ApiOperation("删除")
    public R deleteById(Long id){
        categoryService.deleteById(id);
        return R.success();
    }

    /**
     * 分类状态变更
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("分类状态变更")
    public R<String> allowOrBan(@PathVariable("status") Integer status, long id) {
        log.info("分类状态变更");
        categoryService.allowOrBan(status, id);
        return R.success();
    }

    /**
     * 编辑分类
     * @param categoryDTO
     * @return
     */
    @PutMapping
    @ApiOperation("编辑分类")
    public R<String> update(@RequestBody CategoryDTO categoryDTO) {
        categoryService.update(categoryDTO);
        return R.success();
    }
}
