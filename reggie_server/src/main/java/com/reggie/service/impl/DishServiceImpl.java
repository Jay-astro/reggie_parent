package com.reggie.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.reggie.constant.MessageConstant;
import com.reggie.constant.StatusConstant;
import com.reggie.dto.DishDTO;
import com.reggie.dto.DishPageQueryDTO;
import com.reggie.entity.Dish;
import com.reggie.entity.DishFlavor;
import com.reggie.exception.DeletionNotAllowedException;
import com.reggie.mapper.DishFlavorMapper;
import com.reggie.mapper.DishMapper;
import com.reggie.mapper.SetmealDishMapper;
import com.reggie.result.PageResult;
import com.reggie.service.DishService;
import com.reggie.vo.DishVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Slf4j
public class DishServiceImpl implements DishService {

    @Autowired
    private DishMapper dishMapper;

    @Autowired
    private DishFlavorMapper dishFlavorMapper;

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    @Override
    @Transactional
    public void saveWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        dishMapper.insert(dish);

        Long dishId = dish.getId();
        List<DishFlavor> flavorList = dishDTO.getFlavors();
        if (flavorList == null || flavorList.size() == 0){
            return;
        }
        flavorList.forEach(item ->{
            item.setDishId(dishId);
        });
        dishFlavorMapper.insertBatch(flavorList);
    }

    @Override
    public PageResult pageQuery(DishPageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
        Page<DishVO> page = dishMapper.pageQuery(pageQueryDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    @Transactional
    public void deleteBatch(List<Long> ids) {
        ids.forEach(id-> {
            Dish dish = dishMapper.getById(id);
            if (StatusConstant.ENABLE == dish.getStatus()){
                //正在售卖的商品不能删除
                throw new DeletionNotAllowedException(MessageConstant.DISH_ON_SALE);
            }
        });

        List<Long> setmealIds = setmealDishMapper.getSetmealIdsByDishIds(ids);
        if (setmealIds != null && setmealIds.size() > 0){
            throw  new DeletionNotAllowedException(MessageConstant.DISH_BE_RELATED_BY_SETMEAL);
        }
        ids.forEach(dishId-> {
            dishMapper.deleteById(dishId);
            dishFlavorMapper.deleteByDishId(dishId);
        });
    }

    @Override
    public DishVO getByIdWithFlavor(Long id) {
        DishVO dishVO = dishMapper.getByIdWithFlavor(id);
        return dishVO;
    }

    @Override
    @Transactional
    public void updateWithFlavor(DishDTO dishDTO) {
        Dish dish = new Dish();
        BeanUtils.copyProperties(dishDTO,dish);
        //修改菜品
        dishMapper.update(dish);
        //删除口味数据
        Long dishId = dishDTO.getId();
        dishFlavorMapper.deleteByDishId(dishId);

        List<DishFlavor> flavorList = dishDTO.getFlavors();
        if (flavorList != null && flavorList.size() > 0 ){
            flavorList.forEach(dishFlavor -> {
                dishFlavor.setDishId(dishId);
            });
            //重写口味数据
            dishFlavorMapper.insertBatch(flavorList);
        }
    }
}
