package com.brightskies.biker_system.order.dto;

import com.brightskies.biker_system.order.model.OrderHistory;
import java.util.List;
import java.util.stream.Collectors;

public class OrderHistoryMapper {
    public static OrderHistoryDTO toDTO (OrderHistory orderHistory) {
        return new OrderHistoryDTO(
                orderHistory.getProduct(),
                orderHistory.getQuantity()
        );
    }

    public static List<OrderHistoryDTO> toDTOList(List<OrderHistory> orderHistoryList) {
        return orderHistoryList.stream()
                .map(orderHistory -> new OrderHistoryDTO(
                        orderHistory.getProduct(),
                        orderHistory.getQuantity()
                ))
                .collect(Collectors.toList());
    }
}

