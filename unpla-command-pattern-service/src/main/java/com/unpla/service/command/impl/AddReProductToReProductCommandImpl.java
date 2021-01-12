package com.unpla.service.command.impl;

import com.unpla.entity.document.RecycledProduct;
import com.unpla.model.controller.RecycledProductAddResponse;
import com.unpla.model.service.RecycledProductAddRequest;
import com.unpla.repository.RecycledProductRepository;
import com.unpla.service.command.AddReProductToReProductCommand;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class AddReProductToReProductCommandImpl implements AddReProductToReProductCommand {

    @Autowired
    private RecycledProductRepository recycledProductRepository;

    @Override
    public Mono<RecycledProductAddResponse> execute(RecycledProductAddRequest request) {
        return Mono.fromCallable(() -> convertToReProduct(request))
                .flatMap(reProduct -> recycledProductRepository.save(reProduct))
                .map(this::convertToRecycledProductResponse);
    }

    private RecycledProduct convertToReProduct(RecycledProductAddRequest req){

        RecycledProduct recycledProduct = new RecycledProduct();
        BeanUtils.copyProperties(req, recycledProduct);

        return recycledProduct;
    }

    private RecycledProductAddResponse convertToRecycledProductResponse(RecycledProduct recycledProduct){
        return RecycledProductAddResponse.builder()
                .id(recycledProduct.getId())
                .build();
    }
}
