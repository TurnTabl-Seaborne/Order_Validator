package com.seaborne.order_validator.service;

import com.seaborne.consumervalidator.SendOrderRequest;
import com.seaborne.order_validator.model.StockData;
import org.springframework.stereotype.Service;



@Service
public class OrderServiceImpl implements OrderService{

    static final WebClientService WEB_CLIENT_EXCHANGE_SERVICE_1 = new WebClientService("https://exchange.matraining.com");
    static final WebClientService WEB_CLIENT_EXCHANGE_SERVICE_2 = new WebClientService("https://exchange2.matraining.com");

    public boolean productExists(String orderProductName){

        boolean pass = false;

        StockData sd_1 = WEB_CLIENT_EXCHANGE_SERVICE_1.getProductMarketData(orderProductName);

        if (sd_1 != null)
            pass = true;

        return pass;
    }

    public boolean nonNegativePrice(double orderPrice){

        boolean pass = false;

        if (orderPrice > 0)
            pass = true;

        return pass;
    }

    public boolean withinLimit(SendOrderRequest orderRequest){

        StockData sd_1 = WEB_CLIENT_EXCHANGE_SERVICE_1.getProductMarketData(orderRequest.getProduct());
//        System.out.println(sd_1);

        boolean pass = false;

        if (orderRequest.getSide().equals("BUY")){
            if (orderRequest.getQuantity() <= sd_1.getBuyLimit())
                pass = true;
        }else if (orderRequest.getSide().equals("SELL")){
            if (orderRequest.getQuantity() <= sd_1.getSellLimit())
                pass = true;
        }

        return pass;
    }

    public boolean withinShift(SendOrderRequest orderRequest){

        boolean pass = false;

        StockData sd_1 = WEB_CLIENT_EXCHANGE_SERVICE_1.getProductMarketData(orderRequest.getProduct());
//        System.out.println(sd_1);

        double lower = sd_1.getAskPrice() - sd_1.getMaxPriceShift();
        double upper = sd_1.getAskPrice() + sd_1.getMaxPriceShift();
        double price = orderRequest.getPrice();

        if (price >= lower && price <= upper){
            pass = true;
        }

        return pass;

    }

    @Override
    public Boolean isOrderValid(SendOrderRequest orderRequest) {

        boolean isOrderValid = false;

        if (
                productExists(orderRequest.getProduct())
                &&
                nonNegativePrice(orderRequest.getPrice())
                &&
                withinLimit(orderRequest)
                &&
                withinShift(orderRequest)
        ) {
            isOrderValid = true;
        }

        return isOrderValid;

        }
}
