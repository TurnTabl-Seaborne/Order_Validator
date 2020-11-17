package com.seaborne.order_validator.endpoint;


import com.seaborne.consumervalidator.GetOrderRequest;
import com.seaborne.consumervalidator.GetOrderResponse;
import com.seaborne.order_validator.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import org.springframework.ws.soap.server.endpoint.annotation.SoapAction;

@Endpoint
public class OrderEndpoint {

    private static final String NAMESPACE_URI = "http://seaborne.com/ConsumerValidator";


    private OrderService orderService;

    @Autowired
    public OrderEndpoint(OrderService orderService){
        this.orderService = orderService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI,localPart = "getOrderRequest")
    @ResponsePayload
    public GetOrderResponse getOrders(@RequestPayload GetOrderRequest request){
        GetOrderResponse response = new GetOrderResponse();
        response.setOrder(orderService.getOrders(request.getUserId()));
        return response;
    }
}
