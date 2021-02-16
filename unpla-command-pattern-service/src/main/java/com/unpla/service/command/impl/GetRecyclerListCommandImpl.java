package com.unpla.service.command.impl;

import com.unpla.entity.document.Recycler;
import com.unpla.model.controller.RecyclerGetListResponse;
import com.unpla.model.controller.RecyclerGetToRecyclerResponse;
import com.unpla.model.service.RecyclerGetListRequest;
import com.unpla.repository.RecyclerRepository;
import com.unpla.service.command.GetRecyclerListCommand;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GetRecyclerListCommandImpl implements GetRecyclerListCommand {

    @Autowired
    private RecyclerRepository recyclerRepository;

    @Override
    public Mono<RecyclerGetListResponse> execute(RecyclerGetListRequest request) {
        return recyclerRepository.findAll()
                .map(this::toGetRecyclerWebResponse)
                .collectList()
                .map(this::toWebResponse);
    }

    private RecyclerGetListResponse toWebResponse(List<RecyclerGetToRecyclerResponse> recyclerResponses) {
        return RecyclerGetListResponse.builder()
                .listRecycler(recyclerResponses)
                .total(recyclerResponses.size())
                .build();
    }

    private RecyclerGetToRecyclerResponse toGetRecyclerWebResponse(Recycler recycler) {

        RecyclerGetToRecyclerResponse response = new RecyclerGetToRecyclerResponse();
        BeanUtils.copyProperties(recycler, response);

        return response;
    }
}
