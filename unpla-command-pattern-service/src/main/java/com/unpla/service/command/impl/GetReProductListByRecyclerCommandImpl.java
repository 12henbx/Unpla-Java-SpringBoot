package com.unpla.service.command.impl;

import com.unpla.entity.document.RecycledProduct;
import com.unpla.model.controller.RecycledProductGetListResponse;
import com.unpla.model.controller.RecycledProductGetResponse;
import com.unpla.model.service.RProductGetListByRecyclerRequest;
import com.unpla.repository.RecycledProductRepository;
import com.unpla.repository.UserRepository;
import com.unpla.service.command.GetReProductListByRecyclerCommand;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class GetReProductListByRecyclerCommandImpl implements GetReProductListByRecyclerCommand {

    @Autowired
    private RecycledProductRepository recycledProductRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<RecycledProductGetListResponse> execute(RProductGetListByRecyclerRequest request) {

        return userRepository.findById(request.getUserId())
                .flatMapMany(user -> {
                    if(user.isRecyclerActive()){
                        return recycledProductRepository.findRecycledProductsByRecyclerId(user.getRecyclerId());
                    }
                    return Mono.empty();
                })
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
