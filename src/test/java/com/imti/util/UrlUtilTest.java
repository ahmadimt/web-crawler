package com.imti.util;

import com.imti.model.RedirectionDetails;
import java.io.IOException;
import java.net.URISyntaxException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by imteyaz on 21/10/18
 **/

public class UrlUtilTest {

  @Test
  public void shouldReturnDomainName() throws URISyntaxException {
    String domain = UrlUtil.extractDomainName("https://www.scout24.com/en/Home.aspx");
    Assert.assertEquals("scout24.com", domain);
  }


  @Test
  public void shouldCheckRedirection() {
    RedirectionDetails details = UrlUtil
        .getRedirectionDetails("http://techblog.scout24.com/");
    Assert.assertEquals(301,details.getStatusCode());
    Assert.assertTrue(details.isRedirect());
    //UrlUtil.getRedirectionDetails("http://clairvoyantsoft.com/");
  }
}
