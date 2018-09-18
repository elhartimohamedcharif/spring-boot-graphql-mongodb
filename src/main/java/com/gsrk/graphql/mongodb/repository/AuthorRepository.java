package com.gsrk.graphql.mongodb.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.gsrk.graphql.mongodb.model.Author;

public interface AuthorRepository  extends MongoRepository<Author, String> {

}
