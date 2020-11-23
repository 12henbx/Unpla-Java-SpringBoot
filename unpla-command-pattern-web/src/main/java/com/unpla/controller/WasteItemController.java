package com.unpla.controller;

import com.blibli.oss.command.CommandExecutor;
import com.unpla.model.controller.Response;
import com.unpla.model.controller.WasteAddToWasteItemResponse;
import com.unpla.model.controller.WasteGetToWasteItemResponse;
import com.unpla.model.service.WasteAddToWasteItemRequest;
import com.unpla.service.command.AddWasteToWasteItemCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
public class WasteItemController {

    @Autowired
    private CommandExecutor commandExecutor;

    @Operation(summary = "Add Waste Item", security = @SecurityRequirement(name = "basicAuth"))
    @PostMapping(value = "/add-waste-item", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<WasteAddToWasteItemResponse>> addWasteItem(@RequestBody WasteAddToWasteItemRequest wasteItemRequest) {

        return commandExecutor.execute(AddWasteToWasteItemCommand.class, wasteItemRequest)
                .map(Response::ok)
                .subscribeOn(Schedulers.elastic());
    }

    @MandatoryParameterAtHeader
    @GetMapping(
            value = "/api/customers/{customerId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Response<WasteGetToWasteItemResponse>> get(@PathVariable("wasteItemId") String wasteItemId) {
        return commandExecutor.execute(GetCustomerCommand.class, toGetCustomerCommandRequest(wasteItemId))
                .map(ResponseHelper::ok)
                .subscribeOn(commandScheduler);
    }
}
