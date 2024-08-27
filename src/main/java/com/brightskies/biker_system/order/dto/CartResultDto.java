package com.brightskies.biker_system.order.dto;

import java.util.List;

public record CartResultDto (List<CartItemDto> cartItemDtoList, double totalPrice){
}
