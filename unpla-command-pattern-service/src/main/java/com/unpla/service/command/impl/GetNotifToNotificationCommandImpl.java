package com.unpla.service.command.impl;

import com.unpla.entity.document.Notification;
import com.unpla.model.controller.NotificationGetToNotificationResponse;
import com.unpla.model.service.NotificationGetToNotificationRequest;
import com.unpla.repository.NotificationRepository;
import com.unpla.service.command.GetNotifToNotificationCommand;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetNotifToNotificationCommandImpl implements GetNotifToNotificationCommand {

    @Autowired
    private NotificationRepository notificationRepository;

    @Override
    public Mono<NotificationGetToNotificationResponse> execute(NotificationGetToNotificationRequest request) {
        return notificationRepository.findById(request.getNotificationId())
                .map(this::toWebResponse);
    }

    private NotificationGetToNotificationResponse toWebResponse(Notification notification) {

        NotificationGetToNotificationResponse response = new NotificationGetToNotificationResponse();
        BeanUtils.copyProperties(notification, response);

        return response;
    }
}
