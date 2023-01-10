package com.turingthink.es.service.impl;

import com.turingthink.es.dao.mapper.ExampleRepository;
import com.turingthink.es.service.ExampleService;
import com.turingthink.es.service.RabbitMqService;
import com.turingthink.es.service.dto.ElasticsearchDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public List<ElasticsearchDTO> exampleList() {
        List<ElasticsearchDTO> exampleListVOS = rabbitMqService.exampleList();
        return exampleListVOS;
    }
}
