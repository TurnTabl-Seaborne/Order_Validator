package com.seaborne.order_validator.endpoint;


import com.seaborne.consumervalidator.GetOrderRequest;
import com.seaborne.consumervalidator.GetOrderResponse;
import com.seaborne.consumervalidator.ServiceStatus;
import com.seaborne.order_validator.exception.ServiceFaultException;
import com.seaborne.order_validator.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @PayloadRoot(namespace = NAMESPACE_URI,localPart = "getOrderRequest")
    @ResponsePayload
    public GetOrderResponse getOrders(@RequestPayload GetOrderRequest request){
        GetOrderResponse response = new GetOrderResponse();
        //response.setOrder(orderService.getOrders(request.getPortfolioId()));
        if(orderService.isOrderValid(request)){
            return response;
            //TODO: publish to trading via redis
        }else{
            throw new ServiceFaultException("ERROR",new ServiceStatus("900","Invalid Order"));
        }
    }
}
