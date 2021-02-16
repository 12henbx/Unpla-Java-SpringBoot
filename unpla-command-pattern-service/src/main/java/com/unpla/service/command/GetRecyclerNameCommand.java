package com.unpla.service.command;

import com.blibli.oss.command.Command;
import com.unpla.model.controller.RecyclerGetNameResponse;
import com.unpla.model.service.RecyclerGetNameRequest;

public interface GetRecyclerNameCommand extends Command<RecyclerGetNameRequest, RecyclerGetNameResponse> {
}
