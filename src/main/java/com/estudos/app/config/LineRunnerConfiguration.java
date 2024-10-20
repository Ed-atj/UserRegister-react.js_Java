package com.estudos.app.config;

import com.estudos.app.entity.UserType;
import com.estudos.app.service.UserTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
@RequiredArgsConstructor
public class LineRunnerConfiguration implements CommandLineRunner {

    public final UserTypeService userTypeService;

    @Override
    public void run(String... args) throws Exception{
        Arrays.stream(UserType.Enum.values())
                .forEach(userType -> userTypeService.createUserType(userType.getType()) );
    }
}
