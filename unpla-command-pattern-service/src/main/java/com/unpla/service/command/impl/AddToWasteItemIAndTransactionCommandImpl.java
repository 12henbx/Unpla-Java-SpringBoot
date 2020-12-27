package com.unpla.service.command.impl;

import com.sun.org.apache.xpath.internal.operations.Bool;
import com.unpla.entity.document.Notification;
import com.unpla.entity.document.WasteItem;
import com.unpla.entity.document.WasteTransaction;
import com.unpla.entity.enums.NotificationGroup;
import com.unpla.entity.enums.NotificationType;
import com.unpla.model.controller.WasteAddToWasteItemAndTransactionResponse;
import com.unpla.model.service.WasteAddToWasteItemAndTransactionRequest;
import com.unpla.repository.NotificationRepository;
import com.unpla.repository.WasteItemRepository;
import com.unpla.repository.WasteTransactionRepository;
import com.unpla.service.command.AddToWasteItemIAndTransactionCommand;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AddToWasteItemIAndTransactionCommandImpl implements AddToWasteItemIAndTransactionCommand {  // TODO: masuk datanya malah ke wasteTransaction

    @Autowired
    private WasteItemRepository wasteItemRepository;

    @Autowired
    private WasteTransactionRepository wasteTransactionRepository;

    @Autowired
    private NotificationRepository notificationRepository;


    @Override
    public Mono<WasteAddToWasteItemAndTransactionResponse> execute(WasteAddToWasteItemAndTransactionRequest request) {

        WasteItem wasteItem = convertToWasteItem(request);
        wasteItemRepository.save(wasteItem);

        WasteTransaction wasteTransaction = convertToWasteTransaction(request);
        wasteTransactionRepository.save(wasteTransaction);

        Notification notificationForRecycler = createNotificationRecycler(request);
        notificationRepository.save(notificationForRecycler);

        Notification notificationForUser = createNotificationUser(request);
        notificationRepository.save(notificationForUser);

        return Mono.fromCallable(() -> convertToWasteItemAndTransactionResponse(wasteItem, wasteTransaction));
    }

    private WasteItem convertToWasteItem(WasteAddToWasteItemAndTransactionRequest req){

        return WasteItem.builder()
                .isDelete(Boolean.FALSE)
                .photo(req.getPhoto())
                .mainWasteCategory(req.getMainWasteCategory())
                .subWasteCategory(req.getSubWasteCategory())
                .weightValue(req.getWeightValue())
                .magnitude(req.getMagnitude())
                .userId(req.getUserId())
                .build();
    }

    private WasteTransaction convertToWasteTransaction(WasteAddToWasteItemAndTransactionRequest req){

        return WasteTransaction.builder()
                .pickUpDate(req.getPickUpDate())
                .pickUpPeriod(req.getPickUpPeriod())
                .totalPrice(req.getTotalPrice())
                .status(req.getStatus())
                .desc(req.getDesc())
                .recyclerId(req.getRecyclerId())
                .wasteItemId(req.getWasteItemId())
                .build();
    }

    private Notification createNotificationRecycler(WasteAddToWasteItemAndTransactionRequest req){

        return Notification.builder()
                .idReceiver(req.getRecyclerId())
                .idRequester(req.getUserId())
                .isDelete(Boolean.FALSE)
                .isRead(Boolean.FALSE)
                .nGroupForReceiver(NotificationGroup.penjual_sampah)
                .notificationType(NotificationType.pesanan_baru)
                .itemId(req.getWasteItemId())
                .build();
    }

    private Notification createNotificationUser(WasteAddToWasteItemAndTransactionRequest req){

        return Notification.builder()
                .idReceiver(req.getUserId())
                .idRequester(req.getRecyclerId())
                .isDelete(Boolean.FALSE)
                .isRead(Boolean.FALSE)
                .nGroupForReceiver(NotificationGroup.pembeli_sampah)
                .notificationType(NotificationType.pesanan_baru)
                .itemId(req.getWasteItemId())
                .build();
    }


    private WasteAddToWasteItemAndTransactionResponse convertToWasteItemAndTransactionResponse(
            WasteItem wasteItem, WasteTransaction wasteTransaction){

        return WasteAddToWasteItemAndTransactionResponse.builder()
                .mainWasteCategory(wasteItem.getMainWasteCategory())
                .subWasteCategory(wasteItem.getSubWasteCategory())
                .weightValue(wasteItem.getWeightValue())
                .magnitude(wasteItem.getMagnitude())
                .userId(wasteItem.getUserId())
                .pickUpDate(wasteTransaction.getPickUpDate())
                .pickUpPeriod(wasteTransaction.getPickUpPeriod())
                .totalPrice(wasteTransaction.getTotalPrice())
                .status(wasteTransaction.getStatus())
                .desc(wasteTransaction.getDesc())
                .recyclerId(wasteTransaction.getRecyclerId())
                .isSuccess(Boolean.TRUE)
                .build();
    }
}
