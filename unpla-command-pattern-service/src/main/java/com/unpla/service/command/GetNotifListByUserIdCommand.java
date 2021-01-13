package com.unpla.service.command;

import com.blibli.oss.command.Command;
import com.unpla.model.controller.NotificationGetListResponse;
import com.unpla.model.service.NotificationGetListRequest;

public interface GetNotifListByUserIdCommand extends Command<NotificationGetListRequest, NotificationGetListResponse>{
}
