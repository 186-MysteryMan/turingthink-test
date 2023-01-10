package com.turingthink.rabbit.service.dto;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.turingthink.rabbit.controller.vo.RabbitMqVO;
import com.turingthink.rabbit.dao.entity.ExampleEntity;
import com.turingthink.rabbit.enums.ExampleTypeEnum;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author GongJie Sheng
 * @version v1.0.0
 * @Date 2023-01-10 15:35:46
 * @description
 */
@Data
public class RabbitMqDTO {
    /**
     * 主键ID
     */
    private Long uid;

    /**
     * 创建人名称
     */
    private String creator;

    /**
     * 名称
     */
    private String name;

    /**
     * 描述
     */
    private String description;

    /**
     * 类型：默认UNKNOWN=未知；DEV=开发；TEST=测试；PROD=生产
     */
    private ExampleTypeEnum type;

    /**
     * 延迟秒数
     */
    private Integer second;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    /**
     * 是否可用：默认1=可用；0=不可用
     */
    private Boolean enable;

    /**
     * 是否删除：默认0=未删除；1=已删除
     */
    private Boolean deleted;

    public static List<RabbitMqDTO> convert(List<ExampleEntity> exampleEntityList) {
        List<RabbitMqDTO> exampleList = new ArrayList<>();
        for (ExampleEntity exampleEntity : exampleEntityList) {
            RabbitMqDTO rabbitMqDTO = new RabbitMqDTO();
            BeanUtils.copyProperties(exampleEntity, rabbitMqDTO);
            exampleList.add(rabbitMqDTO);
        }
        return exampleList;
    }
}
