package com.lucarinelli.library.model.book;

import com.lucarinelli.library.model.PageStatsDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class BookPageDtoResponse {

    private PageStatsDto pageStats;
    private List<BookDtoResponse> books;

}
