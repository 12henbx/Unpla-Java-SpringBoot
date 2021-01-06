package com.unpla.service.command.impl;

import com.unpla.entity.document.WasteItem;
import com.unpla.model.controller.WasteGetListResponse;
import com.unpla.model.controller.WasteGetToWasteItemResponse;
import com.unpla.model.service.WasteGetListByUsernameRequest;
import com.unpla.repository.UserRepository;
import com.unpla.repository.WasteItemRepository;
import com.unpla.service.command.GetWasteListByUsernameCommand;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GetWasteListByUsernameCommandImpl implements GetWasteListByUsernameCommand {

    @Autowired
    private WasteItemRepository wasteItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<WasteGetListResponse> execute(WasteGetListByUsernameRequest req) {

        return userRepository.findByUsername(req.getUsername())
                .flatMapMany(userId -> wasteItemRepository.findWasteItemsByUserId(userId.getId(), PageRequest.of(req.getPage(), req.getSize())))
                .map(this::toGetCustomerWebResponse)
                .collectList()
                .map(this::toWebResponse)
                .flatMap(this::fillTotal);
    }

    private WasteGetListResponse toWebResponse(List<WasteGetToWasteItemResponse> wasteList) {
        return WasteGetListResponse.builder()
                .ListWasteItem(wasteList)
                .build();
    }

    private WasteGetToWasteItemResponse toGetCustomerWebResponse(WasteItem wasteItem) {
        WasteGetToWasteItemResponse response = new WasteGetToWasteItemResponse();
        BeanUtils.copyProperties(wasteItem, response);
        return response;
    }

    private Mono<WasteGetListResponse> fillTotal(WasteGetListResponse response) {
        return wasteItemRepository.count()
                .map(aLong -> {
                    response.setTotal(aLong);
                    return response;
                });
    }

}
