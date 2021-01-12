package com.unpla.service.command;

import com.blibli.oss.command.Command;
import com.unpla.model.controller.RecycledProductAddResponse;
import com.unpla.model.service.RecycledProductAddRequest;

public interface AddReProductToReProductCommand extends Command<RecycledProductAddRequest, RecycledProductAddResponse> {
}
