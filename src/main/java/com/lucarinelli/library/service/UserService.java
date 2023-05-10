package com.lucarinelli.library.service;

import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.model.dto.user.UserDtoSearch;
import com.lucarinelli.library.model.entity.User;

import java.util.List;

public interface UserService {

    User createUser(User newUser) throws ConflictException;

    User findUserById(String id) throws NotFoundException;

    List<User> findUsersByFilters(UserDtoSearch request);

    User updateUser(String id, User newUser) throws NotFoundException;

    void deleteUser(String id) throws NotFoundException;

}
