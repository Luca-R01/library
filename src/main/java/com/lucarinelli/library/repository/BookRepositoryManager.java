package com.lucarinelli.library.repository;

import com.lucarinelli.library.model.book.BookDtoSearch;
import com.lucarinelli.library.model.book.BookEntity;
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

        if (request.getTitle() != null && !request.getTitle().isBlank()) {
            criteria = criteria.and("title").is(request.getTitle());
        }

        if (request.getAuthor() != null && !request.getAuthor().isBlank()) {
            criteria = criteria.and("author").is(request.getAuthor());
        }

        if (request.getCategory() != null) {
            criteria = criteria.and("category").is(request.getCategory());
        }

        if (request.getPrice() != null) {
            criteria = criteria.and("price").is(request.getPrice());
        }

        if (request.getQuantity() != null) {
            criteria = criteria.and("quantity").is(request.getQuantity());
        }

        query.addCriteria(criteria);
        long totElements = mongoTemplate.count(query, BookEntity.class);
        query.with(pageable);
        List<BookEntity> books = mongoTemplate.find(query, BookEntity.class);
        return new PageImpl<>(books, pageable, totElements);
    }


}
