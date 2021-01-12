package com.unpla.service.command;

import com.blibli.oss.command.Command;
import com.unpla.model.controller.RecycledProductGetListResponse;
import com.unpla.model.service.RecycledProductGetListRequest;

public interface GetReProductListCommand extends Command<RecycledProductGetListRequest, RecycledProductGetListResponse> {
}
