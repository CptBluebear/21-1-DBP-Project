package org.corodiak.library.service;

import java.util.ArrayList;

import org.corodiak.library.mapper.BookMapper;
import org.corodiak.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {
	
	@Autowired
	BookMapper bookMapper;
	
	public ArrayList<Book> searchBook(Book book) throws Exception
	{
		return bookMapper.selectBookList(book);
	}
}
