package com.imti.util;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import org.jsoup.Connection.Response;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.helper.HttpConnection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by imteyaz on 21/10/18
 **/

public final class ValidationUtil {

  private static final Logger logger = LoggerFactory.getLogger(ValidationUtil.class.getName());

  private ValidationUtil() {
    throw new IllegalStateException("You are trying to access private constructor");
  }

  public static boolean isReachable(String url) {
    HttpURLConnection connection = null;
    try {
      connection = (HttpURLConnection) new URL(url).openConnection();
      connection.setRequestMethod("GET");
      int responseCode = connection.getResponseCode();
      if (responseCode == 200) {
        return true;
      }
    } catch (MalformedURLException e) {
      logger.error("Failed to parse the url", e);
      return false;
    } catch (IOException e) {
      logger.error("Failed due to ", e);
      return false;
    } finally {
      if (connection != null) {
        connection.disconnect();
      }
    }
    return false;
  }

  public static HttpResponse checkReachability(String url) {
    HttpResponse response;
    try {
      Response response1 = Jsoup.connect(url).userAgent(HttpConnection.DEFAULT_UA).execute();
      response = new HttpResponse(response1.statusCode(), response1.statusMessage());
    } catch (HttpStatusException e) {
      response = new HttpResponse(e.getStatusCode(), e.getMessage());
    } catch (IOException e) {
      response = new HttpResponse(500, "Internal Server error");
    }
    return response;
  }
}
