package org.corodiak.library.model;

public class Article extends MaterialImpl {
	private int articleOid;
	private String articleAuthor;
	private String articleRa;
	private String articleKeyword;
	
	public Article() {
		// TODO Auto-generated constructor stub
	}
	
	public int getArticleOid() { return articleOid; }
	public String getArticleAuthor() { return articleAuthor; }
	public String getArticleRa() { return articleRa; }
	public String getArticleKeyword() { return articleKeyword; }
	
	public void setArticleOid(int articleOid) { this.articleOid = articleOid; }
	public void setArticleAuthor(String articleAuthor) { this.articleAuthor = articleAuthor; }
	public void setArticleRa(String articleRa) { this.articleRa = articleRa; }
	public void setArticleKeyword(String articleKeyword) { this.articleKeyword = articleKeyword; }
}
