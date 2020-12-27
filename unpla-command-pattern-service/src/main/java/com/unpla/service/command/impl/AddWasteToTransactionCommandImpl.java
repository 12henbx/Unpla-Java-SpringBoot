//package com.unpla.service.command.impl;
//
//import com.unpla.entity.document.WasteTransaction;
//import com.unpla.model.controller.WasteAddToTransactionResponse;
//import com.unpla.model.service.WasteAddToTransactionRequest;
//import com.unpla.model.service.WasteAddToWasteItemRequest;
//import com.unpla.repository.NotificationRepository;
//import com.unpla.repository.WasteTransactionRepository;
//import com.unpla.service.command.AddWasteToTransactionCommand;
//import org.springframework.beans.BeanUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import reactor.core.publisher.Mono;
//
//@Service
//public class AddWasteToTransactionCommandImpl implements AddWasteToTransactionCommand {
//
//    @Autowired
//    private WasteTransactionRepository wasteTransactionRepository;
//
//    @Autowired
//    private NotificationRepository notificationRepository;
//
//    @Override
//    public Mono<WasteAddToTransactionResponse> execute(WasteAddToTransactionRequest request) {
//        return Mono.fromCallable(() -> convertToWasteTransaction(request))
//                .flatMap(wasteItem -> wasteItemRepository.save(wasteItem))
//                .map(this::convertToWasteAddResponse);
//    }
//
//    private WasteTransaction convertToWasteTransaction(WasteAddToTransactionRequest req){
//        WasteTransaction wasteTransaction = new WasteTransaction();
//        BeanUtils.copyProperties(req, wasteTransaction);
//        return wasteTransaction;
//    }
//
//
//}
