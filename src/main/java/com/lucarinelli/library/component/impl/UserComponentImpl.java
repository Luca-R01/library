package com.lucarinelli.library.component.impl;

import com.lucarinelli.library.component.UserComponent;
import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.mapper.UserMapper;
import com.lucarinelli.library.model.dto.user.UserDtoRequest;
import com.lucarinelli.library.model.dto.user.UserDtoResponse;
import com.lucarinelli.library.model.dto.user.UserDtoSearch;
import com.lucarinelli.library.model.entity.User;
import com.lucarinelli.library.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class UserComponentImpl implements UserComponent {

    private final UserMapper userMapper;

    private final UserService userService;

    @Override
    public UserDtoResponse createUser(UserDtoRequest dtoRequest) throws ConflictException {
        log.info("createUser - IN: {}", dtoRequest.toString());

        User userRequest = userMapper.toUser(dtoRequest);
        User user = userService.createUser(userRequest);
        UserDtoResponse response = userMapper.toDto(user);

        log.info("createUser - OUT: {}", response);
        return response;
    }

    @Override
    public UserDtoResponse findUserById(String id) throws NotFoundException {
        log.info("findUserById - OUT: id({})", id);

        User user = userService.findUserById(id);
        UserDtoResponse response = userMapper.toDto(user);

        log.info("findUserById - OUT: {}", response.toString());
        return response;
    }

    @Override
    public List<UserDtoResponse> findUsersByFilters(UserDtoSearch request) {
        log.info("findByUsersByFilter - IN: {}", request.toString());

        List<User> users = userService.findUsersByFilters(request);
        List<UserDtoResponse> response = userMapper.toDtoList(users);

        log.info("findByUsersByFilter - OUT: {}", response);
        return response;
    }

    @Override
    public UserDtoResponse updateUser(String id, UserDtoRequest dtoRequest) throws NotFoundException {
        log.info("updateUser - IN: id({}), {}", id, dtoRequest.toString());

        User userRequest = userMapper.toUser(dtoRequest);
        User user = userService.updateUser(id, userRequest);
        UserDtoResponse response = userMapper.toDto(user);

        log.info("updateUser - OUT: {}", response);
        return response;
    }

    @Override
    public void deleteUser(String id) throws NotFoundException {
        log.info("deleteUser - IN: id({})", id);

        userService.deleteUser(id);

        log.info("deleteUser - OUT: END");
    }
}
