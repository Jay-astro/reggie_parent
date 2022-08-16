package com.reggie.mapper;

import com.github.pagehelper.Page;
import com.reggie.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    /**
     * 插入订单数据
     * @param order
     */
    void insert(Orders order);

    /**
     * 根据订单号查询当前用户的订单
     * @param orderNumber
     * @param userId
     * @return
     */
    Orders getByNumber(String orderNumber, Long userId);

    /**
     *
     * @param orders
     */
    void updateOrdersById(Orders orders);

    /**
     *
     * @param map
     * @return
     */
    Page<Orders> pageQuerySortByOrderTime(Map map);

    /**
     *
     * @param id
     * @return
     */
    Orders getById(Long id);

    /**
     *
     * @param status
     * @param orderTime
     * @return
     */
    List<Orders> getByStatusAndOrderTimeLT(Integer status, LocalDateTime orderTime);
}
