package com.unpla.service.command;

import com.blibli.oss.command.Command;
import com.unpla.model.controller.UserGetRecyclerIdResponse;
import com.unpla.model.service.UserGetRecyclerIdRequest;

public interface GetRecyclerIdByUserIdCommand extends Command<UserGetRecyclerIdRequest, UserGetRecyclerIdResponse> {
}
