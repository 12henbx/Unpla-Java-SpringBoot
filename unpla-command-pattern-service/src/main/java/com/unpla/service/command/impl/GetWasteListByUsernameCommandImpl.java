package com.unpla.service.command.impl;

import com.mongodb.MongoWriteException;
import com.unpla.entity.document.WasteItem;
import com.unpla.model.controller.WasteGetListByUsernameResponse;
import com.unpla.model.controller.WasteGetToWasteItemResponse;
import com.unpla.model.service.WasteGetListByUsernameRequest;
import com.unpla.repository.WasteItemRepository;
import com.unpla.service.command.GetWasteListByUsernameCommand;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GetWasteListByUsernameCommandImpl implements GetWasteListByUsernameCommand {

    @Autowired
    private WasteItemRepository wasteItemRepository;

    @Override
    public Mono<WasteGetListByUsernameResponse> execute(WasteGetListByUsernameRequest req) {

        return wasteItemRepository.findWasteItemsByUserId(req.getUserId(), PageRequest.of(req.getPage(), req.getSize()))
                .map(this::toGetCustomerWebResponse)
                .collectList()
                .map(this::toWebResponse)
                .flatMap(this::fillTotal);
    }

    private WasteGetListByUsernameResponse toWebResponse(List<WasteGetToWasteItemResponse> wasteList) {
        return WasteGetListByUsernameResponse.builder()
                .ListWasteItem(wasteList)
                .build();
    }

    private WasteGetToWasteItemResponse toGetCustomerWebResponse(WasteItem wasteItem) {
        WasteGetToWasteItemResponse response = new WasteGetToWasteItemResponse();
        BeanUtils.copyProperties(wasteItem, response);
        return response;
    }

    private Mono<WasteGetListByUsernameResponse> fillTotal(WasteGetListByUsernameResponse response) {
        return wasteItemRepository.count()
                .map(aLong -> {
                    response.setTotal(aLong);
                    return response;
                });
    }

}
