package com.unpla.service.command.impl;

import com.unpla.entity.document.WasteItem;
import com.unpla.entity.document.WasteTransaction;
import com.unpla.model.controller.WasteAddToWasteItemResponse;
import com.unpla.model.service.WasteAddToWasteItemRequest;
import com.unpla.repository.WasteItemRepository;
import com.unpla.repository.WasteTransactionRepository;
import com.unpla.service.command.AddWasteToWasteItemCommand;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AddWasteToWasteItemCommandImpl implements AddWasteToWasteItemCommand {

    @Autowired
    private WasteItemRepository wasteItemRepository;

    @Autowired
    private WasteTransactionRepository wasteTransactionRepository;

    @Override
    public Mono<WasteAddToWasteItemResponse> execute(WasteAddToWasteItemRequest request) {

        Mono.fromCallable(() -> convertToWasteItem(request))
                .map(wasteItem -> wasteItemRepository.save(wasteItem));
        return Mono
                .fromCallable(() -> convertToWasteTransaction(request))
                .flatMap(wasteTransaction -> wasteTransactionRepository.save(wasteTransaction))
                .map(this::convertToWasteAddResponse);
    }

    private WasteTransaction convertToWasteTransaction(WasteAddToWasteItemRequest req){
        WasteTransaction wasteTransaction = new WasteTransaction();
        BeanUtils.copyProperties(req, wasteTransaction);
        return wasteTransaction;
    }

    private WasteItem convertToWasteItem(WasteAddToWasteItemRequest req){
        WasteItem wasteItem = new WasteItem();
        BeanUtils.copyProperties(req, wasteItem);
        return wasteItem;
    }

    private WasteAddToWasteItemResponse convertToWasteAddResponse(WasteTransaction wasteTransaction){
        WasteAddToWasteItemResponse wasteAddResponse = WasteAddToWasteItemResponse.builder()
                .isSuccess(Boolean.TRUE)
                .build();
        return wasteAddResponse;
    }
}
