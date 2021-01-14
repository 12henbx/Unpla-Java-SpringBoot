package com.unpla.service.command.impl;

import com.unpla.entity.document.Recycler;
import com.unpla.model.controller.RecyclerGetListBySubWasteResponse;
import com.unpla.model.controller.RecyclerGetToRecyclerResponse;
import com.unpla.model.service.RecyclerGetListBySubWasteRequest;
import com.unpla.repository.RecyclerRepository;
import com.unpla.service.command.GetRecyclerListBySubWasteCommand;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GetRecyclerListBySubWasteCommandImpl implements GetRecyclerListBySubWasteCommand {

    @Autowired
    private RecyclerRepository recyclerRepository;

    @Override
    public Mono<RecyclerGetListBySubWasteResponse> execute(RecyclerGetListBySubWasteRequest request) {
        return recyclerRepository.findRecyclersBySubWasteCategoriesContains(request.getSubWasteCategory())
                .map(this::toRecyclerWebResponse)
                .collectList()
                .map(this::toWebResponse);
    }

    private RecyclerGetToRecyclerResponse toRecyclerWebResponse(Recycler recycler){
        RecyclerGetToRecyclerResponse response = new RecyclerGetToRecyclerResponse();
        BeanUtils.copyProperties(recycler, response);
        return response;
    }

    private RecyclerGetListBySubWasteResponse toWebResponse(List<RecyclerGetToRecyclerResponse> recyclers){
        return RecyclerGetListBySubWasteResponse.builder()
                .listWasteItem(recyclers)
                .build();
    }
}
