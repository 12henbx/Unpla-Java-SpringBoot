package com.unpla.service.command.impl;

import com.unpla.entity.document.Notification;
import com.unpla.entity.document.User;
import com.unpla.model.controller.NotificationGetListResponse;
import com.unpla.model.controller.NotificationGetToNotificationResponse;
import com.unpla.model.service.NotificationGetListRequest;
import com.unpla.repository.NotificationRepository;
import com.unpla.repository.UserRepository;
import com.unpla.service.command.GetNotifListByUserIdCommand;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GetNotifListByUserIdCommandImpl implements GetNotifListByUserIdCommand {

    @Autowired
    private NotificationRepository notificationRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Mono<NotificationGetListResponse> execute(NotificationGetListRequest request) {

        return userRepository.findById(request.getUserId())
                            .flatMapMany(user -> {
                                if(user.isRecyclerActive()){
                                    return Flux.concat(notificationRepository.findNotificationsByIdRecycler(user.getRecyclerId()), notificationRepository.findNotificationsByIdUser(user.getId()));
                                }
                                return notificationRepository.findNotificationsByIdUser(user.getId());
                            })
                            .map(this::toGetNotificationWebResponse)
                            .collectList()
                            .map(this::toWebResponse);
    }

    private NotificationGetListResponse toWebResponse(List<NotificationGetToNotificationResponse> notificationList) {
        return NotificationGetListResponse.builder() // TODO : Belum terbagi jadi recycler notif dan user notif
                .recyclerNotifList(notificationList)
                .totalRecyclerNotif(notificationList.size())
                .userNotifList(notificationList)
                .totalUserNotif(notificationList.size())
                .build();
    }

    private NotificationGetToNotificationResponse toGetNotificationWebResponse(Notification notification) {
//        System.out.println(notification.toString() + " ini adalah notif");
//        NotificationGetToNotificationResponse response = new NotificationGetToNotificationResponse();
//        BeanUtils.copyProperties(notification, response);
//        return response;

        return NotificationGetToNotificationResponse.builder()
                .id(notification.getId())
                .idRecycler(notification.getIdRecycler())
                .idUser(notification.getIdUser())
                .isDelete(notification.isDelete())
                .isRead(notification.isRead())
                .itemId(notification.getItemId())
                .notifGroupForRecycler(notification.getNotifGroupForRecycler())
                .notificationType(notification.getNotificationType())
                .build();
    }
}
