package com.gsrk.graphql.mongodb.resolver;

import com.coxautodev.graphql.tools.GraphQLResolver;
import com.gsrk.graphql.mongodb.model.Author;
import com.gsrk.graphql.mongodb.model.Book;
import com.gsrk.graphql.mongodb.repository.AuthorRepository;

public class BookResolver implements GraphQLResolver<Book> {
    private AuthorRepository authorRepository;

    public BookResolver(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    public Author getAuthor(Book book) {
        return authorRepository.findOne(book.getAuthor().getId());
    }
}