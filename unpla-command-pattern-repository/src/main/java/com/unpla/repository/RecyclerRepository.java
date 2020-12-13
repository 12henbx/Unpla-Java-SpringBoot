package com.unpla.repository;

import com.unpla.entity.document.Recycler;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface RecyclerRepository extends ReactiveMongoRepository<Recycler, String> {
}
