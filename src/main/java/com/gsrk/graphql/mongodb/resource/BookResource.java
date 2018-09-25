package com.gsrk.graphql.mongodb.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.gsrk.graphql.mongodb.model.Book;
import com.gsrk.graphql.mongodb.service.BookService;


@RestController
@RequestMapping(value="/book")
public class BookResource {
	
	@Autowired
	private BookService bookService;
	
	@RequestMapping(value="/all",method=RequestMethod.GET)
	@ResponseBody
	public List<Book> findAllBooks (){
		
		List<Book> booksList = bookService.findAllBooks();		
		return booksList;
	}
	
	@RequestMapping(value="{id}",method=RequestMethod.GET)
	@ResponseBody
	public Book findBookById (@PathVariable String id){
		Book book = bookService.findById(id)	;
		return book;
	}

}
