package com.turingthink.es.controller.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.turingthink.es.enums.ExampleTypeEnum;
import com.turingthink.es.service.dto.ElasticsearchDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

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
public class ElasticsearchVO {

    @Data
    public static class ExampleListVO {

        @ApiModelProperty(value = "主键ID", example = "主键ID")
        private Long uid;

        @ApiModelProperty(value = "创建人名称", example = "创建人名称")
        private String creator;

        @ApiModelProperty(value = "名称", example = "名称")
        private String name;

        @ApiModelProperty(value = "描述", example = "这是开发环境")
        private String description;

        @ApiModelProperty(value = "类型：默认UNKNOWN=未知；DEV=开发；TEST=测试；PROD=生产", example = "DEV")
        private ExampleTypeEnum type;

        @ApiModelProperty(value = "创建时间", example = "2023-01-10 17:12:31")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime createTime;

        @ApiModelProperty(value = "修改时间", example = "2023-01-10 17:12:31")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime updateTime;

        @ApiModelProperty(value = "是否可用：默认1=可用；0=不可用", example = "true")
        private Boolean enable;

        @ApiModelProperty(value = "是否删除：默认0=未删除；1=已删除", example = "true")
        private Boolean deleted;

        public static List<ExampleListVO> convert(List<ElasticsearchDTO> rabbitMqList) {
            List<ExampleListVO> exampleList = new ArrayList<>();
            for (ElasticsearchDTO elasticsearchDTO : rabbitMqList) {
                ExampleListVO exampleListVO = new ExampleListVO();
                BeanUtils.copyProperties(elasticsearchDTO, exampleListVO);
                exampleList.add(exampleListVO);
            }
            return exampleList;
        }
    }

    @Data
    public static class AddExampleForm {
        @ApiModelProperty(value = "类型：默认UNKNOWN=未知；DEV=开发；TEST=测试；PROD=生产", example = "DEV")
        private ExampleTypeEnum type;

        @ApiModelProperty(value = "描述", example = "这是开发环境")
        private String description;

        public ElasticsearchDTO convert() {
            ElasticsearchDTO elasticsearchDTO = new ElasticsearchDTO();
            BeanUtils.copyProperties(this, elasticsearchDTO);
            return elasticsearchDTO;
        }
    }
}
