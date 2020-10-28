package com.unpla.service.command;

import com.blibli.oss.command.Command;
import com.unpla.model.controller.WasteAddToWasteItemResponse;
import com.unpla.model.service.WasteAddToWasteItemRequest;

public interface AddWasteToWasteItemCommand extends Command<WasteAddToWasteItemRequest, WasteAddToWasteItemResponse> {
}
