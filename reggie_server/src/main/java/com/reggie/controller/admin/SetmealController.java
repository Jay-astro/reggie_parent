package com.reggie.controller.admin;

import com.reggie.dto.SetmealDTO;
import com.reggie.dto.SetmealPageQueryDTO;
import com.reggie.result.PageResult;
import com.reggie.result.R;
import com.reggie.service.SetmealService;
import com.reggie.vo.DishVO;
import com.reggie.vo.SetmealVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/setmeal")
@Slf4j
@Api(tags = "套餐相关接口")
public class SetmealController {


    @Autowired
    private SetmealService setmealService;

    /**
     * 套餐分页查询
     * @param setmealPageQueryDTO
     * @return
     */
    @GetMapping("/page")
    @ApiOperation("套餐分页查询")
    public R<PageResult> page(SetmealPageQueryDTO setmealPageQueryDTO){
        log.info("套餐分页查询");
        PageResult pageResult =  setmealService.pageQuery(setmealPageQueryDTO);
        return R.success(pageResult);
    }

    /**
     * 修改套餐
     * @param setmealDTO
     * @return
     */
    @PutMapping
    @ApiOperation("修改套餐")
    public R<String> updateById(@RequestBody SetmealDTO setmealDTO){
        log.info("修改套餐:{}",setmealDTO);
        setmealService.updateById(setmealDTO);
        return R.success();
    }

    /**
     * 查询套餐
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ApiOperation("查询套餐")
    public R<SetmealVO> getById(@PathVariable Long id){
        log.info("查询套餐");
        SetmealVO setmealVO = setmealService.getByIdWithDish(id);
        return R.success(setmealVO);
    }

    /**
     * 套餐状态变更
     * @param status
     * @return
     */
    @PostMapping("/status/{status}")
    @ApiOperation("套餐状态变更")
    public R<String> allowOrBan(@PathVariable("status") Integer status,Long id){
        log.info("套餐状态变更");
        setmealService.allowOrBan(status,id);
        return R.success();
    }

    /**
     * 批量删除套餐
     * @param ids
     * @return
     */
    @DeleteMapping
    @ApiOperation("批量删除套餐")
    public R<String> delete(@RequestParam List<Long> ids){
        log.info("批量删除套餐");
        setmealService.deleteBatch(ids);
        return R.success();
    }

    /**
     * 新增套餐
     * @param setmealDTO
     * @return
     */
    @PostMapping
    @ApiOperation("新增套餐")
    public R save(@RequestBody SetmealDTO setmealDTO){
        log.info("新增套餐");
        setmealService.saveWithDishs(setmealDTO);
        return R.success();
    }
}
