package com.turingthink.rabbit.controller.vo;

import com.turingthink.rabbit.service.dto.OrderDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.BeanUtils;

import javax.validation.constraints.NotNull;

/**
 * @author GongJie Sheng
 * @version v1.0.0
 * @Date 2023-02-08 16:52:45
 * @description
 */
@Data
@ApiModel
public class OrderVO {

    @Data
    @ApiModel
    public static class CreateOrderForm {

        @ApiModelProperty(value = "商品ID", example = "1")
        @NotNull(message = "goodsId:{NotNull}")
        private Long goodsId;

        @ApiModelProperty(value = "下单数量", example = "1")
        @NotNull(message = "count:{NotNull}")
        private Integer count;
        public OrderDTO convert() {
            OrderDTO orderDTO = new OrderDTO();
            BeanUtils.copyProperties(this, orderDTO);
            return orderDTO;
        }
    }
}
