package com.snyrise.eli.model;

public class Journal {

  private String name;
  private Double initial_capital;
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

  public boolean isPaperTradingJournal() {
    return isPaperTradingJournal;
  }

  public void setPaperTradingJournal(boolean isPaperTradingJournal) {
    this.isPaperTradingJournal = isPaperTradingJournal;
  }



}
