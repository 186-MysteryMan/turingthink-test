package com.turingthink.es.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.turingthink.es.dao.entity.ExampleDocument;
import com.turingthink.es.enums.ExampleTypeEnum;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author GongJie Sheng
 * @version v1.0.0
 * @Date 2023-01-10 15:35:46
 * @description
 */
@Data
public class
ElasticsearchDTO {
    @JsonFormat
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
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateTime;

    /**
     * 是否可用：默认1=可用；0=不可用
     */
    private Boolean enable;

    /**
     * 是否删除：默认0=未删除；1=已删除
     */
    private Boolean deleted;

    public static List<ElasticsearchDTO> convert(List<ExampleDocument> exampleDocumentList) {
        List<ElasticsearchDTO> elasticsearchList = new ArrayList<>();
        for (ExampleDocument exampleDocument : exampleDocumentList) {
            ElasticsearchDTO elasticsearchDTO = new ElasticsearchDTO();
            BeanUtils.copyProperties(exampleDocument, elasticsearchDTO);
            elasticsearchList.add(elasticsearchDTO);
        }
        return elasticsearchList;
    }

    public static List<ExampleDocument> convertToExample(List<ElasticsearchDTO> list) {
        List<ExampleDocument> exampleList = new ArrayList<>();
        for (ElasticsearchDTO elasticsearchDTO : list) {
            ExampleDocument exampleDocument = new ExampleDocument();
            BeanUtils.copyProperties(elasticsearchDTO, exampleDocument);
            exampleList.add(exampleDocument);
        }
        return exampleList;
    }
}
