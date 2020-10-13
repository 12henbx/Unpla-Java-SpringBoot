package com.unpla.service.command.impl;

import com.unpla.model.controller.UserAddRequest;
import com.unpla.model.service.AddUserToUserResponse;
import com.unpla.repository.UserRepository;
import com.unpla.service.command.AddUserToUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class AddUserToUserCommandImpl implements AddUserToUserCommand {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<AddUserToUserResponse> execute(UserAddRequest request) {
        return null;
    }
}
