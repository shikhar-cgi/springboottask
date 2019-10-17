package com.stackroute.userservice.service;

import com.stackroute.userservice.domain.User;
import com.stackroute.userservice.exception.UserAlreadyExist;
import com.stackroute.userservice.exception.UserNotFound;

import java.util.List;

public interface UserService {

    public User saveUser(User user) throws UserAlreadyExist;
    public List<User> getAllUser();
    public void deleteUser(int userId);
    public User upadate(User user);
    public List<User> findUserByTrackName(String name) throws UserNotFound;

}
