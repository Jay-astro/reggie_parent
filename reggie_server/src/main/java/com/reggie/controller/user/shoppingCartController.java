package com.reggie.controller.user;

import com.reggie.context.BaseContext;
import com.reggie.dto.ShoppingCartDTO;
import com.reggie.entity.ShoppingCart;
import com.reggie.result.R;
import com.reggie.service.ShoppingCartService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@RequestMapping("/user/shoppingCart")
@Api(tags = "C端-购物车接口")
public class shoppingCartController {
    @Autowired
    private ShoppingCartService shoppingCartService;

    /**
     * 添加购物车
     *
     * @param shoppingCartDTO
     * @return
     */
    @PostMapping("/add")
    @ApiOperation("添加购物车")
    public R<String> add(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        log.info("添加购物车，商品:{}", shoppingCartDTO);
        shoppingCartService.add(shoppingCartDTO);
        return R.success();
    }

    /**
     * 查看购物车
     *
     * @return
     */
    @GetMapping("/list")
    @ApiOperation("查看购物车")
    public R<List<ShoppingCart>> list() {
        List<ShoppingCart> list = shoppingCartService.list();
        return R.success(list);
    }

    /**
     * 清空购物车
     *
     * @return
     */
    @DeleteMapping("/clean")
    @ApiOperation("清空购物车")
    public R<String> clean() {
        log.info("清空购物车:{}", BaseContext.getCurrentId());
        shoppingCartService.clean();
        return R.success();
    }

    /**
     * 删除一个商品
     *
     * @param shoppingCartDTO
     * @return
     */
    @PostMapping("/sub")
    @ApiOperation("删除一个商品")
    public R<String> sub(@RequestBody ShoppingCartDTO shoppingCartDTO) {
        log.info("删除一个商品:{}",shoppingCartDTO);
        shoppingCartService.sub(shoppingCartDTO);
        return R.success();
    }
}
