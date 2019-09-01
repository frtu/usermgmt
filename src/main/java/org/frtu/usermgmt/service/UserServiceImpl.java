package org.frtu.usermgmt.service;

import org.frtu.usermgmt.model.User;
import org.frtu.usermgmt.repository.UserRepository;
import org.frtu.usermgmt.service.exception.UserAlreadyExistsException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.List;

@Service
@Validated
public class UserServiceImpl implements UserService {
    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository repository;

    @Inject
    public UserServiceImpl(final UserRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public User findUserById(Long id) {
        LOGGER.debug("Searching for userId {}", id);
        return repository.findOne(id);
    }

    @Override
    @Transactional
    public User findUserByUsername(String username) {
        LOGGER.debug("Searching for username {}", username);
        final List<User> userList = repository.findByUsername(username);
        if (userList.isEmpty()) {
            throw new UsernameNotFoundException(
                    String.format("There already exists a user with id=%s", username));
        }
        return userList.get(0);
    }

    @Override
    @Transactional
    public User save(@NotNull @Valid final User user) {
        LOGGER.debug("Creating {}", user);
        final String username = user.getUsername();
        final List<User> userList = repository.findByUsername(username);
        if (!userList.isEmpty()) {
            throw new UserAlreadyExistsException(
                    String.format("There already exists a user with id=%s", username));
        }
        return repository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getList() {
        LOGGER.debug("Retrieving the list of all users");
        return repository.findAll();
    }
}
