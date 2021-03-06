package com.unpla.service.command;

import com.blibli.oss.command.Command;
import com.unpla.model.controller.WasteAddToTransactionResponse;
import com.unpla.model.controller.WasteGetToTransactionResponse;
import com.unpla.model.service.WasteGetToTransactionRequest;

public interface GetWasteTransactionByIdCommand extends Command<WasteGetToTransactionRequest, WasteGetToTransactionResponse> {
}
