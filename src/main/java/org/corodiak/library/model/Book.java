package org.corodiak.library.model;

import java.sql.Date;

public class Book extends MaterialImpl {
	private int bookOid;
	private String bookAuthor;
	private String bookPublisher;
	private Date bookPublishDate;
	private String bookIsbn;
	
	public Book() {
		// TODO Auto-generated constructor stub
	}
	
	public int getBookOid() { return bookOid; }
	public String getBookAuthor() { return bookAuthor; }
	public String getBookPublisher() { return bookPublisher; }
	public Date getBookPublishDate() { return bookPublishDate; }
	public String getBookIsbn() { return bookIsbn; }
	
	public void setBookOid(int bookOid) { this.bookOid = bookOid; }
	public void setBookAuthor(String bookAuthor) { this.bookAuthor = bookAuthor; }
	public void setBookPublisher(String bookPublisher) { this.bookPublisher = bookPublisher; }
	public void setBookPublishDate(Date bookPublishDate) { this.bookPublishDate = bookPublishDate; }
	public void setBookIsbn(String bookIsbn) { this.bookIsbn = bookIsbn; }
}
