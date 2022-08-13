package com.reggie.controller.admin;

import com.reggie.dto.DishDTO;
import com.reggie.dto.DishPageQueryDTO;
import com.reggie.entity.Dish;
import com.reggie.result.PageResult;
import com.reggie.result.R;
import com.reggie.service.DishService;
import com.reggie.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/dish")
@Api(tags = "菜品接口")
@Slf4j
public class DishController {

    @Autowired
    private DishService dishService;

    /**
     * 新增菜品
     *
     * @param dishDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增菜品")
    public R<String> save(@RequestBody DishDTO dishDTO) {
        log.info("新增菜品:{}", dishDTO);
        dishService.saveWithFlavor(dishDTO);
        return R.success();
    }

    /**
     * 菜品分页查询
     *
     * @param pageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("菜品分页查询")
    public R<PageResult> page(DishPageQueryDTO pageQueryDTO) {
        log.info("菜品分页查询");
        PageResult pageResult = dishService.pageQuery(pageQueryDTO);
        return R.success(pageResult);
    }

    /**
     * 批量删除菜品
     *
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("批量删除菜品")
    public R<String> delete(@RequestParam List<Long> ids) {
        log.info("批量删除菜品");
        dishService.deleteBatch(ids);
        return R.success();
    }

    /**
     * 查询菜品关联的口味数据
     *
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("查询菜品关联的口味数据")
    public R<DishVO> getById(@PathVariable Long id) {
        log.info("查询菜品关联的口味数据");
        DishVO dishVO = dishService.getByIdWithFlavor(id);
        return R.success(dishVO);
    }

    /**
     * 修改菜品
     *
     * @param dishDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改菜品")
    public R<String> update(@RequestBody DishDTO dishDTO) {
        log.info("修改菜品:{}", dishDTO);
        dishService.updateWithFlavor(dishDTO);
        return R.success();
    }

    /**
     * 菜品状态变更
     *
     * @param status
     * @param id
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("菜品状态变更")
    public R<String> allowOrBan(@PathVariable("status") Integer status, long id) {
        log.info("菜品状态变更");
        dishService.allowOrBan(status, id);
        return R.success();
    }

    /**
     * 根据分类Id查询菜品
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根据分类Id查询菜品")
    public R<List<Dish>> list(Long categoryId){
        log.info("根据分类Id查询菜品");
        List<Dish> list = dishService.list(categoryId);
        return R.success(list);

    }
}
