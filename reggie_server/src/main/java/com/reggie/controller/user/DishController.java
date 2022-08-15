package com.reggie.controller.user;

import com.reggie.entity.Dish;
import com.reggie.result.R;
import com.reggie.service.DishService;
import com.reggie.vo.DishVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController("userDishController")
@RequestMapping("/user/dish")
@Slf4j
@Api(tags = "C端-菜品浏览接口")
public class DishController {
    @Autowired
    private DishService dishService;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 根据分类id查询菜品
     *
     * @param categoryId
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("根据分类id查询菜品")
    public R<List<DishVO>> list(Long categoryId) {
        String key = "dish_" + categoryId;

        List<DishVO> list = (List<DishVO>) redisTemplate.opsForValue().get(key);

        if (list != null){
            return R.success(list);
        }

        Dish dish = new Dish();
        dish.setCategoryId(categoryId);
        dish.setStatus(Dish.ON);

        list = dishService.listWithFlavor(dish);

        redisTemplate.opsForValue().set(key,list);

        return R.success(list);
    }

}
