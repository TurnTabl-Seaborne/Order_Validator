package com.seaborne.order_validator.service;

import com.seaborne.consumervalidator.GetOrderRequest;

public interface OrderService{
    public Boolean isOrderValid(GetOrderRequest orderRequest);
}
