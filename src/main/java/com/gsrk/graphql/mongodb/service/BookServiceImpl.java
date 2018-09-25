package com.gsrk.graphql.mongodb.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gsrk.graphql.mongodb.model.Book;
import com.gsrk.graphql.mongodb.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired
	private BookRepository bookRepository;

	@Override
	public List<Book> findAllBooks() {
		// TODO Auto-generated method stub
		return bookRepository.findAll();
	}

	@Override
	public Book findById(String id) {
		// TODO Auto-generated method stub
		return bookRepository.findOne(id);
	}
	
	

}
