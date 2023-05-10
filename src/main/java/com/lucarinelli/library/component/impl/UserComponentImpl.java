package com.lucarinelli.library.component.impl;

import com.lucarinelli.library.component.UserComponent;
import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.mapper.UserMapper;
import com.lucarinelli.library.model.user.UserDtoRequest;
import com.lucarinelli.library.model.user.UserDtoResponse;
import com.lucarinelli.library.model.user.UserDtoSearch;
import com.lucarinelli.library.model.user.UserEntity;
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

        UserEntity userEntityRequest = userMapper.toUser(dtoRequest);
        UserEntity userEntity = userService.createUser(userEntityRequest);
        UserDtoResponse response = userMapper.toIdDto(userEntity);

        log.info("createUser - OUT: {}", response);
        return response;
    }

    @Override
    public UserDtoResponse findUserById(String id) throws NotFoundException {
        log.info("findUserById - OUT: id({})", id);

        UserEntity userEntity = userService.findUserById(id);
        UserDtoResponse response = userMapper.toDto(userEntity);

        log.info("findUserById - OUT: {}", response.toString());
        return response;
    }

    @Override
    public List<UserDtoResponse> findUsersByFilters(UserDtoSearch request) {
        log.info("findByUsersByFilter - IN: {}", request.toString());

        List<UserEntity> userEntities = userService.findUsersByFilters(request);
        List<UserDtoResponse> response = userMapper.toDtoList(userEntities);

        log.info("findByUsersByFilter - OUT: {}", response);
        return response;
    }

    @Override
    public void updateUser(String id, UserDtoRequest dtoRequest) throws NotFoundException {
        log.info("updateUser - IN: id({}), {}", id, dtoRequest.toString());

        UserEntity userEntityRequest = userMapper.toUser(dtoRequest);
        userService.updateUser(id, userEntityRequest);

        log.info("updateUser - OUT: End method");
    }

    @Override
    public void deleteUser(String id) throws NotFoundException {
        log.info("deleteUser - IN: id({})", id);

        userService.deleteUser(id);

        log.info("deleteUser - OUT: END");
    }
}
