package com.estudos.app.service;

import com.estudos.app.entity.User;
import com.estudos.app.exception.user.UserNotFoundException;
import com.estudos.app.logging.ServiceLogging;
import com.estudos.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsService implements org.springframework.security.core.userdetails.UserDetailsService {

    private final ServiceLogging log;
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email).orElseThrow(()->{
            log.warnFindOperations("missing", "loadUserByUsername", "User Email");
            return new UserNotFoundException(email);
        });
        return new UserDetailsImpl(user);


    }
}
