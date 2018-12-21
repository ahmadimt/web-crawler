package com.imti.api;

import com.imti.crawler.service.CrawlerService;
import com.imti.crawler.service.ResultService;
import com.imti.model.Result;
import com.imti.util.UrlUtil;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by imteyaz on 20/10/18
 **/

@RestController
@RequestMapping(value = CrawlerResources.BASE_URL, produces = MediaType.APPLICATION_JSON_VALUE)
public class CrawlerResources {

  static final String BASE_URL = "/api/v1/crawler";

  private static final Pattern BLOCKED_EXTENSIONS = Pattern
      .compile(".*\\.(bmp|gif|jpg|png|pdf|txt|jpeg)$");

  private final CrawlerService crawlerService;

  private final ResultService resultService;

  @Autowired
  public CrawlerResources(CrawlerService crawlerService,
      ResultService resultService) {
    this.crawlerService = crawlerService;
    this.resultService = resultService;
  }

  @PostMapping
  public ResponseEntity<Response> requestCrawling(@RequestBody String url) throws IOException {
    Response response = new Response();
    if (!UrlUtil.validateUrl(url)) {
      response.setStatus(HttpStatus.OK.value());
      response.setError("Please enter a valid url");
      return ResponseEntity.ok().body(response);
    }
    if (BLOCKED_EXTENSIONS.matcher(url).matches()) {
      response.setError("Can not crawl as the url can not return html");
      response.setStatus(HttpStatus.OK.value());
      return ResponseEntity.ok().body(response);
    } else {
      crawlerService.processUrl(url);
      response.setMessage("Started crawling..");
      response.setStatus(HttpStatus.OK.value());
      return ResponseEntity.ok(response);
    }
  }

  @GetMapping(value = "/result")
  public ResponseEntity<Result> getResult(String url) {

    Result result = resultService.getResult(url);
    if (Objects.nonNull(result)) {
      return ResponseEntity.ok(result);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
    }
  }

}
