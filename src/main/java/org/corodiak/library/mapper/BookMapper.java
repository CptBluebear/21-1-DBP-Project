package org.corodiak.library.mapper;

import java.util.ArrayList;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.corodiak.library.model.Book;

@Mapper
public interface BookMapper {
	ArrayList<Book> selectBookList() throws Exception;
	ArrayList<Book> selectBookList(RowBounds rowBounds) throws Exception;
	ArrayList<Book> selectBookList(@Param("book")Book book) throws Exception;
	ArrayList<Book> selectBookList(@Param("book")Book book, RowBounds rowBounds) throws Exception;
	
	int insertBook(@Param("book")Book book) throws Exception;
	
	int deleteBookByMaterialId(@Param("materialOid")String oid) throws Exception;
}
