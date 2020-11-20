package io.github.adrbloch.FootballDB.service.impl;

import io.github.adrbloch.FootballDB.exception.FieldsNotMatchException;
import io.github.adrbloch.FootballDB.exception.ResourceAlreadyExistsException;
import io.github.adrbloch.FootballDB.model.user.User;
import io.github.adrbloch.FootballDB.repository.UserRepository;
import io.github.adrbloch.FootballDB.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    public static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public User createUser(User newUser) {

        logger.info("Create new user...");
        if (userRepository.findByUsername(newUser.getUsername()).isPresent())
            throw new ResourceAlreadyExistsException("User already exists!");

        if (!newUser.getPassword().equals(newUser.getMatchingPassword()))
            throw new FieldsNotMatchException("Passwords do not match!");

        newUser.setPassword(passwordEncoder.encode(newUser.getPassword()));

        return userRepository.save(newUser);
    }
}
