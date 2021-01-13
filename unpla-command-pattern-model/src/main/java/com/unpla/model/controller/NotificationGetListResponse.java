package com.unpla.model.controller;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationGetListResponse { //TODO: notification get list dan get by id

    private int totalRecyclerNotif;

    private int totalUserNotif;

    private List<NotificationGetToNotificationResponse> recyclerNotifList;

    private List<NotificationGetToNotificationResponse> userNotifList;
}
