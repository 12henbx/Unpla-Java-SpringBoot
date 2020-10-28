package com.unpla.service.command;

import com.blibli.oss.command.Command;
import com.unpla.model.controller.WasteAddToTransactionResponse;
import com.unpla.model.service.WasteAddToTransactionRequest;

public interface AddWasteToTransactionCommand extends Command<WasteAddToTransactionRequest, WasteAddToTransactionResponse> {
}
