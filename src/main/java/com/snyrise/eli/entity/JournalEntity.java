package com.snyrise.eli.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collation = "Journals")
public class JournalEntity {

  @Id
  private String name;
  private Double initial_capital;
  private Double balance;
  private boolean isPaperTradingJournal;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public Double getInitial_capital() {
    return initial_capital;
  }

  public void setInitial_capital(Double initial_capital) {
    this.initial_capital = initial_capital;
  }

  public Double getBalance() {
    return balance;
  }

  public void setBalance(Double balance) {
    this.balance = balance;
  }

  public boolean isPaperTradingJournal() {
    return isPaperTradingJournal;
  }

  public void setPaperTradingJournal(boolean isPaperTradingJournal) {
    this.isPaperTradingJournal = isPaperTradingJournal;
  }



}
