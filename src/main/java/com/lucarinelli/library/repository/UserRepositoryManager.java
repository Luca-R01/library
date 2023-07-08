package com.lucarinelli.library.repository;

import com.lucarinelli.library.model.user.UserDtoSearch;
import com.lucarinelli.library.model.user.UserEntity;
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
public class UserRepositoryManager {

    private final MongoTemplate mongoTemplate;

    public Page<UserEntity> findUsersByFilters(Pageable pageable, UserDtoSearch request) {

        Query query = new Query();
        Criteria criteria = new Criteria();

        RepositoryUtility.criteriaBuilder(criteria, "fiscalCode", request.getFiscalCode());

        RepositoryUtility.criteriaBuilder(criteria, "name", request.getName());

        RepositoryUtility.criteriaBuilder(criteria, "surname", request.getSurname());

        query.addCriteria(criteria);
        long totElements = mongoTemplate.count(query, UserEntity.class);
        query.with(pageable);
        List<UserEntity> users = mongoTemplate.find(query, UserEntity.class);
        return new PageImpl<>(users, pageable, totElements);
    }


}
