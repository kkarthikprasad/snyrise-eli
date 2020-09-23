package com.snyrise.eli.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.snyrise.eli.entity.JournalEntity;
import com.snyrise.eli.exceptions.OperationNotAllowedException;
import com.snyrise.eli.model.Journal;
import com.snyrise.eli.repository.JournalsRepository;
import com.snyrise.eli.repository.OrdersRepository;

@Service
public class JournalsService {

  @Autowired
  private JournalsRepository journalsRepository;

  @Autowired
  private OrdersRepository ordersRepository;

  public List<JournalEntity> getAllJournals() {
    return journalsRepository.findAll();
  }

  public JournalEntity getJournalByName(String journalName) {
    return journalsRepository.findByName(journalName);
  }

  public JournalEntity createJournal(Journal journal) {
    JournalEntity journalEntity = new JournalEntity();
    journalEntity.setName(journal.getName().trim());
    journalEntity.setInitial_capital(journal.getInitial_capital());
    journalEntity.setBalance(journal.getInitial_capital());
    journalEntity.setPaperTradingJournal(journal.isPaperTradingJournal());

    journalsRepository.save(journalEntity);

    return journalEntity;
  }

  public JournalEntity updateJournal(String journalName, Journal journal) {
    JournalEntity oldJournal = journalsRepository.findByName(journalName);

    if (!oldJournal.isPaperTradingJournal()) {
      throw new OperationNotAllowedException(
          "Operation not allowed. Can't update a non paper trading journal");
    }

    JournalEntity newJournal = new JournalEntity();
    newJournal.setName(journal.getName());
    newJournal.setInitial_capital(journal.getInitial_capital());

    // Calculate profit and loss and add that to the new capital so that profit or loss is applied
    // to new capital and new balance is given
    Double profitOrLoss = oldJournal.getBalance() - oldJournal.getInitial_capital();
    newJournal.setBalance(journal.getInitial_capital() + profitOrLoss);

    journalsRepository.delete(oldJournal);
    journalsRepository.save(newJournal);

    return newJournal;
  }

  public void deleteJournal(String journalName) {
    JournalEntity journal = journalsRepository.findByName(journalName);
    journalsRepository.delete(journal);
    ordersRepository.deleteByJournalName(journalName);
  }

}
