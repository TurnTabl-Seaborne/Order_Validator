package com.seaborne.order_validator.model;

public class StockData {
    private double bidPrice;
    private double askPrice;
    private int buyLimit;
    private  String  product;
    private  int sellLimit;
    private  double lastTradedPrice;
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
