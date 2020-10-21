package com.unpla.service.command;

import com.blibli.oss.command.Command;
import com.unpla.model.controller.UserLoginResponse;
import com.unpla.model.service.LoginUserRequest;

public interface UserLoginToUserCommand extends Command<LoginUserRequest, UserLoginResponse> {
}
