package com.unpla.service.command.impl;

import com.unpla.entity.document.RecycledProduct;
import com.unpla.model.controller.RecycledProductGetListResponse;
import com.unpla.model.controller.RecycledProductGetResponse;
import com.unpla.model.service.RecycledProductGetListRequest;
import com.unpla.repository.RecycledProductRepository;
import com.unpla.service.command.GetReProductListCommand;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GetReProductListCommandImpl implements GetReProductListCommand {

    @Autowired
    private RecycledProductRepository recycledProductRepository;

    @Override
    public Mono<RecycledProductGetListResponse> execute(RecycledProductGetListRequest request) {
        return recycledProductRepository.findAll()
                .map(this::toGetReProductWebResponse)
                .collectList()
                .map(this::toWebResponse);
    }

    private RecycledProductGetListResponse toWebResponse(List<RecycledProductGetResponse> reProductResponses) {
        return RecycledProductGetListResponse.builder()
                .recycledProductList(reProductResponses)
                .total(reProductResponses.size())
                .build();
    }

    private RecycledProductGetResponse toGetReProductWebResponse(RecycledProduct recycledProduct) {

        RecycledProductGetResponse response = new RecycledProductGetResponse();
        BeanUtils.copyProperties(recycledProduct, response);
        response.setSubmitRatingCount(recycledProduct.getReviews().size());

        return response;
    }
}
