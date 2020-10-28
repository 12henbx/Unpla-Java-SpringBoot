package com.unpla.repository;

import com.unpla.entity.document.WasteTransaction;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface WasteTransactionRepository extends ReactiveMongoRepository<WasteTransaction, String> {
}
