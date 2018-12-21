package com.imti.config;

import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_HEADERS;
import static org.springframework.http.HttpHeaders.ACCESS_CONTROL_ALLOW_ORIGIN;
import static org.springframework.http.HttpHeaders.CONTENT_DISPOSITION;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Created by imteyaz on 21/10/18
 **/

@Configuration
public class CorsConfiguration implements WebMvcConfigurer {

  @Value("${cors.allowed.origin.path:/**}")
  private String allowedPath;
  @Value("${cors.allowed.origin:*}")
  private String allowedOrigin;

  @Value("${cors.allowed.headers:*}")
  private String allowedHeaders;


  @Override
  public void addCorsMappings(CorsRegistry registry) {
    registry.addMapping(allowedPath)
        .allowedOrigins(allowedOrigin)
        .allowedMethods(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.OPTIONS.name())
        .allowedHeaders(allowedHeaders)
        .exposedHeaders(ACCESS_CONTROL_ALLOW_HEADERS, ACCESS_CONTROL_ALLOW_ORIGIN,
            CONTENT_DISPOSITION);
  }
}
