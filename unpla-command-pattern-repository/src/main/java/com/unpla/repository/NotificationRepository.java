package com.unpla.repository;

import com.unpla.entity.document.Notification;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Flux;

public interface NotificationRepository extends ReactiveMongoRepository<Notification, String> {
    Flux<Notification> findNotificationsByIdUser(String idUser);

    Flux<Notification> findNotificationsByIdRecycler(String idRecycler);
}
