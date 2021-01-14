package com.unpla.service.command.impl;

import com.unpla.entity.document.Recycler;
import com.unpla.model.controller.RecyclerAddResponse;
import com.unpla.model.service.RecyclerAddRequest;
import com.unpla.repository.RecyclerRepository;
import com.unpla.service.cloud.UploadFileGCP;
import com.unpla.service.command.AddRecyclerToRecyclerCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class AddRecyclerToRecyclerCommandImpl implements AddRecyclerToRecyclerCommand {

    @Autowired
    RecyclerRepository recyclerRepository;

    String randomUUID = "";
    String photoProfilePath = null;

    @Override
    public Mono<RecyclerAddResponse> execute(RecyclerAddRequest request) {
        randomUUID = UUID.randomUUID().toString();
//        for (String eachPath : request.getProfilePhoto()) {
        String outputFilePath = "WasteItemPhotos/Photo_Profile" + "_" + randomUUID + ".jpg";
        convertBase64ToImage(request.getProfilePhoto(), outputFilePath);
        photoProfilePath = "https://storage.googleapis.com/unpla-photo-storage/" + outputFilePath;
//        }

        return Mono.fromCallable(() -> convertToRecycler(request))
                .flatMap(user -> recyclerRepository.save(user))
                .map(this::convertToRecyclerResponse);
    }

    private Recycler convertToRecycler(RecyclerAddRequest req){
        return Recycler.builder()
                .id(randomUUID)
                .name(req.getName())
                .address(req.getAddress())
                .coordinate(req.getCoordinate())
                .description(req.getDescription())
                .mainWasteCategories(req.getMainWasteCategories())
                .profilePhoto(photoProfilePath)
                .subWasteCategories(req.getSubWasteCategories())
                .build();
    }

    private RecyclerAddResponse convertToRecyclerResponse(Recycler recycler){
        return RecyclerAddResponse.builder()
                .id(recycler.getId())
                .name(recycler.getName())
                .address(recycler.getAddress())
                .coordinate(recycler.getCoordinate())
                .description(recycler.getDescription())
                .mainWasteCategories(recycler.getMainWasteCategories())
                .profilePhoto(recycler.getProfilePhoto())
                .subWasteCategories(recycler.getSubWasteCategories())
                .lastModifiedDate(recycler.getLastModifiedDate())
                .build();
    }

    private static void convertBase64ToImage(String encodedString, String outputFileName) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(encodedString);
            UploadFileGCP.uploadObject("unpla-vision-api", "unpla-photo-storage", outputFileName, decodedBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
