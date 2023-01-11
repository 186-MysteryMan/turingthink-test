package com.turingthink.es.dao.entity;

import com.turingthink.es.enums.ExampleTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author GongJie Sheng
 * @since 2022-05-19
 */
@Document(indexName="example")
@Data
public class ExampleEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Id
    private Long uid;

    /**
     * 创建人名称
     */
    @Field(type = FieldType.Keyword)
    private String creator;

    /**
     * 名称
     */
    @Field(type = FieldType.Keyword)
    private String name;

    /**
     * 描述
     */
    @Field(type = FieldType.Keyword)
    private String description;

    /**
     * 类型：默认0=未知；1=开发；2=测试；3=生产
     */
    @Field(type = FieldType.Keyword)
    private ExampleTypeEnum type;

    /**
     * 创建时间
     */
    @Field(type = FieldType.Date)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @Field(type = FieldType.Date)
    private LocalDateTime updateTime;

    /**
     * 是否可用：默认1=可用；0=不可用
     */
    @Field(type = FieldType.Boolean)
    private Boolean enable;

    /**
     * 是否删除：默认0=未删除；1=已删除
     */
    @Field(type = FieldType.Boolean)
    private Boolean deleted;
}
