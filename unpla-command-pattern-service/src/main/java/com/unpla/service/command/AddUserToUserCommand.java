package com.unpla.service.command;

import com.blibli.oss.command.Command;
import com.unpla.entity.document.User;
import com.unpla.model.controller.UserAddRequest;
import com.unpla.model.service.AddUserToUserResponse;

public interface AddUserToUserCommand extends Command<UserAddRequest, AddUserToUserResponse> {
}
