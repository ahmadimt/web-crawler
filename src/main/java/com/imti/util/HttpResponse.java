package com.imti.util;

/**
 * Created by imteyaz on 21/10/18
 **/

public class HttpResponse {

  private int status;

  private String message;

  public HttpResponse(int status, String message) {
    this.status = status;
    this.message = message;
  }

  public int getStatus() {
    return status;
  }

  public String getMessage() {
    return message;
  }
}
