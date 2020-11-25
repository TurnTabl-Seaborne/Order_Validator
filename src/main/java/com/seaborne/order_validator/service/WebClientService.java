package com.seaborne.order_validator.service;

import com.seaborne.order_validator.model.StockData;
import org.springframework.web.reactive.function.client.WebClient;

public class WebClientService {

    private WebClient webClient;

    public WebClientService(String url) {
        this.webClient = WebClient.create(url);
    }

    StockData getProductMarketData(String ticker){

        StockData res = null;

        try {
            res = webClient.get()
                    .uri("/md/{ticker}", ticker)
                    .retrieve()
                    .bodyToMono(StockData.class)
                    .block();
        } catch (Exception e) {
            System.out.println("product does not exist");
        }

        return res;
    }
}
