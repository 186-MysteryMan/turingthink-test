package com.turingthink.es.service.impl;

import co.elastic.clients.elasticsearch._types.aggregations.Aggregation;
import com.turingthink.es.common.R;
import com.turingthink.es.dao.entity.ExampleDocument;
import com.turingthink.es.dao.mapper.ExampleRepository;
import com.turingthink.es.service.ExampleService;
import com.turingthink.es.service.RabbitMqService;
import com.turingthink.es.service.dto.ElasticsearchDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.client.elc.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.client.elc.NativeQuery;
import org.springframework.data.elasticsearch.client.elc.NativeQueryBuilder;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.BaseQuery;
import org.springframework.data.elasticsearch.core.query.Criteria;
import org.springframework.data.elasticsearch.core.query.CriteriaQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author GongJie Sheng
 * @date 2023/1/11 0:02
 */
@Service
@Slf4j
public class ExampleServiceImpl implements ExampleService {

    @Autowired
    private ExampleRepository exampleRepository;
    @Autowired
    private RabbitMqService rabbitMqService;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public void addExample() {
        R<List<ElasticsearchDTO>> exampleList = rabbitMqService.exampleList();
        List<ExampleDocument> exampleEntities = ElasticsearchDTO.convertToExample(exampleList.getData());
        exampleRepository.saveAll(exampleEntities);
    }

    @Override
    public List<ElasticsearchDTO> exampleList(ElasticsearchDTO dto) {
        Criteria criteria = new Criteria().and("creator").is("盛攻杰");
        if (StringUtils.isNotBlank(dto.getDescription())){
            criteria.and("description").is(dto.getDescription());
        }
        if (Objects.nonNull(dto.getType())){
            criteria.and("type").is(dto.getType());
        }
        Query query = new CriteriaQuery(criteria);
        SearchHits<ExampleDocument> search = elasticsearchTemplate.search(query, ExampleDocument.class);
        List<SearchHit<ExampleDocument>> searchHits = search.getSearchHits();
        if (!CollectionUtils.isEmpty(searchHits)) {
            List<ExampleDocument> exampleDocumentList = searchHits.stream().map(SearchHit::getContent).collect(Collectors.toList());
            return ElasticsearchDTO.convert(exampleDocumentList);
        }
        return Collections.emptyList();
    }

    @Override
    public void deleteExample() {
        rabbitMqService.deleteExample();
        exampleRepository.deleteByCreator("盛攻杰");
    }
}
