
package com.imti.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)

public class Detail {

  @JsonProperty("info")
  private Info info;

  public Info getInfo() {
    return info;
  }

  public void setInfo(Info info) {
    this.info = info;
  }

  @Override
  public String toString() {
    return "Detail{" +
        "info=" + info +
        '}';
  }
}
