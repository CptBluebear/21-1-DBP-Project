package org.corodiak.library.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Map;

import org.corodiak.library.mapper.ArticleMapper;
import org.corodiak.library.mapper.BookMapper;
import org.corodiak.library.mapper.DigitalMapper;
import org.corodiak.library.mapper.MaterialMapper;
import org.corodiak.library.mapper.MultiMediaMapper;
import org.corodiak.library.model.Article;
import org.corodiak.library.model.Book;
import org.corodiak.library.model.Digital;
import org.corodiak.library.model.Material;
import org.corodiak.library.model.MediaType;
import org.corodiak.library.model.MultiMedia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MaterialService {

	@Autowired
	MaterialMapper materialMapper;
	
	@Autowired
	ArticleMapper articleMapper;
	
	@Autowired
	BookMapper bookMapper;
	
	@Autowired
	DigitalMapper digitalMapper;
	
	@Autowired
	MultiMediaMapper multiMediaMapper;
	
	@Transactional
	public boolean addMaterial(Map<String, Object> param) throws Exception
	{
		String materialType = (String)param.get("materialType");
		String materialOid = (String)param.get("materialOid");
		String materialName = (String)param.get("materialName");
		Date materialRegisterDate = Date.valueOf(LocalDate.now());
		int materialPrice = (int)param.get("materialPrice");
		int materialLoanable = (int)param.get("materialLoanable");
		
		switch (materialType) {
		case "article":
			Article article =  new Article();
			String articleAuthor = (String)param.get("articleAuthor");
			String articleRa = (String)param.get("articleRa");
			String articleKeyword = (String)param.get("articleKeyword");
			
			article.setMaterialOid(materialOid);
			article.setMaterialName(materialName);
			article.setMaterialRegisterDate(materialRegisterDate);
			article.setMaterialPrice(materialPrice);
			article.setMaterialLoanable(materialLoanable);
			article.setArticleAuthor(articleAuthor);
			article.setArticleRa(articleRa);
			article.setArticleKeyword(articleKeyword);
			
			materialMapper.insertMaterial(article);
			articleMapper.insertArticle(article);
			break;
		
		case "book":
			Book book = new Book();
			String bookAuthor = (String)param.get("bookAuthor");
			String bookPublisher = (String)param.get("bookPublisher");
			String bookPublishDate = (String)param.get("bookPublishDate");
			String bookIsbn = (String)param.get("bookIsbn");
			
			book.setMaterialOid(materialOid);
			book.setMaterialName(materialName);
			book.setMaterialRegisterDate(materialRegisterDate);
			book.setMaterialPrice(materialPrice);
			book.setMaterialLoanable(materialLoanable);
			book.setBookAuthor(bookAuthor);
			book.setBookPublisher(bookPublisher);
			book.setBookPublishDate(Date.valueOf(bookPublishDate));
			book.setBookIsbn(bookIsbn);
			
			materialMapper.insertMaterial(book);
			bookMapper.insertBook(book);
			break;
		
		case "digital":
			Digital digital = new Digital();
			String digitalFilePath = (String)param.get("digitalFilePath");
			
			digital.setMaterialOid(materialOid);
			digital.setMaterialName(materialName);
			digital.setMaterialRegisterDate(materialRegisterDate);
			digital.setMaterialPrice(materialPrice);
			digital.setMaterialLoanable(materialLoanable);
			digital.setDigitalFilePath(digitalFilePath);
			
			materialMapper.insertMaterial(digital);
			digitalMapper.insertDigital(digital);
			break;
		
		case "multiMedia":
			MultiMedia multiMedia = new MultiMedia();
			int multiMediaType = (int)param.get("multiMediaType");
			
			MediaType mediaType = new MediaType();
			mediaType.setMediaTypeOid(multiMediaType);
			multiMedia.setMaterialOid(materialOid);
			multiMedia.setMaterialName(materialName);
			multiMedia.setMaterialRegisterDate(materialRegisterDate);
			multiMedia.setMaterialPrice(materialPrice);
			multiMedia.setMaterialLoanable(materialLoanable);
			multiMedia.setMultiMediaType(mediaType);
			
			materialMapper.insertMaterial(multiMedia);
			multiMediaMapper.insertMultiMedia(multiMedia, multiMedia.getMultiMediaType());
			break;

		default:
			return false;
		}
		
		return true;
	}
	
	@Transactional
	public boolean removeMaterial(String materialOid) throws Exception
	{
		materialMapper.deleteMaterialByOid(materialOid);
		int result = 0;
		
		result += articleMapper.deleteArticleByMaterialId(materialOid);
		result += bookMapper.deleteBookByMaterialId(materialOid);
		result += digitalMapper.deleteDigitalByMaterialId(materialOid);
		result += multiMediaMapper.deleteMultiMediaByMaterialId(materialOid);
		
		if(result > 0) return true;
		else return false;
	}
	
	@Transactional
	public ArrayList<Material> searchMaterial(Material material) throws Exception
	{
		return materialMapper.selectMaterialList(material);
	}
	
}
