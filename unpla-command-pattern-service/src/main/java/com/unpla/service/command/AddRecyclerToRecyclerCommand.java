package com.unpla.service.command;

import com.blibli.oss.command.Command;
import com.unpla.model.controller.RecyclerAddResponse;
import com.unpla.model.service.RecyclerAddRequest;

public interface AddRecyclerToRecyclerCommand extends Command<RecyclerAddRequest, RecyclerAddResponse> {
}
