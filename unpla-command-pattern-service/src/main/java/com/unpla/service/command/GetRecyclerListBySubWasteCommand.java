package com.unpla.service.command;

import com.blibli.oss.command.Command;
import com.unpla.model.controller.RecyclerGetListBySubWasteResponse;
import com.unpla.model.service.RecyclerGetListBySubWasteRequest;

public interface GetRecyclerListBySubWasteCommand extends Command<RecyclerGetListBySubWasteRequest, RecyclerGetListBySubWasteResponse> {
}
