package com.unpla.service.command.impl;

//import com.unpla.config.security.JWTUtil;
import com.unpla.config.security.JWTUtil;
import com.unpla.config.security.PBKDF2Encoder;
import com.unpla.entity.document.User;
import com.unpla.model.controller.UserAddResponse;
import com.unpla.model.service.UserAddRequest;
import com.unpla.repository.UserRepository;
import com.unpla.service.command.AddUserToUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Date;

@Service
public class AddUserToUserCommandImpl implements AddUserToUserCommand {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private PBKDF2Encoder passwordEncoder;

    @Override
    public Mono<UserAddResponse> execute(UserAddRequest request) {
        return Mono.fromCallable(() -> convertToUser(request))
                .flatMap(user -> userRepository.save(user))
                .map(this::convertToUserResponse);
    }

    private User convertToUser(UserAddRequest req){
        User user = User.builder()
                .fullName(req.getNama())
                .username(req.getUsername())
                .password(passwordEncoder.encode(req.getPassword()))
                .email(req.getEmail())
                .lastModifiedDate(new Date().getTime())
                .lastModifiedBy(req.getUsername())
                .createdDate(new Date().getTime())
                .createdBy(req.getUsername())
                .build();
        return user;
    }

    private UserAddResponse convertToUserResponse(User user){
        UserAddResponse userAddResponse = UserAddResponse.builder()
                .username(user.getUsername())
                .isSuccess(Boolean.TRUE)
                .lastModifiedDate(user.getLastModifiedDate())
                .lastModifiedBy(user.getLastModifiedBy())
                .createdDate(user.getCreatedDate())
                .createdBy(user.getCreatedBy())
                .build();

        return userAddResponse;
    }
}
