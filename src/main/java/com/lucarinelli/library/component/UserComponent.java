package com.lucarinelli.library.component;

import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.model.user.UserDtoRequest;
import com.lucarinelli.library.model.user.UserDtoResponse;
import com.lucarinelli.library.model.user.UserDtoSearch;
import com.lucarinelli.library.model.user.UserPageDtoResponse;

public interface UserComponent {

    UserDtoResponse createUser(UserDtoRequest dtoRequest) throws ConflictException;

    UserDtoResponse findUserById(String id) throws NotFoundException;

    UserPageDtoResponse findUsersByFilters(Integer page, Integer pageSize, UserDtoSearch request);

    void updateUser(String id, UserDtoRequest dtoRequest) throws NotFoundException;

    void deleteUser(String id) throws NotFoundException;

}
