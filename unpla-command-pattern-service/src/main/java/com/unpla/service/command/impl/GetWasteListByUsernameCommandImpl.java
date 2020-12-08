package com.unpla.service.command.impl;

import com.unpla.entity.document.WasteItem;
import com.unpla.model.controller.WasteGetListByUsernameResponse;
import com.unpla.model.controller.WasteGetToWasteItemResponse;
import com.unpla.model.service.WasteGetListByUsernameRequest;
import com.unpla.repository.WasteItemRepository;
import com.unpla.service.command.GetWasteListByUsernameCommand;
import com.unpla.support.PageSupport;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
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
        System.out.println(req.toString() + "    bloo    ");
        return wasteItemRepository.findWasteItemsByUserId(req.getUserId(), PageRequest.of(req.getPage(), req.getSize()))
                .collectList()
                .map(this::toWebResponse)
                .flatMap(this::fillTotal);

//        return Mono.fromCallable(() -> wasteItemRepository.findAll())
//                .map(this::toWebResponse)
//                .flatMap(this::fillTotal);

//        return wasteItemRepository.findAll()
//                .collectList()
//                .map(list -> new PageSupport<>(
//                        list
//                                .stream()
//                                .skip(req.getPage() * req.getSize())
//                                .limit(req.getSize())
//                                .collect(Collectors.toList()),
//                        req.getPage(), req.getSize(), list.size()));
    }

    private WasteGetListByUsernameResponse toWebResponse(List<WasteItem> wasteList) {
        System.out.println(wasteList + "    bloo1    ");
        List<WasteGetToWasteItemResponse> response = new ArrayList<>();
        BeanUtils.copyProperties(wasteList, response);
        return WasteGetListByUsernameResponse.builder()
                .ListWasteItem(response)
                .build();
    }

    private Mono<WasteGetListByUsernameResponse> fillTotal(WasteGetListByUsernameResponse response) {
        System.out.println(response + "    bloo2    ");
        return wasteItemRepository.count()
                .map(aLong -> {
                    response.setTotal(aLong);
                    return response;
                });
    }

}
