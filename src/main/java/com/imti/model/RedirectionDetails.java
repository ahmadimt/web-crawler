package com.imti.model;

/**
 * Created by imteyaz on 22/10/18
 **/

public class RedirectionDetails {

  private int statusCode;

  private String message;

  private boolean redirect;

  private String redirectUrl;

  public RedirectionDetails() {
  }

  public RedirectionDetails(int statusCode, String message, boolean redirect,
      String redirectUrl) {
    this.statusCode = statusCode;
    this.message = message;
    this.redirect = redirect;
    this.redirectUrl = redirectUrl;
  }

  public int getStatusCode() {
    return statusCode;
  }

  public String getMessage() {
    return message;
  }

  public boolean isRedirect() {
    return redirect;
  }

  public String getRedirectUrl() {
    return redirectUrl;
  }

  @Override
  public String toString() {
    return "RedirectionDetails{" +
        "statusCode=" + statusCode +
        ", redirect=" + redirect +
        ", redirectUrl='" + redirectUrl + '\'' +
        '}';
  }
}
