package com.snyrise.eli.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import com.snyrise.eli.enums.OrderType;
import com.snyrise.eli.enums.TradeType;
import com.snyrise.eli.model.RangeOrderPrice;

@Document(collection = "Orders")
public class OrderEntity {

  @Id
  private String id;
  private String tradeId;
  private String journalName;
  private String strategy;
  private String ticker;
  private String strategyComment;
  private String exchange;
  private OrderType orderType;
  private TradeType tradeType;
  private String quantity;
  private String limitOrderPrice;
  private String marketOrderPrice;
  private RangeOrderPrice rangeOrderPrice;
  private String boughtPrice;
  private String stopLoss;
  private String target;
  private String trailingStopLoss;
  private String triggerPrice;
  private String time;

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getTradeId() {
    return tradeId;
  }

  public void setTradeId(String tradeId) {
    this.tradeId = tradeId;
  }

  public String getJournalName() {
    return journalName;
  }

  public void setJournalName(String journalName) {
    this.journalName = journalName;
  }

  public String getStrategy() {
    return strategy;
  }

  public void setStrategy(String strategy) {
    this.strategy = strategy;
  }

  public String getTicker() {
    return ticker;
  }

  public void setTicker(String ticker) {
    this.ticker = ticker;
  }

  public String getStrategyComment() {
    return strategyComment;
  }

  public void setStrategyComment(String strategyComment) {
    this.strategyComment = strategyComment;
  }

  public String getExchange() {
    return exchange;
  }

  public void setExchange(String exchange) {
    this.exchange = exchange;
  }

  public OrderType getOrderType() {
    return orderType;
  }

  public void setOrderType(OrderType orderType) {
    this.orderType = orderType;
  }

  public TradeType getTradeType() {
    return tradeType;
  }

  public void setTradeType(TradeType tradeType) {
    this.tradeType = tradeType;
  }

  public String getQuantity() {
    return quantity;
  }

  public void setQuantity(String quantity) {
    this.quantity = quantity;
  }

  public String getLimitOrderPrice() {
    return limitOrderPrice;
  }

  public void setLimitOrderPrice(String limitOrderPrice) {
    this.limitOrderPrice = limitOrderPrice;
  }

  public String getMarketOrderPrice() {
    return marketOrderPrice;
  }

  public void setMarketOrderPrice(String marketOrderPrice) {
    this.marketOrderPrice = marketOrderPrice;
  }

  public RangeOrderPrice getRangeOrderPrice() {
    return rangeOrderPrice;
  }

  public void setRangeOrderPrice(RangeOrderPrice rangeOrderPrice) {
    this.rangeOrderPrice = rangeOrderPrice;
  }

  public String getBoughtPrice() {
    return boughtPrice;
  }

  public void setBoughtPrice(String boughtPrice) {
    this.boughtPrice = boughtPrice;
  }

  public String getStopLoss() {
    return stopLoss;
  }

  public void setStopLoss(String stopLoss) {
    this.stopLoss = stopLoss;
  }

  public String getTarget() {
    return target;
  }

  public void setTarget(String target) {
    this.target = target;
  }

  public String getTrailingStopLoss() {
    return trailingStopLoss;
  }

  public void setTrailingStopLoss(String trailingStopLoss) {
    this.trailingStopLoss = trailingStopLoss;
  }

  public String getTriggerPrice() {
    return triggerPrice;
  }

  public void setTriggerPrice(String triggerPrice) {
    this.triggerPrice = triggerPrice;
  }

  public String getTime() {
    return time;
  }

  public void setTime(String time) {
    this.time = time;
  }

}
