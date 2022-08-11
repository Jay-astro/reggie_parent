package com.reggie.controller.user;

import com.reggie.result.R;
import com.reggie.service.ShopService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("userShopController")
@RequestMapping("/user/shop")
@Api(tags = "C端:店铺操作接口")
public class ShopController {
    @Autowired
    private ShopService shopService;

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
