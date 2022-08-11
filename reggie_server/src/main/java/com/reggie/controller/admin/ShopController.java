package com.reggie.controller.admin;

import com.reggie.result.R;
import com.reggie.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController("adminShopController")
@RequestMapping("/admin/shop")
@Api(tags = "店铺接口")
public class ShopController {

    @Autowired
    private ShopService shopService;

    /**
     * 设置营业状态
     * @param status
     * @return
     */
    @PutMapping("/{status}")
    @ApiOperation("设置营业状态")
    public R<String> setShopStatus(@PathVariable Integer status){
        shopService.setShopStatus(status);
        return R.success();
    }

    /**
     * 获取营业状态
     * @return
     */
    @GetMapping("/status")
    @ApiOperation("获取营业状态")
    public R<Integer> getShopStatus(){
        Integer shopStatus = null;
        try {
            shopStatus = shopService.getShopStatus();
        } catch (Exception e) {
            e.printStackTrace();
            shopStatus = 1;
        }
        return R.success(shopStatus);
    }
}
