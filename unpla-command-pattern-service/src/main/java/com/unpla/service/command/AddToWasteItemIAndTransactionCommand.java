package com.unpla.service.command;

import com.blibli.oss.command.Command;
import com.unpla.model.controller.WasteAddToWasteItemAndTransactionResponse;
import com.unpla.model.service.WasteAddToWasteItemAndTransactionRequest;

public interface AddToWasteItemIAndTransactionCommand extends Command<WasteAddToWasteItemAndTransactionRequest, WasteAddToWasteItemAndTransactionResponse> {
}
