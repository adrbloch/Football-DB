package io.github.adrbloch.FootballDB.service.impl;

import io.github.adrbloch.FootballDB.exception.FieldsNotMatchException;
import io.github.adrbloch.FootballDB.exception.ResourceAlreadyExistsException;
import io.github.adrbloch.FootballDB.model.user.User;
import io.github.adrbloch.FootballDB.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userServiceImpl;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private static User user;

    @BeforeEach
    void initializeUser() {
        user = new User(1L, "user", "password", "password");
    }

    @Test
    void createAlreadyExistingUserThrowsException() {

        //given
        given(userRepository.findByUsername(user.getUsername())).willReturn(Optional.of(user));

        //when
        //then
        assertThrows(ResourceAlreadyExistsException.class, () -> userServiceImpl.createUser(user));
    }

    @Test
    void createUserWithUnmatchedPasswordsThrowsException() {

        //given
        user.setMatchingPassword("password2");

        //when
        //then
        assertThrows(FieldsNotMatchException.class, () -> userServiceImpl.createUser(user));
    }

    @Test
    void checkIfPasswordIsEncodedAfterCreateNewUser() {

        //given
        String password = user.getPassword();
        given(userRepository.save(user)).willReturn(user);

        //when
        User encodedUser = userServiceImpl.createUser(user);

        //then
        assertTrue(passwordEncoder.matches(password, encodedUser.getPassword()));
    }
}