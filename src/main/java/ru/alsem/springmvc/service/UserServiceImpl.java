package ru.alsem.springmvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.alsem.springmvc.mapper.UserMapper;
import ru.alsem.springmvc.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    //get
    @Transactional(readOnly = true)
    public List<User> getUserList() {
        return userMapper.getUserList();
    }

    //put
    public User addUser(User user) {
        userMapper.addUser(user);
        return user;
    }

    //post
    @Transactional
    public User updateUser(User user) {
        userMapper.updateUser(user);
        return user;
    }

    //delete
    @Transactional
    public void deleteUsers(long[] uids) {

        for (long uid : uids) {
            userMapper.deleteUser(uid);
        }
    }

    @Transactional
    public List<User> createUsers(User[] users) {
        List<User> data = new ArrayList<User>();
        for (User user : users) {
            User storedUser = addUser(user);
            data.add(storedUser);
        }
        return data;
    }

    @Transactional
    public List<User> updateUsers(User[] users) {
        List<User> data = new ArrayList<User>();
        for (User user : users) {
            User storedUser = updateUser(user);
            data.add(storedUser);
        }
        return data;
    }

}
