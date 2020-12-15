package com.unpla.service.command.impl;

import com.unpla.model.controller.RecyclerGetListBySubWasteResponse;
import com.unpla.model.service.RecyclerGetListBySubWasteRequest;
import com.unpla.repository.RecyclerRepository;
import com.unpla.service.command.GetRecyclerListBySubWasteCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetRecyclerListBySubWasteCommandImpl implements GetRecyclerListBySubWasteCommand {

    @Autowired
    private RecyclerRepository recyclerRepository;

    @Override
    public Mono<RecyclerGetListBySubWasteResponse> execute(RecyclerGetListBySubWasteRequest request) {
        return null;
    }
}
