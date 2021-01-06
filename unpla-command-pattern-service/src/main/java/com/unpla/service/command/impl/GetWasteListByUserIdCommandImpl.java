package com.unpla.service.command.impl;

import com.unpla.model.controller.WasteGetListResponse;
import com.unpla.model.service.WasteGetListByUserIdRequest;
import com.unpla.service.command.GetWasteListByUserIdCommand;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class GetWasteListByUserIdCommandImpl implements GetWasteListByUserIdCommand {
    @Override
    public Mono<WasteGetListResponse> execute(WasteGetListByUserIdRequest request) {
        return null;
    }
}
