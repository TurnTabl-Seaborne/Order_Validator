package com.seaborne.order_validator.endpoint;

import com.seaborne.consumervalidator.SendOrderRequest;
import com.seaborne.consumervalidator.SendOrderResponse;
import com.seaborne.consumervalidator.ServiceStatus;
import com.seaborne.order_validator.exception.ServiceFaultException;
import com.seaborne.order_validator.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;


@Endpoint
public class OrderEndpoint{



    private static final String NAMESPACE_URI = "http://seaborne.com/ConsumerValidator";


    private OrderServiceImpl orderService;

    @Autowired
    public OrderEndpoint(OrderServiceImpl orderService){
        this.orderService = orderService;
    }

    @PayloadRoot(namespace = NAMESPACE_URI,localPart = "sendOrderRequest")
    @ResponsePayload
    public SendOrderResponse getOrders(@RequestPayload SendOrderRequest request){
        SendOrderResponse response = new SendOrderResponse();
        if(orderService.isOrderValid(request)){

            response.setStatusCode(HttpStatus.OK.value());
            response.setMessage("Valid");

            //TODO: publish to trading via redis
        }else{

            response.setStatusCode(HttpStatus.FORBIDDEN.value());
            response.setMessage("Invalid");

        }
        return response;
    }
}
