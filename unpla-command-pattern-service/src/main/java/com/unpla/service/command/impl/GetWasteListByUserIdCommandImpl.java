package com.unpla.service.command.impl;

import com.unpla.entity.document.WasteItem;
import com.unpla.entity.document.WasteTransaction;
import com.unpla.model.controller.WasteGetListResponse;
import com.unpla.model.controller.WasteGetToWasteItemResponse;
import com.unpla.model.service.WasteGetListByUserIdRequest;
import com.unpla.repository.UserRepository;
import com.unpla.repository.WasteItemRepository;
import com.unpla.repository.WasteTransactionRepository;
import com.unpla.service.command.GetWasteListByUserIdCommand;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GetWasteListByUserIdCommandImpl implements GetWasteListByUserIdCommand {

    @Autowired
    private WasteItemRepository wasteItemRepository;

    @Autowired
    private WasteTransactionRepository wasteTransactionRepository;

    @Override
    public Mono<WasteGetListResponse> execute(WasteGetListByUserIdRequest request) {

        return wasteItemRepository.findWasteItemsByUserId(request.getUserId())
                .flatMap( wasteIt -> wasteTransactionRepository.findById(wasteIt.getId())
                                .map((wasteTransaction) -> {
                                    return toWasteItemTransactionResponse(wasteIt,wasteTransaction);
                                })).collectList().map(this::toWebResponse);

//        return wasteItemRepository.findWasteItemsByUserId(request.getUserId())
//                .map(this::toGetWasteItemWebResponse)
//                .collectList()
//                .map(this::toWebResponse);
    }

    private WasteGetListResponse toWebResponse(List<WasteGetToWasteItemResponse> wasteList) {
        return WasteGetListResponse.builder()
                .listWasteItem(wasteList)
                .total(wasteList.size())
                .build();
    }

    private WasteGetToWasteItemResponse toGetWasteItemWebResponse(WasteItem wasteItem) { // TODO: masih berdasar ID dari wasteItem
        WasteGetToWasteItemResponse response = new WasteGetToWasteItemResponse();

        Mono<WasteTransaction> wasteTransaction = wasteTransactionRepository.findById(wasteItem.getUserId());
        System.out.println(wasteTransaction);

        BeanUtils.copyProperties(wasteItem, response);
        BeanUtils.copyProperties(wasteTransaction, response);

        return response;

//        return WasteGetToWasteItemResponse.builder()
//                .photoListPath(wasteItem.getPhotoListPath())
//                .magnitude(wasteItem.getMagnitude())
//                .subWasteCategory(wasteItem.getSubWasteCategory())
//                .mainWasteCategory(wasteItem.getMainWasteCategory())
//                .weightValue(wasteItem.getWeightValue())
//                .build();
    }

    private WasteGetToWasteItemResponse toWasteItemTransactionResponse(WasteItem wasteItem, WasteTransaction wasteTransaction){
        WasteGetToWasteItemResponse response = new WasteGetToWasteItemResponse();
        BeanUtils.copyProperties(wasteTransaction, response);
        BeanUtils.copyProperties(wasteItem, response);
        return response;
    }
}
