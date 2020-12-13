package com.unpla.service.command.impl;

import com.unpla.model.controller.RecyclerAddResponse;
import com.unpla.model.service.RecyclerAddRequest;
import com.unpla.repository.RecyclerRepository;
import com.unpla.service.command.AddRecyclerToRecyclerCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AddRecyclerToRecyclerCommandImpl implements AddRecyclerToRecyclerCommand {

    @Autowired
    RecyclerRepository recyclerRepository;

    @Override
    public Mono<RecyclerAddResponse> execute(RecyclerAddRequest request) {
//        return Mono.fromCallable(() -> convertToUser(request))
//                .flatMap(user -> recyclerRepository.save(user))
//                .map(this::convertToUserResponse);
    }
}
