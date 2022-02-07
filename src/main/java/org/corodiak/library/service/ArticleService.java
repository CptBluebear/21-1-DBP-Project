package org.corodiak.library.service;

import java.util.ArrayList;

import org.corodiak.library.mapper.ArticleMapper;
import org.corodiak.library.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {
	
	@Autowired
	ArticleMapper articleMapper;
	
	public ArrayList<Article> searchArticle(Article article) throws Exception
	{
		return articleMapper.selectArticleList(article);
	}
}
