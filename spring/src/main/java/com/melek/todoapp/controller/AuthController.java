package com.melek.todoapp.controller;

import com.melek.todoapp.dto.CreateUserRequest;
import com.melek.todoapp.dto.LoginRequest;
import com.melek.todoapp.dto.LoginResponse;
import com.melek.todoapp.dto.UserDto;
import com.melek.todoapp.entity.User;
import com.melek.todoapp.mapper.UserMapper;
import com.melek.todoapp.security.JwtService;
import com.melek.todoapp.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UserService userService;

    public AuthController(
        AuthenticationManager authenticationManager,
        JwtService jwtService,
        UserService userService
    ) {
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {

        Authentication authentication = new UsernamePasswordAuthenticationToken(
            request.email(),
            request.password()
        );

        authenticationManager.authenticate(authentication);

        LoginResponse response = new LoginResponse(
            jwtService.generateToken(authentication.getName())
        );

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<UserDto> register(
        @Valid @RequestBody CreateUserRequest request
    ) {
        User user = userService.create(request);

        UserDto response = UserMapper.toDto(user);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
