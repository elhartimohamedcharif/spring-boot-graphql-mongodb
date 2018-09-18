package com.gsrk.graphql.mongodb;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.gsrk.graphql.mongodb.exception.GraphQLErrorAdapter;
import com.gsrk.graphql.mongodb.model.Author;
import com.gsrk.graphql.mongodb.model.Book;
import com.gsrk.graphql.mongodb.repository.AuthorRepository;
import com.gsrk.graphql.mongodb.repository.BookRepository;
import com.gsrk.graphql.mongodb.resolver.BookResolver;
import com.gsrk.graphql.mongodb.resolver.Mutation;
import com.gsrk.graphql.mongodb.resolver.Query;

import graphql.ExceptionWhileDataFetching;
import graphql.GraphQLError;
import graphql.servlet.GraphQLErrorHandler;

@SpringBootApplication
public class GraphQLMongoDBApplication 
{
    public static void main( String[] args ) {
        System.out.println( "Hi GraphQLMongoDBApplication ...." );
        SpringApplication.run(GraphQLMongoDBApplication.class, args);
        System.out.println("GraphQLMongoDBApplication Started ....");
    }
    
    @Bean
    public BookResolver authorResolver(AuthorRepository authorRepository) {
    	return new BookResolver(authorRepository);
    }
    
    @Bean
    public Query query(AuthorRepository authorRepository, BookRepository bookRepository) {
    	return new Query(authorRepository, bookRepository);
    }
    
    @Bean
    public Mutation mutation(AuthorRepository authorRepository, BookRepository bookRepository) {
    	
    	return new Mutation(authorRepository, bookRepository);
    }
    
    
    @Bean
	public CommandLineRunner demo(final AuthorRepository authorRepository, final BookRepository bookRepository) {
		return (args) -> {
			Author author = new Author("Herbert", "Schildt");
			authorRepository.save(author);

			bookRepository.save(new Book("Java: A Beginner's Guide, Sixth Edition", "0071809252", 728, author));
		};
	}
    
    @Bean
	public GraphQLErrorHandler errorHandler() {
		return new GraphQLErrorHandler() {
			@Override
			public List<GraphQLError> processErrors(List<GraphQLError> errors) {
				List<GraphQLError> clientErrors = errors.stream()
						.filter(this::isClientError)
						.collect(Collectors.toList());

				List<GraphQLError> serverErrors = errors.stream()
						.filter(e -> !isClientError(e))
						.map(GraphQLErrorAdapter::new)
						.collect(Collectors.toList());

				List<GraphQLError> e = new ArrayList<>();
				e.addAll(clientErrors);
				e.addAll(serverErrors);
				return e;
			}

			protected boolean isClientError(GraphQLError error) {
				return !(error instanceof ExceptionWhileDataFetching || error instanceof Throwable);
			}
		};
	}

}
