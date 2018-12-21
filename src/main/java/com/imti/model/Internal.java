
package com.imti.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Internal {

  @JsonProperty("total")
  private long total;

  @JsonProperty("details")
  private List<Detail> details = new ArrayList<>();

  public long getTotal() {
    return total;
  }

  public void setTotal(long total) {
    this.total = total;
  }

  public List<Detail> getDetails() {
    return details;
  }

  public void setDetails(List<Detail> details) {
    this.details = details;
  }

  @Override
  public String toString() {
    return "Internal{" +
        "total=" + total +
        ", details=" + details +
        '}';
  }
}
