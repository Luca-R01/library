package com.lucarinelli.library.component;

import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.model.dto.user.UserDtoRequest;
import com.lucarinelli.library.model.dto.user.UserDtoResponse;
import com.lucarinelli.library.model.dto.user.UserDtoSearch;
import com.lucarinelli.library.model.entity.User;

import java.util.List;

public interface UserComponent {

    UserDtoResponse createUser(UserDtoRequest dtoRequest) throws ConflictException;

    UserDtoResponse findUserById(String id) throws NotFoundException;

    List<UserDtoResponse> findUsersByFilters(UserDtoSearch request);

    UserDtoResponse updateUser(String id, UserDtoRequest dtoRequest) throws NotFoundException;

    void deleteUser(String id) throws NotFoundException;

}
