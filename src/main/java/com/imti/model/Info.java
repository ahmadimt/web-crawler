
package com.imti.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Info {

  @JsonProperty("url")
  private String url;

  @JsonProperty("reachable")
  private boolean reachable;

  @JsonProperty("error")
  private Error error;

  @JsonProperty("protocol")
  private String protocol;

  @JsonProperty("redirection_details")
  private RedirectionDetails details;

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    this.url = url;
  }

  public boolean isReachable() {
    return reachable;
  }

  public void setReachable(boolean reachable) {
    this.reachable = reachable;
  }

  public RedirectionDetails getDetails() {
    return details;
  }

  public void setDetails(RedirectionDetails details) {
    this.details = details;
  }

  public Error getError() {
    return error;
  }

  public void setError(Error error) {
    this.error = error;
  }

  public String getProtocol() {
    return protocol;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }


  @Override
  public String toString() {
    return "Info{" +
        "url='" + url + '\'' +
        ", reachable=" + reachable +
        ", error='" + error + '\'' +
        ", protocol='" + protocol + '\'' +
        '}';
  }
}
