package com.snyrise.eli.model;

public class RangeOrderPrice {

  private String high;
  private String low;

  public RangeOrderPrice(String high, String low) {
    this.high = high;
    this.low = low;
  }

  public String getHigh() {
    return high;
  }

  public String getLow() {
    return low;
  }

}
