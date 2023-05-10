package com.lucarinelli.library.component;

import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.model.user.UserDtoRequest;
import com.lucarinelli.library.model.user.UserDtoResponse;
import com.lucarinelli.library.model.user.UserDtoSearch;

import java.util.List;

public interface UserComponent {

    UserDtoResponse createUser(UserDtoRequest dtoRequest) throws ConflictException;

    UserDtoResponse findUserById(String id) throws NotFoundException;

    List<UserDtoResponse> findUsersByFilters(UserDtoSearch request);

    void updateUser(String id, UserDtoRequest dtoRequest) throws NotFoundException;

    void deleteUser(String id) throws NotFoundException;

}
