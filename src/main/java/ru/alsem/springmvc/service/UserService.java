package ru.alsem.springmvc.service;

import org.springframework.transaction.annotation.Transactional;
import ru.alsem.springmvc.model.User;

import java.util.List;

public interface UserService {
    //get
    @Transactional(readOnly = true)
    List<User> getUserList();

    //put
    @Transactional
    User addUser(User user);

    @Transactional
    List<User> createUsers(User[] users);

    //post
    @Transactional
    User updateUser(User user);

    @Transactional
    List<User> updateUsers(User[] users);

    //delete
    @Transactional
    void deleteUsers(long[] uids);


}
