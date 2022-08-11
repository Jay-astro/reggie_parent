package com.reggie.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.reggie.dto.SetmealDTO;
import com.reggie.dto.SetmealPageQueryDTO;
import com.reggie.entity.Setmeal;
import com.reggie.entity.SetmealDish;
import com.reggie.mapper.SetmealDishMapper;
import com.reggie.mapper.SetmealMapper;
import com.reggie.result.PageResult;
import com.reggie.result.R;
import com.reggie.service.SetmealService;
import com.reggie.vo.DishVO;
import com.reggie.vo.SetmealVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Slf4j
public class SetmealServiceImpl implements SetmealService {

    @Autowired
    private SetmealMapper setmealMapper;

    @Autowired
    private SetmealDishMapper setmealDishMapper;

    @Override
    @Transactional
    public void updateById(SetmealDTO setmealDTO) {
        Setmeal setmeal = new Setmeal();
        BeanUtils.copyProperties(setmealDTO,setmeal);
        setmealMapper.update(setmeal);

        Long SetmealId = setmealDTO.getId();
        setmealDishMapper.deleteBySetmealId(SetmealId);
    }

    @Override
    public PageResult pageQuery(SetmealPageQueryDTO setmealPageQueryDTO) {
        PageHelper.startPage(setmealPageQueryDTO.getPage(),setmealPageQueryDTO.getPageSize());
        Page<SetmealVO> page = setmealMapper.pageQuery(setmealPageQueryDTO);
        return new PageResult(page.getTotal(),page.getResult());
    }


}
