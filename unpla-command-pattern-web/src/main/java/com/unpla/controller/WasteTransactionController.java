package com.unpla.controller;

import com.blibli.oss.command.CommandExecutor;
import com.unpla.model.controller.Response;
import com.unpla.model.controller.WasteAddToTransactionResponse;
import com.unpla.model.controller.WasteGetToTransactionResponse;
import com.unpla.model.service.WasteGetToTransactionRequest;
import com.unpla.service.command.GetWasteTransactionByIdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping(value = "/api/waste-transaction")
public class WasteTransactionController {

    @Autowired
    private CommandExecutor commandExecutor;

    @PostMapping(value = "/{wasteTransactionId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<WasteGetToTransactionResponse>> GetWasteTransaction(@PathVariable("Waste Transaction Id") String wasteTransactionId,
                                                                             @RequestParam(name = "page") int page,
                                                                             @RequestParam(name = "size") int size){

        return commandExecutor.execute(GetWasteTransactionByIdCommand.class, toGetWasteTransaction(wasteTransactionId, page, size))
                .map(Response::ok)
                .subscribeOn(Schedulers.elastic());
    }

    private WasteGetToTransactionRequest toGetWasteTransaction(String wasteTransactionId, int page, int size){
        WasteGetToTransactionRequest commandReq = new WasteGetToTransactionRequest();
        commandReq.setWasteTransactionId(wasteTransactionId);
        commandReq.setPage(page);
        commandReq.setSize(size);
        return commandReq;
    }
}
