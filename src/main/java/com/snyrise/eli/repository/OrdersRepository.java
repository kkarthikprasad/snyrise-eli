package com.snyrise.eli.repository;

import java.util.List;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.snyrise.eli.entity.OrderEntity;

public interface OrdersRepository extends MongoRepository<OrderEntity, String> {

  public List<OrderEntity> findByStrategy(String strategy);
  
  public List<OrderEntity> findByJournalNameAndTradeId(String journalName, String tradeId);

  public List<OrderEntity> findByTicker(String ticker);

  public List<OrderEntity> findByExchange(String exchange);

  public List<OrderEntity> findByJournalName(String journalName);

  // public List<JournalOrder> findByFromDate(String fromDate);

  // public List<JournalOrder> findByToDate(String toDate);

  public void deleteByJournalName(String journalName);
}
