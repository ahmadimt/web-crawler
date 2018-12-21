package com.imti.crawler.service;

import com.imti.crawler.repo.ResultRepo;
import com.imti.model.Detail;
import com.imti.model.Error;
import com.imti.model.External;
import com.imti.model.Info;
import com.imti.model.Internal;
import com.imti.model.Links;
import com.imti.model.Result;
import com.imti.util.HttpResponse;
import com.imti.util.UrlUtil;
import com.imti.util.ValidationUtil;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * Created by imteyaz on 20/10/18
 **/
@Component
public class PageProcessorImpl implements PageProcessor {

  private static final Logger logger = LoggerFactory.getLogger(PageProcessorImpl.class.getName());

  private final ResultRepo resultRepo;

  @Autowired
  public PageProcessorImpl(ResultRepo resultRepo) {
    this.resultRepo = resultRepo;
  }

  @Override
  public Result process(Document document, String url) {
    Result result = new Result();
    String domain = getParentDomain(url);
    Elements links = document.select("a[href]");
    result.setUrl(url);
    Links links1 = new Links();
    links1.setTotal(links.size());
    result.setLinks(links1);
    Internal internal = new Internal();
    populateInternalLinks(domain, links, internal);
    External external = new External();
    populateExternalLinks(domain, links, external);
    links1.setExternal(external);
    links1.setInternal(internal);
    logger.info("Number of links {}", links.size());
    result.setLinks(links1);
    resultRepo.save(result);
    return result;
  }

  private void populateInternalLinks(String domain, Elements links, Internal internal) {
    internal.setTotal(links.stream()
        .filter(element -> StringUtils.hasText(element.absUrl("href")))
        .filter(element -> isInternal(element, domain))
        .count());
    List<Detail> details = links.stream()
        .filter(PageProcessorImpl::filterValidUrls)
        .filter(element -> isInternal(element, domain))
        .map(this::getDetails).collect(Collectors.toList());
    internal.setDetails(details);
  }

  private void populateExternalLinks(String domain, Elements links, External external) {
    external.setTotal(links.stream()
        .filter(element -> StringUtils.hasText(element.absUrl("href")))
        .filter(element -> !isInternal(element, domain))
        .count());
    List<Detail> details = links.stream()
        .filter(PageProcessorImpl::filterValidUrls)
        .filter(element -> !isInternal(element, domain))
        .map(this::getDetails).collect(Collectors.toList());
    external.setDetails(details);
  }

  private static boolean filterValidUrls(Element element) {
    String absUrl = element.absUrl("href");
    return StringUtils.hasText(absUrl) && (
        absUrl.contains("http") || absUrl.contains("https"));
  }

  private boolean isInternal(Element element, String domain) {
    return element.absUrl("href").contains(domain);
  }


  private Detail getDetails(Element element) {
    Detail detail = new Detail();
    String absUrl = element.absUrl("href");
    Info info = new Info();
    info.setUrl(absUrl);
    info.setProtocol(extractProtocol(absUrl));
    boolean reachable;

    HttpResponse response = ValidationUtil.checkReachability(absUrl);
    if (response.getStatus() == 200) {
      reachable = true;
    } else {
      reachable = false;
      info.setError(new Error(response.getStatus(), response.getMessage()));
    }
    info.setReachable(reachable);
    info.setDetails(UrlUtil.getRedirectionDetails(absUrl));
    detail.setInfo(info);
    return detail;
  }

  private String extractProtocol(String urlStr) {
    String protocol = null;
    try {
      URL url = new URL(urlStr);
      protocol = url.getProtocol();
    } catch (MalformedURLException e) {
      logger.error("Failed to generate url from ", e);
    }
    return protocol;
  }


  private String getParentDomain(String url) {
    String domain = null;
    try {
      domain = UrlUtil.extractDomainName(url);
    } catch (URISyntaxException e) {
      logger.error("Failed to parse base uri for", e);
    }
    return domain;
  }
}
