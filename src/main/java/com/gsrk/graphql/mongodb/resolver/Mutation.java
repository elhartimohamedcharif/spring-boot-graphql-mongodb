package com.gsrk.graphql.mongodb.resolver;

import com.coxautodev.graphql.tools.GraphQLMutationResolver;
import com.gsrk.graphql.mongodb.exception.BookNotFoundException;
import com.gsrk.graphql.mongodb.model.Author;
import com.gsrk.graphql.mongodb.model.Book;
import com.gsrk.graphql.mongodb.repository.AuthorRepository;
import com.gsrk.graphql.mongodb.repository.BookRepository;

public class Mutation implements GraphQLMutationResolver {
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;

    public Mutation(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    public Author newAuthor(String firstName, String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);

        authorRepository.save(author);

        return author;
    }

    public Book newBook(String title, String isbn, Integer pageCount, String authorId) {
        Book book = new Book();
        book.setAuthor(new Author(authorId));
        book.setTitle(title);
        book.setIsbn(isbn);
        book.setPageCount(pageCount != null ? pageCount : 0);

        bookRepository.save(book);

        return book;
    }

    public boolean deleteBook(String authorId) {
        bookRepository.delete(authorId);
        return true;
    }

    public Book updateBookPageCount(Integer pageCount, String authorId) {
        Book book = bookRepository.findOne(authorId);
        if(book == null) {
            throw new BookNotFoundException("The book to be updated was found", authorId);
        }
        book.setPageCount(pageCount);

        bookRepository.save(book);

        return book;
    }
}