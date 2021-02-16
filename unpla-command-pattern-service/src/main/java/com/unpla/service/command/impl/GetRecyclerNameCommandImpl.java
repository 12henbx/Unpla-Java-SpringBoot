package com.unpla.service.command.impl;

import com.unpla.entity.document.Recycler;
import com.unpla.model.controller.RecyclerGetNameResponse;
import com.unpla.model.service.RecyclerGetNameRequest;
import com.unpla.repository.RecyclerRepository;
import com.unpla.service.command.GetRecyclerNameCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GetRecyclerNameCommandImpl implements GetRecyclerNameCommand {

    @Autowired
    private RecyclerRepository recyclerRepository;

    @Override
    public Mono<RecyclerGetNameResponse> execute(RecyclerGetNameRequest request) {
        return recyclerRepository.findById(request.getRecyclerId())
                .map(this::toRecyclerWebResponse);
    }

    private RecyclerGetNameResponse toRecyclerWebResponse(Recycler recycler){
        return RecyclerGetNameResponse.builder()
                .recyclerName(recycler.getName())
                .build();
    }
}
