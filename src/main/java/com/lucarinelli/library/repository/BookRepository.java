package com.lucarinelli.library.repository;

import com.lucarinelli.library.model.book.BookEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookRepository extends MongoRepository<BookEntity, String> {

}
