package com.turingthink.rabbit.dao.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.turingthink.rabbit.enums.ExampleTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author GongJie Sheng
 * @since 2022-05-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("t_order")
public class OrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId("id")
    private Long uid;

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
    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    /**
     * 是否可用：默认1=可用；0=不可用
     */
    @TableField("is_enabled")
    private Boolean enable;

    /**
     * 是否删除：默认0=未删除；1=已删除
     */
    @TableField("is_deleted")
    private Boolean deleted;
}
