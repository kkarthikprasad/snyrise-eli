package com.snyrise.eli.service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.snyrise.eli.entity.JournalEntity;
import com.snyrise.eli.entity.OrderEntity;
import com.snyrise.eli.enums.OrderType;
import com.snyrise.eli.exceptions.OperationNotAllowedException;
import com.snyrise.eli.model.Order;
import com.snyrise.eli.repository.JournalsRepository;
import com.snyrise.eli.repository.OrdersRepository;

@Service
public class OrdersService {

  @Autowired
  private OrderPlacingService orderPlacingService;

  @Autowired
  private OrdersRepository ordersRepository;

  @Autowired
  private JournalsRepository journalsRepository;

  public OrderEntity placeOrder(String journalName, Order order) {

    // TODO validate Order object and its values
    order.setTicker(order.getTicker().trim());
    order.setStrategy(order.getStrategy().trim());
    order.setExchange(order.getExchange().trim());

    // place the order with orderPlacingService
    OrderEntity journalOrder = orderPlacingService.placeOrder(journalName, order);

    // log the order
    ordersRepository.save(journalOrder);

    // Updating the balance of the journal
    updateJournalBalance(journalOrder);

    return journalOrder;
  }

  public List<OrderEntity> getOrdersOfJournalGroupedAsTrades(String journalName, String strategy,
      String fromDate, String toDate, String ticker, String exchange) {

    List<OrderEntity> filteredJournalLogs = null;
    if (strategy == null && fromDate == null && toDate == null && ticker == null
        && exchange == null) {
      filteredJournalLogs = ordersRepository.findByJournalName(journalName);
    } else if (strategy != null) {
      if (fromDate == null && toDate == null && ticker == null && exchange == null) {
        filteredJournalLogs = ordersRepository.findByStrategy(strategy);
      } else if (fromDate != null && toDate == null && ticker == null && exchange == null) {
        // findByStrategyAndGreaterThanFromDate
      }
    }



    return filteredJournalLogs;
  }

  public void deleteOrderById(String journalName, String id) {

    if (isPaperTradingJournal(journalName)) {
      Optional<OrderEntity> order = ordersRepository.findById(id);
      if (order.isPresent()) {
        revertOrderForDeletion(order.get());
        ordersRepository.deleteById(id);
      } else {
        throw new OperationNotAllowedException("Passed Id doesn't not exist : " + id);
      }
    } else {
      throw new OperationNotAllowedException(
          "Journal is not a paper trading journal :" + journalName);
    }
  }

  public void deleteTradeByTradeId(String journalName, String tradeId) {
    if (isPaperTradingJournal(journalName)) {
      List<OrderEntity> ordersList =
          ordersRepository.findByJournalNameAndTradeId(journalName, tradeId);
      ordersList.parallelStream().forEach(order -> {
        revertOrderForDeletion(order);
        ordersRepository.deleteById(order.getId());
      });
    } else {
      throw new OperationNotAllowedException(
          "Journal is not a paper trading journal :" + journalName);
    }
  }
  
  
  public List<OrderEntity> allOrdersFromJournal(String journalName)
  {
    return ordersRepository.findByJournalName(journalName);
  }
  

  /**
   * @param order , Have to revert all the profit and loss done from this order to the balance.
   *        Reverting the BUY to SELL and vice versa and then calling updateJournalBalance would
   *        revert the profit or loss made by that order.
   */
  private void revertOrderForDeletion(OrderEntity order) {
    order.setOrderType((order.getOrderType() != null && order.getOrderType().equals(OrderType.BUY))
        ? OrderType.SELL
        : OrderType.BUY);
    updateJournalBalance(order);
  }


  private void updateJournalBalance(OrderEntity journalOrder) {

    // BUY - money deducted from balance, SELL - money added to balance it may be more or less from
    // what it was earlier deducted

    JournalEntity journal = journalsRepository.findByName(journalOrder.getJournalName());
    if (OrderType.BUY.equals(journalOrder.getOrderType())) {
      Double updatedBalance = journal.getBalance() - (Integer.parseInt(journalOrder.getQuantity())
          * Double.parseDouble(journalOrder.getBoughtPrice()));
      journalsRepository.delete(journal);
      journal.setBalance(updatedBalance);
      journalsRepository.save(journal);
    } else {
      // OrderType is SELL
      Double updatedBalance = journal.getBalance() + (Integer.parseInt(journalOrder.getQuantity())
          * Double.parseDouble(journalOrder.getBoughtPrice()));
      journalsRepository.delete(journal);
      journal.setBalance(updatedBalance);
      journalsRepository.save(journal);
    }
  }

  private boolean isPaperTradingJournal(String journalName) {
    return journalsRepository.findByName(journalName).isPaperTradingJournal();
  }

}
