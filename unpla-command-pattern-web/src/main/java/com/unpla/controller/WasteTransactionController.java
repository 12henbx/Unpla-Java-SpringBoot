package com.unpla.controller;

import com.blibli.oss.command.CommandExecutor;
import com.unpla.model.controller.Response;
import com.unpla.model.controller.WasteAddToTransactionResponse;
import com.unpla.model.service.WasteAddToTransactionRequest;
import com.unpla.service.command.AddWasteToTransactionCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
public class WasteTransactionController {

    @Autowired
    private CommandExecutor commandExecutor;

    @PostMapping(value = "/add-waste-item/submit", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<WasteAddToTransactionResponse>> addWasteTransaction(@RequestBody WasteAddToTransactionRequest transactionRequest) {

        return commandExecutor.execute(AddWasteToTransactionCommand.class, transactionRequest)
                .map(Response::ok)
                .subscribeOn(Schedulers.elastic());
    }
}
