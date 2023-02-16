package com.turingthink.es.dao.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.turingthink.es.enums.ExampleTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

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
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExampleDocument implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @Id
    private Long uid;

    /**
     * 创建人名称
     */
    @Field(type = FieldType.Text)
    private String creator;

    /**
     * 名称
     */
    @Field(type = FieldType.Text)
    private String name;

    /**
     * 描述
     */
    @Field(type = FieldType.Text, searchAnalyzer = "ik_smart", analyzer = "ik_smart")
    private String description;

    /**
     * 类型：默认0=未知；1=开发；2=测试；3=生产
     */
    @Field(type = FieldType.Text)
    private ExampleTypeEnum type;

    /**
     * 创建时间
     */
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second_millis)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @Field(type = FieldType.Date, format = DateFormat.date_hour_minute_second_millis)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
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
