package com.lucarinelli.library.service.impl;

import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.model.user.UserDtoSearch;
import com.lucarinelli.library.model.user.UserEntity;
import com.lucarinelli.library.repository.UserRepository;
import com.lucarinelli.library.repository.UserRepositoryManager;
import com.lucarinelli.library.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRepositoryManager userRepositoryManager;

    @Override
    public UserEntity createUser(UserEntity newUserEntity) throws ConflictException {
        log.info("createUser - IN: {}", newUserEntity.toString());

        // Check if User already exists
        UserDtoSearch request = UserDtoSearch.builder()
                .fiscalCode(newUserEntity.getFiscalCode())
                .build();

        Page<UserEntity> userEntity = findUsersByFilters(0, 1, request);
        if (!userEntity.isEmpty()) {
            log.error("createUser - OUT: ConflictException");
            throw new ConflictException("The User alredy exists!");
        }
        // Insert User
        newUserEntity = userRepository.save(newUserEntity);

        log.info("createUser - OUT: {}", newUserEntity.toString());
        return newUserEntity;
    }

    @Override
    public UserEntity findUserById(String id) throws NotFoundException {
        log.info("findUserById - IN: id({})", id);

        Optional<UserEntity> user = userRepository.findById(id);
        if (user.isEmpty()) {
            log.error("findUserById - OUT: NotFoundException");
            throw new NotFoundException("The user not found");
        }

        log.info("findUserById - OUT: {}", user.get().toString());
        return user.get();
    }

    @Override
    public Page<UserEntity> findUsersByFilters(Integer page, Integer pageSize, UserDtoSearch request) {
        log.info("findByUsersByFilter - IN: {}", request.toString());

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<UserEntity> userEntities = userRepositoryManager.findUsersByFilters(pageable, request);

        log.info("findByUsersByFilter - OUT: {}", userEntities.toString());
        return userEntities;
    }

    @Override
    public UserEntity updateUser(String id, UserEntity newUserEntity) throws NotFoundException {
        log.info("updateUser - IN: id({}), {}", id, newUserEntity.toString());

        UserEntity oldUserEntity = findUserById(id);
        // Update User
        newUserEntity.setId(oldUserEntity.getId());
        userRepository.save(newUserEntity);

        log.info("updateUser - OUT: {}", newUserEntity.toString());
        return newUserEntity;
    }

    @Override
    public void deleteUser(String id) throws NotFoundException {
        log.info("deleteUser - IN: id({})", id);

        UserEntity userEntity = findUserById(id);
        // Delete User
        userRepository.delete(userEntity);

        log.info("deleteUser - OUT: END");
    }

}
