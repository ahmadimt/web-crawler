
package com.imti.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Links {

  @JsonProperty("total")
  private int total;

  @JsonProperty("internal")
  private Internal internal;

  @JsonProperty("external")
  private External external;

  public int getTotal() {
    return total;
  }

  public void setTotal(int total) {
    this.total = total;
  }

  public Internal getInternal() {
    return internal;
  }

  public void setInternal(Internal internal) {
    this.internal = internal;
  }

  public External getExternal() {
    return external;
  }

  public void setExternal(External external) {
    this.external = external;
  }

  @Override
  public String toString() {
    return "Links{" +
        "total=" + total +
        ", internal=" + internal +
        ", external=" + external +
        '}';
  }
}
