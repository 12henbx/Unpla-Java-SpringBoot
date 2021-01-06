package com.unpla.service.command.impl;

import com.unpla.entity.document.WasteTransaction;
import com.unpla.model.controller.WasteAddToTransactionResponse;
import com.unpla.model.controller.WasteGetToTransactionResponse;
import com.unpla.model.service.WasteGetToTransactionRequest;
import com.unpla.repository.NotificationRepository;
import com.unpla.repository.WasteTransactionRepository;
import com.unpla.service.command.GetWasteTransactionByIdCommand;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetWasteTransactionByIdCommandImpl implements GetWasteTransactionByIdCommand { // TODO : Cek lagi responsedan implementasinya

    @Autowired
    private WasteTransactionRepository wasteTransactionRepository;

    @Autowired
    private NotificationRepository notificationRepository;


    @Override
    public Mono<WasteGetToTransactionResponse> execute(WasteGetToTransactionRequest request) {
        return wasteTransactionRepository.findById(request.getWasteTransactionId())
                .map(this::toWebResponse);
    }

    private WasteGetToTransactionResponse toWebResponse(WasteTransaction wasteTransaction){
        WasteGetToTransactionResponse response = new WasteGetToTransactionResponse();
        BeanUtils.copyProperties(wasteTransaction, response);
        return response;
    }
}
