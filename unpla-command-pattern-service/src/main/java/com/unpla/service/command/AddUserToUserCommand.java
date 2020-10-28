package com.unpla.service.command;

import com.blibli.oss.command.Command;
import com.unpla.model.controller.UserAddResponse;
import com.unpla.model.service.UserAddRequest;

public interface AddUserToUserCommand extends Command<UserAddRequest, UserAddResponse> {
//    public Mono<UserAddResponse> execute(AddUserToUserRequest request);
}
