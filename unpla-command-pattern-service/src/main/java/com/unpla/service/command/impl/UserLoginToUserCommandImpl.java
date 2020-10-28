package com.unpla.service.command.impl;

import com.fasterxml.jackson.databind.util.BeanUtil;
import com.sun.org.apache.xpath.internal.operations.Bool;
import com.unpla.config.security.JWTUtil;
import com.unpla.config.security.PBKDF2Encoder;
import com.unpla.entity.document.User;
import com.unpla.model.controller.Response;
import com.unpla.model.controller.UserLoginResponse;
import com.unpla.model.service.LoginUserRequest;
import com.unpla.repository.UserRepository;
import com.unpla.service.command.UserLoginToUserCommand;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
public class UserLoginToUserCommandImpl implements UserLoginToUserCommand {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private PBKDF2Encoder passwordEncoder;

    @Override
    public Mono<UserLoginResponse> execute(LoginUserRequest request) {
        Mono<User> user = userRepository.findByUsername(request.getUsername()).subscribeOn(Schedulers.elastic());
//        return Mono.fromCallable(() -> convertToUser(request))
//                .flatMap(user -> userRepository.save(user))
//                .map(this::convertToUserResponse);

//        return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails)));

        return userRepository.findByUsername(request.getUsername())
                .map(userOne -> {
                    if (passwordEncoder.matches(request.getPassword(), userOne.getPassword())){
                        return new UserLoginResponse(jwtUtil.generateToken(userOne));
                    }
                    throw new RuntimeException("Token tidak ditemukan");
                });
    }

    private UserLoginResponse convertToUserLoginResponse(LoginUserRequest loginAuth){
        UserLoginResponse userLoginResponse = new UserLoginResponse();
        BeanUtils.copyProperties(loginAuth, userLoginResponse);
        return userLoginResponse;
    }

}
