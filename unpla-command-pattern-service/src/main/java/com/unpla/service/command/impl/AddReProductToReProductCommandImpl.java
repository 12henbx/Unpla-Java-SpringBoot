package com.unpla.service.command.impl;

import com.unpla.entity.document.RecycledProduct;
import com.unpla.model.controller.RecycledProductAddResponse;
import com.unpla.model.service.RecycledProductAddRequest;
import com.unpla.repository.RecycledProductRepository;
import com.unpla.service.cloud.UploadFileGCP;
import com.unpla.service.command.AddReProductToReProductCommand;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@Service
public class AddReProductToReProductCommandImpl implements AddReProductToReProductCommand {

    @Autowired
    private RecycledProductRepository recycledProductRepository;

    String randomUUID = "";
    List<String> photosPath = new ArrayList<>();

    @Override
    public Mono<RecycledProductAddResponse> execute(RecycledProductAddRequest request) {
        randomUUID = UUID.randomUUID().toString();
        int i=0;

        for (String eachPath : request.getProductImages()) {
            String outputFilePath = "WasteItemPhotos/Photo_" + randomUUID + "_" + i++ + ".jpg";
            convertBase64ToImage(eachPath, outputFilePath);
            photosPath.add("https://storage.googleapis.com/unpla-photo-storage/" + outputFilePath);
        }

        return Mono.fromCallable(() -> convertToReProduct(request))
                .flatMap(reProduct -> recycledProductRepository.save(reProduct))
                .map(this::convertToRecycledProductResponse);
    }

    private RecycledProduct convertToReProduct(RecycledProductAddRequest req){

//        RecycledProduct recycledProduct = new RecycledProduct();
//        BeanUtils.copyProperties(req, recycledProduct);
//
//        return recycledProduct;

        return RecycledProduct.builder()
                .id(randomUUID)
                .name(req.getName())
                .price(req.getPrice())
                .productImages(photosPath)
                .description(req.getDescription())
                .quantity(req.getQuantity())
                .reviews(req.getReviews())
                .recyclerId(req.getRecyclerId())
                .materialList(req.getMaterialList())
                .orderedWasteList(req.getOrderedWasteList())
                .build();
    }

    private RecycledProductAddResponse convertToRecycledProductResponse(RecycledProduct recycledProduct){
        RecycledProductAddResponse recycledProductAddResponse = new RecycledProductAddResponse();
        BeanUtils.copyProperties(recycledProduct,recycledProductAddResponse);

        return recycledProductAddResponse;
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
