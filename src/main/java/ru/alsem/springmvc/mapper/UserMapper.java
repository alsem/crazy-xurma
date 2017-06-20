package ru.alsem.springmvc.mapper;

import org.apache.ibatis.annotations.Param;
import ru.alsem.springmvc.model.User;

import java.util.List;

public interface UserMapper {

    List<User> getUserList();

    long addUser(User user);

    void updateUser(User user);

    void deleteUser(@Param("uid") long uid);
}
