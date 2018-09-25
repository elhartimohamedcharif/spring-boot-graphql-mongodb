package com.gsrk.graphql.mongodb.service;

import java.util.List;

import com.gsrk.graphql.mongodb.model.Book;

public interface BookService {
	
	List<Book> findAllBooks();
	
	Book findById(String id);

}
