package io.github.adrbloch.FootballDB.security;


import io.github.adrbloch.FootballDB.exception.ResourceNotFoundException;
import io.github.adrbloch.FootballDB.model.user.User;
import io.github.adrbloch.FootballDB.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    public static final Logger logger = LoggerFactory.getLogger(UserPrincipalDetailsService.class);
    private final UserRepository userRepository;

    @Autowired
    public UserPrincipalDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        logger.info("Signing in user: {}", username);
        User user = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User does not exist"));

        return new UserPrincipal(user);
    }
}
