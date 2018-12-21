package com.imti.crawler.repo;

import com.imti.model.Result;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by imteyaz on 22/10/18
 **/

public interface ResultRepo extends MongoRepository<Result,String> {

  Result findByUrl(String url);

}
