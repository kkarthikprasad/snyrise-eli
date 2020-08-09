package com.snyrise.eli.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.Date;
import org.springframework.stereotype.Service;
import com.snyrise.eli.entity.OrderEntity;
import com.snyrise.eli.model.Order;

@Service
public class DummyOrderPlacingService implements OrderPlacingService {

  @Override
  public OrderEntity placeOrder(String journalName, Order order) {

    OrderEntity journalOrderEntity = new OrderEntity();
    journalOrderEntity.setId(
        order.getTicker() + order.getStrategy() + (new Timestamp(new Date().getTime())).toString());
    journalOrderEntity
        .setTradeId(order.getTicker() + order.getStrategy() + (LocalDate.now()).toString());
    journalOrderEntity.setJournalName(journalName);
    journalOrderEntity.setStrategy(order.getStrategy());
    journalOrderEntity.setTicker(order.getTicker());
    journalOrderEntity.setStrategyComment(order.getStrategyComment());
    journalOrderEntity.setExchange(order.getExchange());
    journalOrderEntity.setOrderType(order.getOrderType());
    journalOrderEntity.setTradeType(order.getTradeType());
    journalOrderEntity.setQuantity(order.getQuantity());
    journalOrderEntity.setLimitOrderPrice(order.getLimitOrderPrice());
    journalOrderEntity.setMarketOrderPrice(order.getMarketOrderPrice());
    journalOrderEntity.setRangeOrderPrice(order.getRangeOrderPrice());
    journalOrderEntity.setTime((new Timestamp(new Date().getTime())).toString());
    journalOrderEntity.setStopLoss(order.getStopLoss());
    journalOrderEntity.setTarget(order.getTarget());
    journalOrderEntity.setTrailingStopLoss(order.getTrailingStopLoss());
    journalOrderEntity.setTriggerPrice(order.getTriggerPrice());
    journalOrderEntity
        .setBoughtPrice(order.getLimitOrderPrice() != null ? order.getLimitOrderPrice()
            : order.getMarketOrderPrice() != null ? order.getMarketOrderPrice()
                : order.getRangeOrderPrice().getHigh());


    return journalOrderEntity;
  }

}
