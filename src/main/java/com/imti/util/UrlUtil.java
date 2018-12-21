package com.imti.util;

import com.imti.model.RedirectionDetails;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

/**
 * Created by imteyaz on 21/10/18
 **/

public final class UrlUtil {

  private static final Logger logger = LoggerFactory.getLogger(UrlUtil.class.getName());

  private UrlUtil() {
    throw new IllegalStateException("You are trying to access private constructor");
  }

  public static String extractDomainName(String url) throws URISyntaxException {

    URI uri = new URI(url);
    String host = uri.getHost();
    if (StringUtils.hasText(host)) {
      if (host.startsWith("www.")) {
        host = host.substring(4);
      }
      return host;
    }
    return null;
  }

  public static RedirectionDetails getRedirectionDetails(String url) {
    try {
      Response response = Jsoup.connect(url).followRedirects(false).execute();
      return new RedirectionDetails(response.statusCode(), response.statusMessage(),
          response.hasHeader("location"), response.header("location"));
    } catch (IOException e) {
      logger.error("Failed to get redirection details for {} with exception", url, e);
    }
    return new RedirectionDetails();
  }

  public static boolean validateUrl(String url) {
    boolean flag;
    try {
      new URL(url).toURI();
      flag = true;
    } catch (MalformedURLException e) {
      logger.error("Malformed url", url, e);
      flag = false;
    } catch (URISyntaxException e) {
      logger.error("Uri Systax error", url, e);
      flag = false;
    }
    return flag;
  }
}
