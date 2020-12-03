package com.unpla.repository;

import com.unpla.entity.document.WasteItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

import java.util.List;

public interface WasteItemRepository extends ReactiveMongoRepository<WasteItem, String> {
    Flux<WasteItem> findWasteItemsByUserId(String userId, Pageable pageable);
}
