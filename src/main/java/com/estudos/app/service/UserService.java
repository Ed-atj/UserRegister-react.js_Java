package com.estudos.app.service;

import com.estudos.app.dto.UserDto;
import com.estudos.app.entity.User;
import com.estudos.app.exception.UserAlreadyExistsException;
import com.estudos.app.exception.UserNotFoundException;
import com.estudos.app.exception.UsersListNotFoundException;
import com.estudos.app.mapper.UserMapper;
import com.estudos.app.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final UserRepository userRepository;
    private final UserMapper userMapper;

//  Find all
    public List<UserDto> findAll (){
        logger.info("Collecting users...");
        List<User> listUsers = userRepository.findAll();
        if(listUsers.isEmpty()){
            logger.warn("There's no users on list.");
            throw new UsersListNotFoundException();
        }
        return userMapper.listUserToDto(listUsers);
    }

//  Create
    public UserDto createUser(UserDto userDto) {
        logger.info("Creating user...");
        userRepository.findByEmail(userDto.email())
                .ifPresent(user -> {
                    logger.warn("User already exists.\nEmail: {}\nNome: {}", userDto.email(), userDto.nome());
                    throw new UserAlreadyExistsException();
                });

        User newUser = userMapper.toUser(userDto);
        userRepository.save(newUser);
        return userMapper.toDto(newUser);
    }

//  Update
    public UserDto updateUser(Long id, UserDto userDto){
        logger.info("Updating user with Id: {}", id);
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> {
                    logger.warn("Can't find user by Id: {}", id);
                    return new UserNotFoundException();
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
            logger.warn("There's no user with Id: {}", id);
            return new UserNotFoundException();
        });
        userRepository.delete(existingUser);
        logger.info("User successful deleted.");
    }

}
