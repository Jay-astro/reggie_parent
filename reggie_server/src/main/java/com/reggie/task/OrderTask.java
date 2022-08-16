package com.reggie.task;

import com.reggie.entity.Orders;
import com.reggie.mapper.OrderMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Slf4j
public class OrderTask {
    @Autowired
    private OrderMapper orderMapper;

    @Scheduled(cron = "0 0/1 * * * ?")
    public void processTimeoutOrder() {
        log.info("支付超时:{}",LocalDateTime.now());
        LocalDateTime orderTime = LocalDateTime.now().plusMinutes(-15);

        List<Orders> ordersList = orderMapper.getByStatusAndOrderTimeLT(Orders.PENDING_PAYMENT,orderTime);

        if (ordersList != null && ordersList.size() > 0){
            ordersList.forEach((item)->{
                Orders order = Orders.builder()
                        .id(item.getId())
                        .status(Orders.CANCELLED)
                        .cancelReason("支付超时，自动取消")
                        .cancelTime(LocalDateTime.now())
                        .build();
                orderMapper.updateOrdersById(order);
            });
        }
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void processDeliveryOrder() {
        log.info("未完成订单处理:{}",LocalDateTime.now());

        LocalDateTime orderTime = LocalDateTime.now().plusMinutes(-60);

        List<Orders> ordersList = orderMapper.getByStatusAndOrderTimeLT(Orders.DELIVERY_IN_PROGRESS, orderTime);

        if (ordersList != null && ordersList.size() > 0){
            ordersList.forEach((item)->{
                Orders order = Orders.builder()
                        .id(item.getId())
                        .status(Orders.COMPLETED)
                        .build();
                orderMapper.updateOrdersById(order);
            });
        }
    }
}
