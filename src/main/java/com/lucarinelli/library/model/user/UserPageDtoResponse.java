package com.lucarinelli.library.model.user;

import com.lucarinelli.library.model.PageStatsDto;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UserPageDtoResponse {

    private PageStatsDto pageStats;
    private List<UserDtoResponse> users;

}
