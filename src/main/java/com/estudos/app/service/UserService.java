package com.estudos.app.service;

import com.estudos.app.dto.UserDto;
import com.estudos.app.entity.User;
import com.estudos.app.exception.user.UserAlreadyExistsException;
import com.estudos.app.exception.user.UserNotFoundException;
import com.estudos.app.logging.ServiceLogging;
import com.estudos.app.mapper.UserMapper;
import com.estudos.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final ServiceLogging log;

//  Find all
    public List<UserDto> findAll (){
        log.infoOperation("start", "findAll", "User");
        List<User> listUsers = userRepository.findAll();
        if(listUsers.isEmpty()){
            log.warnFindOperations("missing","findAll", "listUsers");
            throw new UserNotFoundException(listUsers.size());

        }
        log.infoOperation("success", "findALl", "User");
        return userMapper.listUserToDto(listUsers);
    }

//  Create
    public UserDto createUser(UserDto userDto) {
        log.infoOperation("start", "createUser", "User");
        userRepository.findByEmail(userDto.email())
                .ifPresent(user -> {
                    log.warnFindOperations("exists", "createUser", "User email");
                    throw new UserAlreadyExistsException(userDto.email());
                });
        String encryptedPassword = new BCryptPasswordEncoder().encode(userDto.password());
        User newUser = userMapper.toUser(userDto);
        newUser.setPassword(encryptedPassword);
        userRepository.save(newUser);
        log.infoOperation("success", "createUser", "User");
        return userMapper.toDto(newUser);
    }

//  Update
    public UserDto updateUser(Long id, UserDto userDto){
        log.infoOperation("start", "updateUser", "User");
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.warnFindOperations("missing", "updateUser", "User Id");
                    return new UserNotFoundException(id);
                });
        User updatedUser = userMapper.toUser(userDto);
        updatedUser.setId(existingUser.getId());

        userRepository.save(updatedUser);
        return userMapper.toDto(updatedUser);
    }

//  Delete
    public void deleteUser (Long id){
        logger.info("Deleting user with Id: {}", id);
        User existingUser = userRepository.findById(id).orElseThrow(() -> {
            log.warnFindOperations("missing", "deleteUser", "User Id");
            return new UserNotFoundException(id);
        });
        userRepository.delete(existingUser);
    }

//  Get email
    public UserDto findByEmail(String email){

        User user = userRepository.findByEmail(email).orElseThrow(() -> {
            log.warnFindOperations("missing", "findByEmail", "User email");
            return new UserNotFoundException(email);
        });
        return userMapper.toDto(user);
    }

}
