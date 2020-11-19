package com.seaborne.order_validator.service;

import com.seaborne.consumervalidator.GetOrderRequest;
import com.seaborne.consumervalidator.Order;
import com.seaborne.order_validator.model.StockData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;

import static java.lang.Math.abs;


@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Boolean isOrderValid(GetOrderRequest orderRequest) {

        Boolean isOrderValid = false;

        StockData stockdata1 = restTemplate.getForObject("https://exchange.matraining.com/md/"+orderRequest.getTicker()  , StockData.class);
        StockData stockdata2 = restTemplate.getForObject("https://exchange2.matraining.com/md/"+orderRequest.getTicker()  , StockData.class);


        StockData AvData =
                new StockData(
                        (stockdata1.getBidPrice()+stockdata2.getBidPrice())/2,
                        (stockdata1.getAskPrice()+stockdata2.getAskPrice())/2,
                        stockdata2.getBuyLimit(),
                        orderRequest.getTicker(),
                        stockdata1.getSellLimit(),
                        Math.max(stockdata1.getLastTradedPrice(), stockdata2.getLastTradedPrice()),
                        Math.max(stockdata1.getMaxPriceShift(), stockdata2.getMaxPriceShift())
                );



        if(orderRequest.getSide().matches("BUY")){
            if(AvData.getBidPrice()!=0){
                if (((orderRequest.getPrice()/AvData.getBidPrice())*100 < 120)){
                    isOrderValid = true;
                }
            }else{
                if (((orderRequest.getPrice()*100) < 120)){
                    isOrderValid = true;
                }
            }
        }

        if(orderRequest.getSide().matches("SELL")){
            if(AvData.getAskPrice()!=0){
                if (((orderRequest.getPrice() / AvData.getAskPrice()) * 100) > AvData.getAskPrice() * 120) {
                    isOrderValid = true;
                }
            }else{
                if ((orderRequest.getPrice() * 100) > AvData.getAskPrice() * 120) {
                    isOrderValid = true;
                }
            }

        }

        return isOrderValid;

        }
}
