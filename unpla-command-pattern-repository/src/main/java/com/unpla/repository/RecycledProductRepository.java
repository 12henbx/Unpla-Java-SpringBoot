package com.unpla.repository;

import com.unpla.entity.document.RecycledProduct;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface RecycledProductRepository extends ReactiveMongoRepository<RecycledProduct, String> {
    Flux<RecycledProduct> findRecycledProductsByRecyclerId(String recyclerId);
}
