package com.turingthink.es.dao.mapper;

import com.turingthink.es.dao.entity.ExampleDocument;
import com.turingthink.es.enums.ExampleTypeEnum;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @description:
 * @author: Administrator
 * @create: 2021-12-29 17:17
 **/
public interface ExampleRepository extends ElasticsearchRepository<ExampleDocument,Long> {

    /**
     * 根据描述模糊匹配和类型匹配和姓名匹配
     * @param description
     * @param type
     * @param creator
     * @return
     */
    List<ExampleDocument> findByDescriptionLikeAndTypeAndCreator(String description, ExampleTypeEnum type, String creator);

    /**
     * 根据描述模糊匹配和类型匹配和姓名匹配
     * @param type
     * @param creator
     * @return
     */
    List<ExampleDocument> findByTypeAndCreator(ExampleTypeEnum type, String creator);

    /**
     * 根据描述模糊匹配和类型匹配和姓名匹配
     * @param description
     * @param creator
     * @return
     */
    List<ExampleDocument> findByDescriptionLikeAndCreator(String description, String creator);

    /**
     * 根据描述模糊匹配和类型匹配和姓名匹配
     * @param creator
     * @return
     */
    List<ExampleDocument> findByCreator(String creator);

    /**
     * 删除通过创建人
     * @param creator
     * @return
     */
    void deleteByCreator(String creator);

    /**
     * 通过描述模糊
     * @param description
     * @return
     */
    List<ExampleDocument> findByDescriptionLike(String description);
}
