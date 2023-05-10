package com.lucarinelli.library.repository.manager;

import com.lucarinelli.library.model.dto.book.BookDtoSearch;
import com.lucarinelli.library.model.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Builder
@AllArgsConstructor
@Repository
public class BookRepositoryManager {

    @Autowired
    private final MongoTemplate mongoTemplate;

    public List<Book> findBooksByFilters(BookDtoSearch request) {

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

        return mongoTemplate.find(query, Book.class);
    }


}
