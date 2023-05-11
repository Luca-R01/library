package com.lucarinelli.library.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PageStatsDto {

    private Integer page;
    private Integer pageSize;
    private Integer pageElements;
    private Long totalElements;
    private Integer totalPages;

}
