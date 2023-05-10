package com.lucarinelli.library.repository.manager;

import com.lucarinelli.library.model.RentalModel;
import com.lucarinelli.library.model.dto.user.UserDtoSearch;
import com.lucarinelli.library.model.entity.User;
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
public class UserRepositoryManager {

    @Autowired
    private final MongoTemplate mongoTemplate;

    public List<User> findUsersByFilters(UserDtoSearch request) {

        Query query = new Query();
        Criteria criteria = new Criteria();

        if (request.getFiscalCode() != null && !request.getFiscalCode().isBlank()) {
            criteria = criteria.and("fiscalCode").is(request.getFiscalCode());
        }

        if (request.getName() != null && !request.getName().isBlank()) {
            criteria = criteria.and("name").is(request.getName());
        }

        if (request.getSurname() != null && !request.getSurname().isBlank()) {
            criteria = criteria.and("surname").is(request.getSurname());
        }

        query.addCriteria(criteria);
        return mongoTemplate.find(query, User.class);
    }


}
