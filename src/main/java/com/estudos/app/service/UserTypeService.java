package com.estudos.app.service;

import com.estudos.app.entity.UserType;
import com.estudos.app.repository.UserTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserTypeService {

    private final UserTypeRepository userTypeRepository;

    public void createUserType(UserType userType){
        userTypeRepository.save(userType);
    }
}
