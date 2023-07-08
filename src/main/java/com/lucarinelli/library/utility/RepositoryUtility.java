package com.lucarinelli.library.utility;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.core.query.Criteria;

@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class RepositoryUtility {

    private static final Integer DEFAULT_PAGE = 0;
    private static final Integer DEFAULT_PAGE_SIZE = 10;

    public static Pageable PageableBuilder(Integer page, Integer pageSize) {
        log.info("PageableBuilder - IN: page({}), pageSize({})", page, pageSize);

        if (page == null) {
            page = DEFAULT_PAGE;
        }
        if (pageSize == null) {
            pageSize = DEFAULT_PAGE_SIZE;
        }
        Pageable pageable = PageRequest.of(page, pageSize);

        log.info("PageableBuilder - OUT: {}", pageable.toString());
        return pageable;
    }

    public static void criteriaBuilder(Criteria criteria, String jsonKey, String value) {
        if (value != null && StringUtils.isNotEmpty(value.trim())) {
            criteria.and(jsonKey).is(value);
        }
    }

    public static void criteriaBuilder(Criteria criteria, String jsonKey, Object value) {
        if (value != null) {
            criteria.and(jsonKey).is(value);
        }
    }

}
