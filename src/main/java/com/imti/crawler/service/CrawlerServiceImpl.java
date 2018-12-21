package com.imti.crawler.service;

import com.imti.util.ValidationUtil;
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * Created by imteyaz on 21/10/18
 **/
@Service
public class CrawlerServiceImpl implements CrawlerService {

  private static final Logger logger = LoggerFactory.getLogger(CrawlerServiceImpl.class.getName());

  private final PageProcessor pageProcessor;

  @Autowired
  public CrawlerServiceImpl(PageProcessor pageProcessor) {
    this.pageProcessor = pageProcessor;
  }

  @Override
  @Async
  public void processUrl(String url) throws IOException {
    logger.info("Processing url {}", url);
    if (ValidationUtil.isReachable(url)) {
      Document doc = Jsoup.connect(url).get();
      pageProcessor.process(doc, url);
    }
  }

}
