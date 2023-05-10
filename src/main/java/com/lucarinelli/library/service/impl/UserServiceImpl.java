package com.lucarinelli.library.service.impl;

import com.lucarinelli.library.exception.ConflictException;
import com.lucarinelli.library.exception.NotFoundException;
import com.lucarinelli.library.model.dto.user.UserDtoSearch;
import com.lucarinelli.library.model.entity.User;
import com.lucarinelli.library.repository.UserRepository;
import com.lucarinelli.library.repository.manager.UserRepositoryManager;
import com.lucarinelli.library.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserRepositoryManager userRepositoryManager;

    @Override
    public User createUser(User newUser) throws ConflictException {
        log.info("createUser - IN: {}", newUser.toString());

        // Check if User already exists
        UserDtoSearch request = UserDtoSearch.builder()
                .fiscalCode(newUser.getFiscalCode())
                .build();

        List<User> user = findUsersByFilters(request);
        if (!user.isEmpty()) {
            log.error("createUser - OUT: ConflictException");
            throw new ConflictException("The User alredy exists!");
        }
        // Insert User
        newUser = userRepository.save(newUser);

        log.info("createUser - OUT: {}", newUser.toString());
        return newUser;
    }

    @Override
    public User findUserById(String id) throws NotFoundException {
        log.info("findUserById - IN: id({})", id);

        Optional<User> user = userRepository.findById(id);
        if (user.isEmpty()) {
            log.error("findUserById - OUT: NotFoundException");
            throw new NotFoundException("The user not found");
        }

        log.info("findUserById - OUT: {}", user.get().toString());
        return user.get();
    }

    @Override
    public List<User> findUsersByFilters(UserDtoSearch request) {
        log.info("findByUsersByFilter - IN: {}", request.toString());

        List<User> users = userRepositoryManager.findUsersByFilters(request);

        log.info("findByUsersByFilter - OUT: {}", users.toString());
        return users;
    }

    @Override
    public User updateUser(String id, User newUser) throws NotFoundException {
        log.info("updateUser - IN: id({}), {}", id, newUser.toString());

        User oldUser = findUserById(id);
        // Update User
        newUser.setId(oldUser.getId());
        userRepository.save(newUser);

        log.info("updateUser - OUT: {}", newUser.toString());
        return newUser;
    }

    @Override
    public void deleteUser(String id) throws NotFoundException {
        log.info("deleteUser - IN: id({})", id);

        User user = findUserById(id);
        // Delete User
        userRepository.delete(user);

        log.info("deleteUser - OUT: END");
    }

}
