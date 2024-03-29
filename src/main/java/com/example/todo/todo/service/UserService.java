package com.example.todo.todo.service;

import java.time.Instant;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;
import java.util.stream.StreamSupport;

import javax.persistence.EntityNotFoundException;

import com.example.todo.todo.entity.User;
import com.example.todo.todo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public String login(String name, String password) {

        User currentUser = StreamSupport.stream(userRepository.findAll().spliterator(), false)
            .filter(user -> user.getName().equals(name) && user.getPassword().equals(password)).findFirst().get();
        if(currentUser == null){
            throw new EntityNotFoundException("User not found");
        }

        String sessionId = UUID.randomUUID().toString();
        currentUser.setSessionID(sessionId);
        currentUser.setLastLogin((Instant.now()).toEpochMilli());
        currentUser = userRepository.save(currentUser);

        return currentUser.getSessionID();
    }

    public String register(String name, String password) {
        String sessionId = UUID.randomUUID().toString();
        Long lastLogin = (Instant.now()).toEpochMilli();

        User user = new User(null, name, password, sessionId, lastLogin);

        userRepository.save(user);
        return sessionId;
    }

    public void logout(String sessionId) {
        User currentUser = StreamSupport.stream(userRepository.findAll().spliterator(), false)
            .filter(user -> user.getSessionID().equals(sessionId)).findFirst().get();

        Calendar cl = Calendar.getInstance();
        cl.set(1970, 0, 1);
        currentUser.setLastLogin(cl.getTime().toInstant().getEpochSecond());
        currentUser.setSessionID("");

        userRepository.save(currentUser);
    }

    public User getUserFromUserName(String userName){
        User currentUser = StreamSupport.stream(userRepository.findAll().spliterator(), false)
            .filter(user -> user.getName().equals(userName)).findFirst().get();
        return currentUser;
    }

    public User getUserFromSessionId(String sessionId) {
        User currentUser = StreamSupport.stream(userRepository.findAll().spliterator(), false)
            .filter(user -> user.getSessionID().equals(sessionId)).findFirst().get();
        return currentUser;
    }
}