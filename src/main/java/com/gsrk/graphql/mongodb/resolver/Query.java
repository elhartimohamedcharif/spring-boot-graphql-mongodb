package com.gsrk.graphql.mongodb.resolver;

import java.util.List;

import com.coxautodev.graphql.tools.GraphQLQueryResolver;
import com.gsrk.graphql.mongodb.model.Author;
import com.gsrk.graphql.mongodb.model.Book;
import com.gsrk.graphql.mongodb.repository.AuthorRepository;
import com.gsrk.graphql.mongodb.repository.BookRepository;

public class Query implements GraphQLQueryResolver {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public Query(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    public Iterable<Author> findAllAuthors() {
        return authorRepository.findAll();
    }

    public long countBooks() {
        return bookRepository.count();
    }
    public long countAuthors() {
        return authorRepository.count();
    }
}