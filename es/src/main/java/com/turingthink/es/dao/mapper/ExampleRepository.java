package com.turingthink.es.dao.mapper;

import com.turingthink.es.dao.entity.ExampleEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @description:
 * @author: Administrator
 * @create: 2021-12-29 17:17
 **/
public interface ExampleRepository extends ElasticsearchRepository<ExampleEntity,String> {
}
