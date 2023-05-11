package com.lucarinelli.library.mapper;

import com.lucarinelli.library.model.PageStatsDto;
import com.lucarinelli.library.model.rental.RentalModel;
import com.lucarinelli.library.model.user.UserDtoRequest;
import com.lucarinelli.library.model.user.UserDtoResponse;
import com.lucarinelli.library.model.user.UserEntity;
import com.lucarinelli.library.model.user.UserPageDtoResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserMapper {

    public UserEntity toUser(UserDtoRequest dtoRequest) {
        log.info("toUser - IN: {}", dtoRequest.toString());

        UserEntity userEntity = UserEntity.builder()
                .fiscalCode(dtoRequest.getFiscalCode())
                .name(dtoRequest.getName())
                .surname(dtoRequest.getSurname())
                .rentalsList(new ArrayList<RentalModel>())
                .build();

        log.info("toUser - OUT: {}", userEntity.toString());
        return userEntity;
    }

    public UserDtoResponse toDto(UserEntity userEntity) {
        log.info("toDto - IN: {}", userEntity.toString());

        UserDtoResponse dtoResponse = UserDtoResponse.builder()
                .id(userEntity.getId())
                .fiscalCode(userEntity.getFiscalCode())
                .name(userEntity.getName())
                .surname(userEntity.getSurname())
                .rentals(userEntity.getRentalsList())
                .build();

        log.info("toDto - OUT: {}", dtoResponse.toString());
        return dtoResponse;
    }

    public List<UserDtoResponse> toDtoList(List<UserEntity> usersList) {
        log.info("toDtoList - IN: {}", usersList.toString());

        List<UserDtoResponse> dtoResponseList = usersList.stream()
                .map(this::toDto)
                .collect(Collectors.toList());

        log.info("toDtoList - OUT: {}", dtoResponseList.toString());
        return dtoResponseList;
    }

    public UserDtoResponse toIdDto(UserEntity userEntity) {
        log.info("toIdDto - IN: {}", userEntity.toString());

        UserDtoResponse dtoResponse = UserDtoResponse.builder()
                .id(userEntity.getId())
                .build();

        log.info("toIdDto - OUT: {}", dtoResponse.toString());
        return dtoResponse;
    }

    public UserPageDtoResponse toPageDto(Page<UserEntity> usersPaged) {
        log.info("toPageDto - IN: {}", usersPaged.toString());

        PageStatsDto pageStatsDto = PageStatsDto.builder()
                .page(usersPaged.getPageable().getPageNumber())
                .pageSize(usersPaged.getPageable().getPageSize())
                .pageElements(usersPaged.getNumberOfElements())
                .totalElements(usersPaged.getTotalElements())
                .totalPages(usersPaged.getTotalPages())
                .build();

        UserPageDtoResponse responseDto = UserPageDtoResponse.builder()
                .users(toDtoList(usersPaged.stream().toList()))
                .pageStats(pageStatsDto)
                .build();

        log.info("toPageDto - OUT: {}", responseDto.toString());
        return responseDto;
    }

}
