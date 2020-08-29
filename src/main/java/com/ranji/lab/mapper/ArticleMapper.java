package com.ranji.lab.mapper;

import com.ranji.lab.entity.Article;
import com.ranji.lab.entity.ArticleThumbnail;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Select;

/**
 * 文章Mapper接口
 */
public interface ArticleMapper {
    /**
     * 保存文章
     * @param article
     */
    @Insert("insert into t_article(title,summary,thumbnail_url,content,publisher,create_time,last_time) values(#{title},#{summary},#{thumbnailUrl},#{content},#{publisher},#{createTime},#{lastTime})")
    void saveArticle(Article article);

    /**
     * 根据ID获取文章
     * @param id
     * @return
     */
    @Select("select * from t_article where id=#{id}")
    Article findArticle(int id);

    /**
     * 保存文章缩略图
     * @param articleThumbnail
     */
    @Insert("insert into t_article_thumbnail(title,path) values(#{title},#{path})")
    @Options(useGeneratedKeys = true, keyColumn = "id", keyProperty = "id")
    void saveArticleThumbnail(ArticleThumbnail articleThumbnail);

    /**
     * 根据ID获取文章缩略图
     * @param id
     * @return
     */
    @Select("select * from t_article_thumbnail where id=#{id}")
    ArticleThumbnail findArticleThumbnail(int id);
}
