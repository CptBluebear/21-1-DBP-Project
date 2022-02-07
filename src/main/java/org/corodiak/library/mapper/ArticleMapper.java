package org.corodiak.library.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.corodiak.library.model.Article;

@Mapper
public interface ArticleMapper {
	
	ArrayList<Article> selectArticleList() throws Exception;
	ArrayList<Article> selectArticleList(RowBounds rowBounds) throws Exception;
	ArrayList<Article> selectArticleList(@Param("article")Article article) throws Exception;
	ArrayList<Article> selectArticleList(@Param("article")Article article, RowBounds rowBounds) throws Exception;
	
	int insertArticle(@Param("article")Article article) throws Exception;
	
	int deleteArticleByMaterialId(@Param("materialOid")String oid) throws Exception;
}
