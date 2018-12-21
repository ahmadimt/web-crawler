package com.imti.api;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * Created by imteyaz on 21/10/18
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Response {

  private String message;

  private int status;

  private String error;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
  }

  public String getError() {
    return error;
  }

  public void setError(String error) {
    this.error = error;
  }

  @Override
  public String toString() {
    return "Response{" +
        "message='" + message + '\'' +
        ", status=" + status +
        ", error='" + error + '\'' +
        '}';
  }
}
