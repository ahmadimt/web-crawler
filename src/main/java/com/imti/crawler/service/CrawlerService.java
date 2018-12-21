package com.imti.crawler.service;

import java.io.IOException;

/**
 * Created by imteyaz on 21/10/18
 **/

public interface CrawlerService {

  void processUrl(String url) throws IOException;
}
