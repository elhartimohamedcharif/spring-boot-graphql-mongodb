# spring-boot-graphql-mongodb

Install MongoDB in Windows.

start mongoDB:

1.  Create Directories in C Drive.
        a.  C:\data
        b.  C:\data\db
2.  Goto the folder: 
       a. run the mongod.exe
       
GraphQL:

To check the database or to http://localhost:8080/graphiql to start executing queries. For example:

# Save Author:

mutation {
  newAuthor(firstName: "SivaRama", lastName: "Krishna") {
    id
    firstName
    lastName
  }
}

# Save Book:

mutation {
  newBook(
    title: "Spring Boot MongoDB", 
    isbn: "1259589331", 
    pageCount:155
    author: "5ba0e8f64e9c0e3ad4743fef") {
      id title
  }
}

# Find All Authours:

{
  findAllAuthors {
    id
    firstName
    lastName
  }
}

# Find All Books:

{
  findAllBooks {
    id
    isbn
    title
    pageCount
    author {
      id
      firstName
      lastName
    }
  }
}
