package com.unpla.service.command.impl;

//import com.unpla.config.security.JWTUtil;
import com.unpla.entity.document.User;
import com.unpla.model.controller.UserAddResponse;
import com.unpla.model.service.AddUserToUserRequest;
import com.unpla.repository.UserRepository;
import com.unpla.service.command.AddUserToUserCommand;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class AddUserToUserCommandImpl implements AddUserToUserCommand {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<UserAddResponse> execute(AddUserToUserRequest request) {
        return Mono.fromCallable(() -> convertToUser(request))
                .flatMap(user -> userRepository.save(user))
                .map(this::convertToUserResponse);
    }

    private User convertToUser(AddUserToUserRequest req){
        User user = User.builder()
                .id(UUID.randomUUID().toString())
                .fullName(req.getNama())
                .username(req.getUsername())
                .password(req.getPassword())
                .email(req.getEmail())
                .build();

        return user;
    }

    private UserAddResponse convertToUserResponse(User user){
        UserAddResponse userAddResponse = new UserAddResponse();
        BeanUtils.copyProperties(user,userAddResponse);
        return userAddResponse;
    }
}
