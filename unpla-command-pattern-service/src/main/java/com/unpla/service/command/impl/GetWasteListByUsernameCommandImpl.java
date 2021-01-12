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
                .flatMapMany(userId -> wasteItemRepository.findWasteItemsByUserId(userId.getId()))
                .map(this::toGetWasteItemWebResponse)
                .collectList()
                .map(this::toWebResponse);
    }

    private WasteGetListResponse toWebResponse(List<WasteGetToWasteItemResponse> wasteList) {
        return WasteGetListResponse.builder()
                .listWasteItem(wasteList)
                .total(wasteList.size())
                .build();
    }

    private WasteGetToWasteItemResponse toGetWasteItemWebResponse(WasteItem wasteItem) {
        WasteGetToWasteItemResponse response = new WasteGetToWasteItemResponse();
        BeanUtils.copyProperties(wasteItem, response);
        return response;
    }

}
