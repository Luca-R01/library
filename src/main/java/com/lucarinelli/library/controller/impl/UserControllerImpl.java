package com.lucarinelli.library.controller.impl;

import com.lucarinelli.library.controller.UserController;
import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.mapper.UserMapper;
import com.lucarinelli.library.model.user.*;
import com.lucarinelli.library.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserControllerImpl implements UserController {

    private final UserMapper userMapper;
    private final UserService userService;

    @Override
    public ResponseEntity<UserDtoResponse> createUser(@Valid UserDtoRequest dtoRequest) throws ConflictException {
        log.info("createUser - IN: {}", dtoRequest.toString());

        UserEntity user = userMapper.toUser(dtoRequest);
        user = userService.createUser(user);
        UserDtoResponse userDtoResponse = userMapper.toIdDto(user);
        ResponseEntity<UserDtoResponse> response = new ResponseEntity<>(userDtoResponse, HttpStatus.CREATED);

        log.info("createUser - OUT: {}", response.toString());
        return response;
    }

    @Override
    public ResponseEntity<UserDtoResponse> findUserById(String id) throws NotFoundException {
        log.info("findUserById - OUT: id({})", id);

        UserEntity user = userService.findUserById(id);
        UserDtoResponse userDtoResponse = userMapper.toDto(user);
        ResponseEntity<UserDtoResponse> response = new ResponseEntity<>(userDtoResponse, HttpStatus.OK);

        log.info("findUserById - OUT: {}", response.toString());
        return response;
    }

    @Override
    public ResponseEntity<UserPageDtoResponse> findUsersByFilters(Integer page, Integer pageSize, UserDtoSearch request) {
        log.info("findByUsersByFilter - IN: {}", request.toString());

        Page<UserEntity> userPage = userService.findUsersByFilters(page, pageSize, request);
        UserPageDtoResponse userPageDto = userMapper.toPageDto(userPage);
        ResponseEntity<UserPageDtoResponse> response = new ResponseEntity<>(userPageDto, HttpStatus.OK);

        log.info("findByUsersByFilter - OUT: {}", response.toString());
        return response;
    }

    @Override
    public ResponseEntity<String> updateUser(String id, UserDtoRequest dtoRequest) throws NotFoundException {
        log.info("updateUser - IN: id({}), {}", id, dtoRequest.toString());

        UserEntity userEntityRequest = userMapper.toUser(dtoRequest);
        userService.updateUser(id, userEntityRequest);
        ResponseEntity<String> response = new ResponseEntity<>("User updated", HttpStatus.OK);

        log.info("updateUser - OUT: {}", response);
        return response;
    }

    @Override
    public ResponseEntity<String> deleteUser(String id) throws NotFoundException {
        log.info("deleteUser - IN: id({})", id);

        userService.deleteUser(id);
        ResponseEntity<String> response = new ResponseEntity<>("User deleted", HttpStatus.OK);

        log.info("deleteUser - OUT: {}", response.toString());
        return response;
    }

}
