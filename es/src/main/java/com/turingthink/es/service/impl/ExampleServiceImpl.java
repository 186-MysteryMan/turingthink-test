package com.turingthink.es.service.impl;

import com.turingthink.es.dao.entity.ExampleDocument;
import com.turingthink.es.dao.mapper.ExampleRepository;
import com.turingthink.es.service.ExampleService;
import com.turingthink.es.service.RabbitMqService;
import com.turingthink.es.service.dto.ElasticsearchDTO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

/**
 * @author GongJie Sheng
 * @date 2023/1/11 0:02
 */
@Service
public class ExampleServiceImpl implements ExampleService {

    @Autowired
    private ExampleRepository exampleRepository;
    @Autowired
    private RabbitMqService rabbitMqService;

    @Override
    public void addExample() {
        List<ElasticsearchDTO> exampleList = rabbitMqService.exampleList();
        List<ExampleDocument> exampleEntities = ElasticsearchDTO.convertToExample(exampleList);
        exampleRepository.saveAll(exampleEntities);
    }

    @Override
    public List<ElasticsearchDTO> exampleList(ElasticsearchDTO dto) {
        List<ExampleDocument> exampleDocumentList;
        if (StringUtils.isNotBlank(dto.getDescription()) && Objects.nonNull(dto.getType())) {
            exampleDocumentList = exampleRepository.findByDescriptionLikeAndTypeAndCreator(dto.getDescription(), dto.getType(), "盛攻杰");
        } else if (StringUtils.isNotBlank(dto.getDescription())) {
            exampleDocumentList = exampleRepository.findByDescriptionLike(dto.getDescription());
        } else if (Objects.nonNull(dto.getType())) {
            exampleDocumentList = exampleRepository.findByTypeAndCreator(dto.getType(), "盛攻杰");
        } else {
            exampleDocumentList = exampleRepository.findByCreator("盛攻杰");
        }
        return ElasticsearchDTO.convert(exampleDocumentList);
    }
//    docker run --name es8.5.1 -p 9201:9200 -p 9301:9300 \
//            -e "discovery.type=single-node" \
//            -e ES_JAVA_OPTS="-Xms256m -Xmx256m" \
//            -d elasticsearch:8.5.1
//
//    docker run -d --name es8.5.1 \
//            -p9200:9200 \
//            -p9300:9300 \
//            -e ES_JAVA_OPTS="-Xms1024m -Xmx1024m" \
//            -e "discovery.type=single-node" \
//            -v /usr/project/elasticsearch/elk/es8.5.1/config/elasticsearch.yml:/usr/share/elasticsearch/config/elasticsearch.yml \
//    elasticsearch:8.5.1
    //    docker exec -it es8.5.1 /bin/bash
    @Override
    public void deleteExample() {
        exampleRepository.deleteByCreator("盛攻杰");
    }
}
