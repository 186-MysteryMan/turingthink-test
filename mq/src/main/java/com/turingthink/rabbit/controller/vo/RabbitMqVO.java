package com.turingthink.rabbit.controller.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.turingthink.rabbit.enums.ExampleTypeEnum;
import com.turingthink.rabbit.service.dto.RabbitMqDTO;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

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
public class RabbitMqVO {

    @Data
    public static class DelayQueueExampleForm {
        @ApiModelProperty(value = "类型：默认UNKNOWN=未知；DEV=开发；TEST=测试；PROD=生产", example = "DEV")
        private ExampleTypeEnum type;

        @ApiModelProperty(value = "延迟秒数", example = "1")
        private Integer second;

        public RabbitMqDTO convert() {
            RabbitMqDTO rabbitMqDTO = new RabbitMqDTO();
            BeanUtils.copyProperties(this, rabbitMqDTO);
            return rabbitMqDTO;
        }
    }

    @Data
    public static class ExampleListVO {

        @ApiModelProperty(value = "主键ID", example = "主键ID")
        private Long uid;

        @ApiModelProperty(value = "创建人名称", example = "创建人名称")
        private String creator;

        @ApiModelProperty(value = "名称", example = "名称")
        private String name;

        @ApiModelProperty(value = "描述", example = "描述")
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

        public static List<ExampleListVO> convert(List<RabbitMqDTO> rabbitMqList) {
            List<ExampleListVO> exampleList = new ArrayList<>();
            for (RabbitMqDTO rabbitMqDTO : rabbitMqList) {
                ExampleListVO exampleListVO = new ExampleListVO();
                BeanUtils.copyProperties(rabbitMqDTO, exampleListVO);
                exampleList.add(exampleListVO);
            }
            return exampleList;
        }
    }
}
