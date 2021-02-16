package com.unpla.repository;

import com.unpla.entity.document.Recycler;
import com.unpla.entity.enums.SubWasteCategory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;


public interface RecyclerRepository extends ReactiveMongoRepository<Recycler, String> {
//    Flux<Recycler> findRecyclersBySubWasteCategoriesContains(SubWasteCategory subWasteCategory);

    @Query(value = "{ 'subWastePriceList.subWasteCategory' : ?0 }")
    Flux<Recycler> findRecyclersBySubWastePriceList(SubWasteCategory subWasteCategory);
//    Flux<Recycler> findRecyclersBySubWasteAmountListIsContaining(SubWasteCategory subWasteCategory);

}
