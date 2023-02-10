package com.turingthink.es.service.impl;

import com.turingthink.rabbit.common.R;
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
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.SearchHits;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
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
        Query query = NativeQuery.builder()
                .withQuery(q1 -> q1.bool(b -> {
                    if (StringUtils.isNotBlank(dto.getDescription())) {
                        b.filter(m -> m.match(ma -> ma.field("description").query(dto.getDescription())));
                    }
                    if (Objects.nonNull(dto.getType())) {
                        b.filter(m -> m.match(ma -> ma.field("type").query(dto.getType().getValue())));
                    }
                    return b.filter(m -> m.match(ma -> ma.field("creator").query("盛攻杰")));
                })).build();
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
