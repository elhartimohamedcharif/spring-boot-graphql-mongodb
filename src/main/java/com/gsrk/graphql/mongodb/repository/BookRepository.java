package com.gsrk.graphql.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gsrk.graphql.mongodb.model.Book;

public interface BookRepository extends MongoRepository<Book, String> {

}
