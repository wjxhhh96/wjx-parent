package com.webmagic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * <p>
 * <b>ArticleData</b>
 * </p>
 *
 * @author Wjx
 * @Description: TODO
 * @since 2021/10/15
 */
@Data
@TableName("article_data")
public class ArticleData {

   @TableId(type = IdType.ID_WORKER)
   private Long id;

   private String title;

   private String author;

   private String content;

   private LocalDateTime contentTime;

   private Long contentHot;

   private LocalDateTime createTime;

   private String url;
}
