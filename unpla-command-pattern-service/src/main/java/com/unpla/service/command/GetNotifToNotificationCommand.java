package com.unpla.service.command;

import com.blibli.oss.command.Command;
import com.unpla.model.controller.NotificationGetToNotificationResponse;
import com.unpla.model.service.NotificationGetToNotificationRequest;

public interface GetNotifToNotificationCommand extends Command<NotificationGetToNotificationRequest, NotificationGetToNotificationResponse> {
}
