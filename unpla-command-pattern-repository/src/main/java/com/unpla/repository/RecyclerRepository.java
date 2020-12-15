package com.unpla.repository;

import com.unpla.entity.document.Recycler;
import com.unpla.entity.enums.SubWasteCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface RecyclerRepository extends ReactiveMongoRepository<Recycler, String> {
    Flux<Recycler> findRecyclersBySubWasteCategoriesContaining(SubWasteCategory subWasteCategory, Pageable pageable);
}
