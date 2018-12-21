package com.imti.model;

/**
 * Created by imteyaz on 21/10/18
 **/

public class Error {

  private int statusCode;

  private String message;

  public Error() {
  }

  public Error(int statusCode, String message) {
    this.statusCode = statusCode;
    this.message = message;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public void setStatusCode(int statusCode) {
    this.statusCode = statusCode;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  @Override
  public String toString() {
    return "Error{" +
        "statusCode=" + statusCode +
        ", message='" + message + '\'' +
        '}';
  }
}
