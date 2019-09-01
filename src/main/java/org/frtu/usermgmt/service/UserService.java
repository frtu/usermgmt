package org.frtu.usermgmt.service;

import org.frtu.usermgmt.model.User;

import java.util.List;

public interface UserService {
    User findUserById(Long id);

    User findUserByUsername(String username);

    List<User> getList();

    User save(User user);
}
