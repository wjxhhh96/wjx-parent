package com.webmagic.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.webmagic.entity.ArticleData;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * <b>ArticleDataMapper</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/10/15
 */
@Mapper
@Repository
public interface ArticleDataMapper extends BaseMapper<ArticleData> {
}
