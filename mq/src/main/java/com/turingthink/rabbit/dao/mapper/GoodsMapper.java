package com.turingthink.rabbit.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.turingthink.rabbit.dao.entity.GoodsEntity;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author GongJie Sheng
 * @since 2022-05-19
 */
public interface GoodsMapper extends BaseMapper<GoodsEntity> {

    /**
     * 减少库存
     *
     * @param goodsId
     * @param count
     * @return
     */
    @Update("update t_goods set stock = stock - #{count} where stock > #{count} and id = #{goodsId}")
    boolean reduceStock(@Param("goodsId") Long goodsId, @Param("count") Integer count);

    /**
     * 增加库存
     *
     * @param goodsId
     * @param count
     * @return
     */
    @Update("update t_goods set stock = stock + #{count} where id = #{goodsId}")
    void restoreStock(@Param("goodsId") Long goodsId, @Param("count") Integer count);
}
