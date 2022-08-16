package com.reggie.service;

import com.reggie.dto.OrdersPaymentDTO;
import com.reggie.dto.OrdersSubmitDTO;
import com.reggie.result.PageResult;
import com.reggie.vo.OrderPaymentVO;
import com.reggie.vo.OrderSubmitVO;
import com.reggie.vo.OrderVO;

public interface OrderService {

    /**
     * 用户下单
     * @param ordersSubmitDTO
     * @return
     */
    OrderSubmitVO submit(OrdersSubmitDTO ordersSubmitDTO);

    /**
     * 订单支付
     * @param ordersPaymentDTO
     * @return
     */
    OrderPaymentVO payment(OrdersPaymentDTO ordersPaymentDTO);

    /**
     * 历史订单查询
     * @param page
     * @param pageSize
     * @param status
     * @return
     */
    PageResult pageQuery4User(int page, int pageSize, Integer status);

    /**
     * 查询订单详情
     * @param id
     * @return
     */
    OrderVO details(Long id);

    /**
     * 催单
     * @param id
     */
    void reminder(Long id);

    /**
     * 取消订单
     * @param id
     */
    void userCancelById(Long id);

    /**
     * 再来一单
     * @param id
     */
    void repetition(Long id);
}
