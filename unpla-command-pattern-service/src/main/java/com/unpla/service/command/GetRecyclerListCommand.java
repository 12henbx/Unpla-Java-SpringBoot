package com.unpla.service.command;

import com.blibli.oss.command.Command;
import com.unpla.model.controller.RecyclerGetListResponse;
import com.unpla.model.service.RecyclerGetListRequest;

public interface GetRecyclerListCommand extends Command<RecyclerGetListRequest, RecyclerGetListResponse> {
}
