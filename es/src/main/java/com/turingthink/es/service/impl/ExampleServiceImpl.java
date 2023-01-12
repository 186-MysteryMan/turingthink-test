package com.turingthink.es.service.impl;

import com.turingthink.es.common.R;
import com.turingthink.es.dao.entity.ExampleDocument;
import com.turingthink.es.dao.mapper.ExampleRepository;
import com.turingthink.es.service.ExampleService;
import com.turingthink.es.service.RabbitMqService;
import com.turingthink.es.service.dto.ElasticsearchDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Objects;

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

    @Override
    public void addExample() {
        R<List<ElasticsearchDTO>> exampleList = rabbitMqService.exampleList();
        List<ExampleDocument> exampleEntities = ElasticsearchDTO.convertToExample(exampleList.getData());
        exampleRepository.saveAll(exampleEntities);
    }

    @Override
    public List<ElasticsearchDTO> exampleList(ElasticsearchDTO dto) {
        List<ExampleDocument> exampleDocumentList;
        if (StringUtils.isNotBlank(dto.getDescription()) && Objects.nonNull(dto.getType())) {
            exampleDocumentList =
                    exampleRepository.findByDescriptionLikeAndTypeAndCreator(dto.getDescription(), dto.getType(), "盛攻杰");
        } else if (StringUtils.isNotBlank(dto.getDescription())) {
            exampleDocumentList = exampleRepository.findByDescriptionLikeAndCreator(dto.getDescription(), "盛攻杰");
        } else if (Objects.nonNull(dto.getType())) {
            exampleDocumentList = exampleRepository.findByTypeAndCreator(dto.getType(), "盛攻杰");
        } else {
            exampleDocumentList = exampleRepository.findByCreator("盛攻杰");
        }
        return ElasticsearchDTO.convert(exampleDocumentList);
    }

    @Override
    public void deleteExample() {
        rabbitMqService.deleteExample();
        exampleRepository.deleteByCreator("盛攻杰");
    }
}
