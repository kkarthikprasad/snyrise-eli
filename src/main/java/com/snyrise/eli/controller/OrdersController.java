package com.snyrise.eli.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.snyrise.eli.entity.OrderEntity;
import com.snyrise.eli.model.Order;
import com.snyrise.eli.service.OrdersService;

@CrossOrigin(origins = "*")
@RestController
public class OrdersController {

  @Autowired
  private OrdersService service;

  @GetMapping("/journals/{journalName}/orders")
  public ResponseEntity<List<OrderEntity>> getAllOrdersOfJournal(
      @PathVariable(name = "journalName", required = true) String journalName) {
    return new ResponseEntity<>(service.allOrdersFromJournal(journalName), HttpStatus.OK);
  }

  @DeleteMapping("/journals/{journalName}/orders/{id}")
  public ResponseEntity<HttpStatus> deleteOrderOfJournalOfTrade(
      @PathVariable(name = "journalName") String journalName,
      @PathVariable(name = "id") String id) {
    service.deleteOrderById(journalName, id);
    return new ResponseEntity<>(HttpStatus.OK);
  }


  @PostMapping("/journals/{journalName}/orders")
  public ResponseEntity<OrderEntity> placeOrder(
      @PathVariable(name = "journalName") String journalName, @RequestBody Order order) {
    return new ResponseEntity<>(service.placeOrder(journalName, order), HttpStatus.CREATED);
  }

  @GetMapping("/journals/{journalName}/trades")
  public ResponseEntity<List<OrderEntity>> getOrdersGroupedByTradeId(
      @PathVariable(name = "journalName", required = true) String journalName,
      @RequestParam(name = "strategy", required = false) String strategy,
      @RequestParam(name = "exchange", required = false) String exchange,
      @RequestParam(name = "ticker", required = false) String ticker,
      @RequestParam(name = "fromDate", required = false) String fromDate,
      @RequestParam(name = "toDate", required = false) String toDate) {
    return new ResponseEntity<>(service.getOrdersOfJournalGroupedAsTrades(journalName, strategy,
        fromDate, toDate, ticker, exchange), HttpStatus.OK);
  }

  @DeleteMapping("/journals/{journalName}/trades/{tradeId}")
  public ResponseEntity<HttpStatus> deleteJournalTrade(
      @PathVariable(name = "journalName") String journalName,
      @PathVariable(name = "tradeId") String tradeId) {
    service.deleteTradeByTradeId(journalName, tradeId);
    return new ResponseEntity<>(HttpStatus.OK);
  }

}
