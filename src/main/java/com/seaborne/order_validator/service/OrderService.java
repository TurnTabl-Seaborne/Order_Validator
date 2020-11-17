package com.seaborne.order_validator.service;

import com.seaborne.consumervalidator.Order;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

@Service
public class OrderService{
    private static  final Map<String, Order> order = new HashMap();
    @PostConstruct
    public void initialize(){
    Order od1 = new Order();
    od1.setUserId("101");
    od1.setProduct("GOOGLE");
    od1.setQuantity(2);
    od1.setPrice(1.5);
    od1.setSide("Sell");


        Order od2 = new Order();
        od2.setUserId("102");
        od2.setProduct("GOOGLE");
        od2.setQuantity(3);
        od2.setPrice(1.7);
        od2.setSide("BUY");

        Order od3 = new Order();
        od3.setUserId("103");
        od3.setProduct("GOOGLE");
        od3.setQuantity(2);
        od3.setPrice(1.8);
        od3.setSide("Sell");

        order.put(od1.getUserId(),od1);
        order.put(od2.getUserId(),od2);
        order.put(od3.getUserId(),od3);
    }

    public Order getOrders(String userId){
        return order.get(userId);
    }
}
