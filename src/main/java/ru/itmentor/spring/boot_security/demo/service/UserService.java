package ru.itmentor.spring.boot_security.demo.service;

import org.springframework.transaction.annotation.Transactional;
import ru.itmentor.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserService {
    User findById(Long id);

    User findByEmail(String email);

    void addUser(User user);

    List<User> listUsers();

    void update(User user);

    @Transactional
    void deleteUserById(Long id);
}
