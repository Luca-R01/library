package com.lucarinelli.library.controller.impl;

import com.lucarinelli.library.component.UserComponent;
import com.lucarinelli.library.controller.UserController;
import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.model.user.UserDtoRequest;
import com.lucarinelli.library.model.user.UserDtoResponse;
import com.lucarinelli.library.model.user.UserDtoSearch;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@RestController
public class UserControllerImpl implements UserController {

    private final UserComponent userComponent;

    @Override
    public ResponseEntity<UserDtoResponse> createUser(@Valid UserDtoRequest dtoRequest) throws ConflictException {
        log.info("createUser - IN: {}", dtoRequest.toString());

        UserDtoResponse userResponse = userComponent.createUser(dtoRequest);
        ResponseEntity<UserDtoResponse> response = new ResponseEntity<>(userResponse, HttpStatus.CREATED);

        log.info("createUser - OUT: {}", response.toString());
        return response;
    }

    @Override
    public ResponseEntity<UserDtoResponse> findUserById(String id) throws NotFoundException {
        log.info("findUserById - OUT: id({})", id);

        UserDtoResponse userResponse = userComponent.findUserById(id);
        ResponseEntity<UserDtoResponse> response = new ResponseEntity<>(userResponse, HttpStatus.OK);

        log.info("findUserById - OUT: {}", response.toString());
        return response;
    }

    @Override
    public ResponseEntity<List<UserDtoResponse>> findUsersByFilters(UserDtoSearch request) {
        log.info("findByUsersByFilter - IN: {}", request.toString());

        List<UserDtoResponse> userResponse = userComponent.findUsersByFilters(request);
        ResponseEntity<List<UserDtoResponse>> response = new ResponseEntity<>(userResponse, HttpStatus.OK);

        log.info("findByUsersByFilter - OUT: {}", response.toString());
        return response;
    }

    @Override
    public ResponseEntity<String> updateUser(String id, UserDtoRequest dtoRequest) throws NotFoundException {
        log.info("updateUser - IN: id({}), {}", id, dtoRequest.toString());

        userComponent.updateUser(id, dtoRequest);
        ResponseEntity<String> response = new ResponseEntity<>("User updated", HttpStatus.OK);

        log.info("updateUser - OUT: {}", response);
        return response;
    }

    @Override
    public ResponseEntity<String> deleteUser(String id) throws NotFoundException {
        log.info("deleteUser - IN: id({})", id);

        userComponent.deleteUser(id);
        ResponseEntity<String> response = new ResponseEntity<>("User deleted", HttpStatus.OK);

        log.info("deleteUser - OUT: {}", response.toString());
        return response;
    }

}
