package com.estudos.app.controller;

import com.estudos.app.dto.ResponseDto;
import com.estudos.app.dto.UserAuthenticationDto;
import com.estudos.app.dto.UserDto;
import com.estudos.app.service.TokenService;
import com.estudos.app.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth")
@RequiredArgsConstructor
public class UserAuthenticationController {

    private final TokenService tokenService;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody @Valid UserAuthenticationDto userAuthDto){
        UsernamePasswordAuthenticationToken userPass = new UsernamePasswordAuthenticationToken(userAuthDto.email(), userAuthDto.password());
        Authentication authentication = authenticationManager.authenticate(userPass);

        String email = authentication.getName();
        String token = tokenService.generateToken(email, authentication);

        ResponseDto responseDto = new ResponseDto(email, token);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register (@RequestBody @Valid UserDto userDto){
        userService.createUser(userDto);
        return ResponseEntity.ok().build();
    }
}
