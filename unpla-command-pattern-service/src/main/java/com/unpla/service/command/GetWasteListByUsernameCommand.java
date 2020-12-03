package com.unpla.service.command;

import com.blibli.oss.command.Command;
import com.unpla.model.controller.WasteGetListByUsernameResponse;
import com.unpla.model.service.WasteGetListByUsernameRequest;

public interface GetWasteListByUsernameCommand extends Command<WasteGetListByUsernameRequest, WasteGetListByUsernameResponse> {
}
