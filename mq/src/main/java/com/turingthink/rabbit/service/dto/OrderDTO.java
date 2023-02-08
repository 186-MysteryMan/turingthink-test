package com.turingthink.rabbit.service.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author GongJie Sheng
 * @version v1.0.0
 * @Date 2023-02-08 17:20:21
 * @description
 */
@Data
public class OrderDTO {


    /**
     * 主键ID
     */
    private Long uid;

    /**
     * 创建人名称
     */
    private String creator;

    /**
     * 商品ID
     */
    private Long goodsId;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 订单费金额
     */
    private BigDecimal fee;

    /**
     * 下单数量
     */
    private Integer count;

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
}
