package com.unpla.service.command.impl;

import com.unpla.entity.document.User;
import com.unpla.model.controller.UserGetRecyclerIdResponse;
import com.unpla.model.service.UserGetRecyclerIdRequest;
import com.unpla.repository.UserRepository;
import com.unpla.service.command.GetRecyclerIdByUserIdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetRecyclerIdByUserIdCommandImpl implements GetRecyclerIdByUserIdCommand {

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<UserGetRecyclerIdResponse> execute(UserGetRecyclerIdRequest request) {
        return userRepository.findById(request.getUserId())
                .map(this::toUserWebResponse);
    }

    private UserGetRecyclerIdResponse toUserWebResponse(User user){
        return UserGetRecyclerIdResponse.builder()
                .recyclerId(user.getRecyclerId())
                .build();
    }
}
