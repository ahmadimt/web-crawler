package com.imti.util;

import org.junit.Assert;
import org.junit.Test;

/**
 * Created by imteyaz on 21/10/18
 **/

public class ValidationUtilTest {

  @Test
  public void shouldReturnTrue(){
    boolean reachable = ValidationUtil.isReachable("https://www.scout24.com/en/Home.aspx");
    Assert.assertTrue(reachable);
  }

  @Test
  public void shouldReturnFalse(){
    boolean reachable = ValidationUtil.isReachable("http://localhost:8000");
    Assert.assertFalse(reachable);
  }
}
