package com.unpla.service.command.impl;

import com.unpla.entity.document.WasteItem;
import com.unpla.model.controller.WasteGetToWasteItemResponse;
import com.unpla.model.service.WasteGetToWasteItemRequest;
import com.unpla.repository.WasteItemRepository;
import com.unpla.service.command.GetWasteToWasteItemCommand;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetWasteToWasteItemCommandImpl implements GetWasteToWasteItemCommand {

    @Autowired
    private WasteItemRepository wasteItemRepository;

    @Override
    public Mono<WasteGetToWasteItemResponse> execute(WasteGetToWasteItemRequest request) {
        System.out.println(wasteItemRepository.findWasteItemsByUserId(request.getUsername(), PageRequest.of(1, 10)) + "   darlinggg  ");
        return wasteItemRepository.findById(request.getWasteItemId())
                .map(this::toWebResponse);
    }

    private WasteGetToWasteItemResponse toWebResponse(WasteItem waste) {
        WasteGetToWasteItemResponse response = new WasteGetToWasteItemResponse();
        BeanUtils.copyProperties(waste, response);
        return response;
    }
}
