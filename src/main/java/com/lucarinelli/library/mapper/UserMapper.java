package com.lucarinelli.library.mapper;

import com.lucarinelli.library.model.RentalModel;
import com.lucarinelli.library.model.dto.user.UserDtoRequest;
import com.lucarinelli.library.model.dto.user.UserDtoResponse;
import com.lucarinelli.library.model.entity.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserMapper {

    public User toUser(UserDtoRequest dtoRequest) {

        log.info("toUser - IN: {}", dtoRequest.toString());

        User user = User.builder()
                .fiscalCode(dtoRequest.getFiscalCode())
                .name(dtoRequest.getName())
                .surname(dtoRequest.getSurname())
                .rentalsList(new ArrayList<RentalModel>())
                .build();

        log.info("toUser - OUT: {}", user.toString());
        return user;
    }

    public UserDtoResponse toDto(User user) {

        log.info("toDto - IN: {}", user.toString());

        UserDtoResponse dtoResponse = UserDtoResponse.builder()
                .id(user.getId())
                .fiscalCode(user.getFiscalCode())
                .name(user.getName())
                .surname(user.getSurname())
                .rentals(user.getRentalsList())
                .build();

        log.info("toDto - OUT: {}", dtoResponse.toString());
        return dtoResponse;
    }

    public List<UserDtoResponse> toDtoList(List<User> usersList) {

        log.info("toDtoList - IN: {}", usersList.toString());

        List<UserDtoResponse> dtoResponseList = usersList.stream()
                .map(user -> toDto(user))
                .collect(Collectors.toList());

        log.info("toDtoList - OUT: {}", dtoResponseList.toString());
        return dtoResponseList;
    }

}
