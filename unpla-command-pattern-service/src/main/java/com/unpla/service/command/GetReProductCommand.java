package com.unpla.service.command;

import com.blibli.oss.command.Command;
import com.unpla.model.controller.RecycledProductGetResponse;
import com.unpla.model.service.RecycledProductGetRequest;

public interface GetReProductCommand extends Command<RecycledProductGetRequest, RecycledProductGetResponse> {
}
