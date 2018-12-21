package com.imti.crawler.service;

import com.imti.crawler.repo.ResultRepo;
import com.imti.model.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by imteyaz on 22/10/18
 **/
@Service
public class ResultServiceImpl implements ResultService {

  private final ResultRepo resultRepo;

  @Autowired
  public ResultServiceImpl(ResultRepo resultRepo) {
    this.resultRepo = resultRepo;
  }

  @Override
  public Result getResult(String url) {
    return resultRepo.findByUrl(url);
  }
}
