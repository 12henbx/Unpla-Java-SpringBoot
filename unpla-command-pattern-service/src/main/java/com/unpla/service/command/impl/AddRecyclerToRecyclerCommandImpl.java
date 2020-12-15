package com.unpla.service.command.impl;

import com.unpla.entity.document.Recycler;
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
        return Mono.fromCallable(() -> convertToRecycler(request))
                .flatMap(user -> recyclerRepository.save(user))
                .map(this::convertToRecyclerResponse);
    }

    private Recycler convertToRecycler(RecyclerAddRequest req){
        return Recycler.builder()
                .name(req.getName())
                .address(req.getAddress())
                .coordinate(req.getCoordinate())
                .description(req.getDescription())
                .mainWasteCategories(req.getMainWasteCategories())
                .headerPhoto(req.getHeaderPhoto())
                .profilePhoto(req.getProfilePhoto())
                .subWasteCategories(req.getSubWasteCategories())
                .build();
    }

    private RecyclerAddResponse convertToRecyclerResponse(Recycler recycler){
        return RecyclerAddResponse.builder()
                .name(recycler.getName())
                .address(recycler.getAddress())
                .coordinate(recycler.getCoordinate())
                .description(recycler.getDescription())
                .mainWasteCategories(recycler.getMainWasteCategories())
                .headerPhoto(recycler.getHeaderPhoto())
                .profilePhoto(recycler.getProfilePhoto())
                .subWasteCategories(recycler.getSubWasteCategories())
                .lastModifiedDate(recycler.getLastModifiedDate())
                .build();
    }
}
