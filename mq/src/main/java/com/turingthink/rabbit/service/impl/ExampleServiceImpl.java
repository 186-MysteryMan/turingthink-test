package com.turingthink.rabbit.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.turingthink.rabbit.dao.entity.ExampleEntity;
import com.turingthink.rabbit.dao.mapper.ExampleMapper;
import com.turingthink.rabbit.service.ExampleService;
import com.turingthink.rabbit.service.dto.RabbitMqDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author GongJie Sheng
 * @version v1.0.0
 * @Date 2023-01-10 15:40:06
 * @description
 */
@Service
public class ExampleServiceImpl implements ExampleService  {

    @Autowired
    private ExampleMapper exampleMapper;

    @Override
    public List<RabbitMqDTO> exampleList() {
        LambdaQueryWrapper<ExampleEntity> exampleQw =
                Wrappers.lambdaQuery(ExampleEntity.class).eq(ExampleEntity::getCreator, "盛攻杰");
        List<ExampleEntity> exampleEntities = exampleMapper.selectList(exampleQw);
        return RabbitMqDTO.convert(exampleEntities);
    }
}
