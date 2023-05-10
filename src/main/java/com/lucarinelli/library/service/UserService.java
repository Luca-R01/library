package com.lucarinelli.library.service;

import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.model.user.UserDtoSearch;
import com.lucarinelli.library.model.user.UserEntity;

import java.util.List;

public interface UserService {

    UserEntity createUser(UserEntity newUserEntity) throws ConflictException;

    UserEntity findUserById(String id) throws NotFoundException;

    List<UserEntity> findUsersByFilters(UserDtoSearch request);

    UserEntity updateUser(String id, UserEntity newUserEntity) throws NotFoundException;

    void deleteUser(String id) throws NotFoundException;

}
