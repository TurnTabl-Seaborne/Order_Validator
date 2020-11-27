package com.seaborne.order_validator.model;

import com.fasterxml.jackson.annotation.JsonProperty;

public class StockData {
    @JsonProperty("BID_PRICE")
    private double bidPrice;
    @JsonProperty("ASK_PRICE")
    private double askPrice;
    @JsonProperty("BUY_LIMIT")
    private int buyLimit;
    @JsonProperty("TICKER")
    private  String  product;
    @JsonProperty("SELL_LIMIT")
    private  int sellLimit;
    @JsonProperty("LAST_TRADED_PRICE")
    private  double lastTradedPrice;
    @JsonProperty("MAX_PRICE_SHIFT")
    private  double maxPriceShift;

    public  StockData(){

    }

    public StockData(double bidPrice, double askPrice, int buyLimit, String ticker, int sellLimit, double lastTradedPrice, double maxPriceShift) {
        this.bidPrice = bidPrice;
        this.askPrice = askPrice;
        this.buyLimit = buyLimit;
        this.product = ticker;
        this.sellLimit = sellLimit;
        this.lastTradedPrice = lastTradedPrice;
        this.maxPriceShift = maxPriceShift;
    }

    public double getBidPrice() {
        return bidPrice;
    }

    public double getAskPrice() {
        return askPrice;
    }

    public int getBuyLimit() {
        return buyLimit;
    }

    public String getProduct() {
        return product;
    }

    public int getSellLimit() {
        return sellLimit;
    }

    public double getLastTradedPrice() {
        return lastTradedPrice;
    }

    public double getMaxPriceShift() {
        return maxPriceShift;
    }

    @Override
    public String toString() {
        return "StockData{" +
                "bidPrice=" + bidPrice +
                ", askPrice=" + askPrice +
                ", buyLimit=" + buyLimit +
                ", ticker='" + product + '\'' +
                ", sellLimit=" + sellLimit +
                ", lastTradedPrice=" + lastTradedPrice +
                ", maxPriceShift=" + maxPriceShift +
                '}';
    }

}
