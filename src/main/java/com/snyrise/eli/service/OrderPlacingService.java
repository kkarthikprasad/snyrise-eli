package com.snyrise.eli.service;

import com.snyrise.eli.entity.OrderEntity;
import com.snyrise.eli.model.Order;

public interface OrderPlacingService {

  public OrderEntity placeOrder(String journalName, Order order);
  
}
