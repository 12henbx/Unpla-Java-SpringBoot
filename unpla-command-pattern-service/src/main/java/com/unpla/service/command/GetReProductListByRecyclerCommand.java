package com.unpla.service.command;

import com.blibli.oss.command.Command;
import com.unpla.model.controller.RecycledProductGetListResponse;
import com.unpla.model.service.RProductGetListByRecyclerRequest;

public interface GetReProductListByRecyclerCommand extends Command<RProductGetListByRecyclerRequest, RecycledProductGetListResponse> {
}
