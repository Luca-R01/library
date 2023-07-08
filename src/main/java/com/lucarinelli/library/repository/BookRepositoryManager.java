package com.lucarinelli.library.repository;

import com.lucarinelli.library.model.book.BookDtoSearch;
import com.lucarinelli.library.model.book.BookEntity;
import com.lucarinelli.library.utility.RepositoryUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
@Repository
public class BookRepositoryManager {

    private final MongoTemplate mongoTemplate;

    public Page<BookEntity> findBooksByFilters(Pageable pageable, BookDtoSearch request) {

        Query query = new Query();
        Criteria criteria = new Criteria();

        RepositoryUtility.criteriaBuilder(criteria, "title", request.getTitle());

        RepositoryUtility.criteriaBuilder(criteria, "author", request.getAuthor());

        RepositoryUtility.criteriaBuilder(criteria, "category", request.getCategory());

        RepositoryUtility.criteriaBuilder(criteria, "price", request.getPrice());

        RepositoryUtility.criteriaBuilder(criteria, "quantity", request.getQuantity());

        query.addCriteria(criteria);
        long totElements = mongoTemplate.count(query, BookEntity.class);
        query.with(pageable);
        List<BookEntity> books = mongoTemplate.find(query, BookEntity.class);
        return new PageImpl<>(books, pageable, totElements);
    }


}
