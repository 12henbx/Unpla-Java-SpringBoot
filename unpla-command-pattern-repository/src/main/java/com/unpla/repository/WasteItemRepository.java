package com.unpla.repository;

import com.unpla.entity.document.WasteItem;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface WasteItemRepository extends ReactiveMongoRepository<WasteItem, String> {
}
