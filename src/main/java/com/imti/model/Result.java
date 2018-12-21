
package com.imti.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result {

  @Id
  @JsonProperty("url")
  private String url;

  @JsonProperty("links")
  private Links links;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public Links getLinks() {
    return links;
  }

  public void setLinks(Links links) {
    this.links = links;
  }

  @Override
  public String toString() {
    return "Result{" +
        "url='" + url + '\'' +
        ", links=" + links +
        '}';
  }
}
