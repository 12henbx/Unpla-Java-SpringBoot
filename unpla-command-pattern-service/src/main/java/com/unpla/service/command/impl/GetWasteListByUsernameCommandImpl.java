package com.unpla.service.command.impl;

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
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetWasteListByUsernameCommandImpl implements GetWasteListByUsernameCommand {

    @Autowired
    private WasteItemRepository wasteItemRepository;

    @Override
    public Mono<WasteGetListByUsernameResponse> execute(WasteGetListByUsernameRequest req) {
        System.out.println(req.toString() + "    bloo    ");
//        return Mono.fromCallable(() -> wasteItemRepository.findWasteItemsByUserId(req.getUserId(), PageRequest.of(req.getPage(), req.getSize())))
//                .map(this::toWebResponse)
//                .flatMap(this::fillTotal);
        return Mono.fromCallable(() -> wasteItemRepository.findAll())
                .map(this::toWebResponse)
                .flatMap(this::fillTotal);
    }

    private WasteGetListByUsernameResponse toWebResponse(Flux<WasteItem> wasteList) {
        List<WasteGetToWasteItemResponse> response = new ArrayList<>();
        BeanUtils.copyProperties(wasteList, response);
        return WasteGetListByUsernameResponse.builder()
                .ListWasteItem(response)
                .build();
    }

    private Mono<WasteGetListByUsernameResponse> fillTotal(WasteGetListByUsernameResponse response) {
        return wasteItemRepository.count()
                .map(aLong -> {
                    response.setTotal(aLong);
                    return response;
                });
    }

}
