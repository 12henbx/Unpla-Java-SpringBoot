package com.unpla.repository;

import com.unpla.entity.document.RecycledProduct;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RecycledProductRepository extends ReactiveMongoRepository<RecycledProduct, String> {
}
