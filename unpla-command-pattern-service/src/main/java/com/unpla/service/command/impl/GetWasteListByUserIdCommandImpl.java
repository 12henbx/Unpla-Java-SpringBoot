package com.unpla.service.command.impl;

import com.unpla.entity.document.WasteItem;
import com.unpla.model.controller.WasteGetListResponse;
import com.unpla.model.controller.WasteGetToWasteItemResponse;
import com.unpla.model.service.WasteGetListByUserIdRequest;
import com.unpla.repository.UserRepository;
import com.unpla.repository.WasteItemRepository;
import com.unpla.service.command.GetWasteListByUserIdCommand;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GetWasteListByUserIdCommandImpl implements GetWasteListByUserIdCommand {

    @Autowired
    private WasteItemRepository wasteItemRepository;

    @Override
    public Mono<WasteGetListResponse> execute(WasteGetListByUserIdRequest request) {
        return wasteItemRepository.findWasteItemsByUserId(request.getUserId())
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
