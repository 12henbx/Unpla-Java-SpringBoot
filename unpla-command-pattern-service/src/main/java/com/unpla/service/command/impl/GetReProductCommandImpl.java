package com.unpla.service.command.impl;

import com.unpla.entity.document.RecycledProduct;
import com.unpla.model.controller.RecycledProductGetResponse;
import com.unpla.model.service.RecycledProductGetRequest;
import com.unpla.repository.RecycledProductRepository;
import com.unpla.service.command.GetReProductCommand;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GetReProductCommandImpl implements GetReProductCommand { // TODO: terakhir disini, buat add re-product juga

    @Autowired
    private RecycledProductRepository recycledProductRepository;

    @Override
    public Mono<RecycledProductGetResponse> execute(RecycledProductGetRequest request) {
        return recycledProductRepository.findById(request.getRecycledProductId())
                .map(this::toWebResponse);
    }


    private RecycledProductGetResponse toWebResponse(RecycledProduct recycledProduct) {

        RecycledProductGetResponse response = new RecycledProductGetResponse();
        BeanUtils.copyProperties(recycledProduct, response);
        response.setSubmitRatingCount(recycledProduct.getReviews().size());

        return response;
    }
}
