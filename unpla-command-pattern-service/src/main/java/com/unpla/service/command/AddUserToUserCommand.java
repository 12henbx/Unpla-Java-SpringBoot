package com.unpla.service.command;

import com.blibli.oss.command.Command;
import com.unpla.model.controller.UserAddResponse;
import com.unpla.model.service.AddUserToUserRequest;
import reactor.core.publisher.Mono;

public interface AddUserToUserCommand extends Command<AddUserToUserRequest, UserAddResponse> {
//    public Mono<UserAddResponse> execute(AddUserToUserRequest request);
}
