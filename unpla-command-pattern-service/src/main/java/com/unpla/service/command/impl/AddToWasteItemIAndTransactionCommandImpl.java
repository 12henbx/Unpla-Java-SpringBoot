package com.unpla.service.command.impl;

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
import com.unpla.service.cloud.UploadFileGCP;
import com.unpla.service.command.AddToWasteItemIAndTransactionCommand;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

@Service
public class AddToWasteItemIAndTransactionCommandImpl implements AddToWasteItemIAndTransactionCommand {  // TODO: masuk datanya malah ke wasteTransaction

    @Autowired
    private WasteItemRepository wasteItemRepository;

    @Autowired
    private WasteTransactionRepository wasteTransactionRepository;

    @Autowired
    private NotificationRepository notificationRepository;

    String randomUUID = "";
    List<String> photosPath = new ArrayList<>();


    @Override
    @Transactional
    public Mono<WasteAddToWasteItemAndTransactionResponse> execute(WasteAddToWasteItemAndTransactionRequest request) {
        randomUUID = UUID.randomUUID().toString();
        int i=0;

        for (String eachPath : request.getPhoto()) {
            String outputFilePath = "WasteItemPhotos/Photo_" + randomUUID + "_" + i++ + ".jpg";
            convertBase64ToImage(eachPath, outputFilePath);
            photosPath.add("https://storage.googleapis.com/unpla-photo-storage/" + outputFilePath);
        }

        WasteItem wasteItem = convertToWasteItem(request);
        wasteItemRepository.insert(wasteItem).subscribe();

        WasteTransaction wasteTransaction = convertToWasteTransaction(request);
        wasteTransactionRepository.insert(wasteTransaction).subscribe();

        Notification notificationForRecycler = createNotificationRecycler(request);
        notificationRepository.insert(notificationForRecycler).subscribe();

        Notification notificationForUser = createNotificationUser(request);
        notificationRepository.insert(notificationForUser).subscribe();

        return Mono.fromCallable(() -> convertToWasteItemAndTransactionResponse(wasteItem, wasteTransaction));
    }

    private WasteItem convertToWasteItem(WasteAddToWasteItemAndTransactionRequest req){

        return WasteItem.builder()
                .id(randomUUID)
                .photo(photosPath)
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
                .id(randomUUID)
                .pickUpDate(req.getPickUpDate())
                .pickUpPeriod(req.getPickUpPeriod())
                .totalPrice(req.getTotalPrice())
                .status(req.getStatus())
                .desc(req.getDesc())
                .recyclerId(req.getRecyclerId())
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
                .itemId(randomUUID)
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
                .itemId(randomUUID)
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

    private static void convertBase64ToImage(String encodedString, String outputFileName) {
        try {

//            FileOutputStream output = new FileOutputStream(outputFileName);
            byte[] decodedBytes = Base64.getDecoder().decode(encodedString);

            // TODO : apakah file ditaruh di project dulu?
            UploadFileGCP.uploadObject("unpla-vision-api", "unpla-photo-storage", outputFileName, decodedBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
