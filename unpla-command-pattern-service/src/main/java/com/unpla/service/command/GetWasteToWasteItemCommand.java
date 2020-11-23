package com.unpla.service.command;

import com.blibli.oss.command.Command;
import com.unpla.model.controller.WasteGetToWasteItemResponse;
import com.unpla.model.service.WasteGetToWasteItemRequest;

public interface GetWasteToWasteItemCommand extends Command<WasteGetToWasteItemRequest, WasteGetToWasteItemResponse> {
}
