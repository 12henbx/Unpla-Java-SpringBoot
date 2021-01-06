package com.unpla.service.command;

import com.blibli.oss.command.Command;
import com.unpla.model.controller.WasteGetListResponse;
import com.unpla.model.service.WasteGetListByUserIdRequest;

public interface GetWasteListByUserIdCommand extends Command<WasteGetListByUserIdRequest, WasteGetListResponse> {
}
