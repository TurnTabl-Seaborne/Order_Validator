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

    @Override
    public Boolean isOrderValid(SendOrderRequest orderRequest) {

        boolean isOrderValid = false;

        if (
                productExists(orderRequest.getProduct())
                &&
                nonNegativePrice(orderRequest.getPrice())
        )
            isOrderValid = true;

//        WebClientService webClientService = new WebClientService("https://exchange.matraining.com");
//        WebClientService webClientService1 = new WebClientService("https://exchange2.matraining.com");
//
//        StockData stockdata1 = webClientService.getData(orderRequest.getProduct());
//        StockData stockdata2 = webClientService1.getData(orderRequest.getProduct());
//
//        StockData AvData =
//                new StockData(
//                        (stockdata1.getBidPrice()+stockdata2.getBidPrice())/2,
//                        (stockdata1.getAskPrice()+stockdata2.getAskPrice())/2,
//                        stockdata2.getBuyLimit(),
//                        orderRequest.getProduct(),
//                        stockdata1.getSellLimit(),
//                        Math.max(stockdata1.getLastTradedPrice(), stockdata2.getLastTradedPrice()),
//                        Math.max(stockdata1.getMaxPriceShift(), stockdata2.getMaxPriceShift())
//                );
//
//
//
//        if(orderRequest.getSide().matches("BUY")){
//            // Check if the price is within 120% of the average price on both exchanges
//            if(AvData.getBidPrice()!=0){
//                if (((orderRequest.getPrice()/AvData.getBidPrice())*100 < 120)){
//                    isOrderValid = true;
//                }
//            }else{
//                if (((orderRequest.getPrice()*100) < 120)){
//                    isOrderValid = true;
//                }
//            }
//        }
//
//        if(orderRequest.getSide().matches("SELL")){
//            if(AvData.getAskPrice()!=0){
//                if (((orderRequest.getPrice() / AvData.getAskPrice()) * 100) > AvData.getAskPrice() * 120) {
//                    isOrderValid = true;
//                }
//            }else{
//                if ((orderRequest.getPrice() * 100) > AvData.getAskPrice() * 120) {
//                    isOrderValid = true;
//                }
//            }
//
//        }

        return isOrderValid;

        }
}
