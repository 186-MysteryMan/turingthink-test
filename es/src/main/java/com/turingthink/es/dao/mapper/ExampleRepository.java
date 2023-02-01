package com.turingthink.es.dao.mapper;

import com.turingthink.es.dao.entity.ExampleDocument;
import com.turingthink.es.enums.ExampleTypeEnum;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: Administrator
 * @create: 2021-12-29 17:17
 **/
public interface ExampleRepository extends ElasticsearchRepository<ExampleDocument,Long> {
    /**
     * 删除通过创建人
     * @param creator
     * @return
     */
    void deleteByCreator(String creator);
}
