package com.webmagic.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
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
@NoArgsConstructor
@Data
@TableName("article_data")
public class ArticleData implements Serializable {

   @TableId(type = IdType.ID_WORKER)
   private Long id;

   private String title;

   private String author;

   private String content;

   private LocalDateTime contentTime;

   private Long contentHot;

   private LocalDateTime createTime;

   private String url;

   private String resource;

   private Long resourceId;

   private String imgs;

   private String video;

   private String keyWord;
}
