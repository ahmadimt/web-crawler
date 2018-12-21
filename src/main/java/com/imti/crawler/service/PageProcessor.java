package com.imti.crawler.service;

import com.imti.model.Result;
import org.jsoup.nodes.Document;

/**
 * Created by imteyaz on 20/10/18
 **/

public interface PageProcessor {

  Result process(Document document,String url);
}
