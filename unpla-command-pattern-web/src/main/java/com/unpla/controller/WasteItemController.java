package com.unpla.controller;

import com.blibli.oss.command.CommandExecutor;
import com.unpla.model.controller.Response;
import com.unpla.model.controller.WasteAddToWasteItemResponse;
import com.unpla.model.controller.WasteGetToWasteItemResponse;
import com.unpla.model.service.WasteAddToWasteItemRequest;
import com.unpla.model.service.WasteGetToWasteItemRequest;
import com.unpla.repository.WasteItemRepository;
import com.unpla.service.command.AddWasteToWasteItemCommand;
import com.unpla.service.command.GetWasteToWasteItemCommand;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
public class WasteItemController {

    @Autowired
    private CommandExecutor commandExecutor;

    @Autowired
    private WasteItemRepository wasteItemRepository;

    @Operation(summary = "Add Waste Item", security = @SecurityRequirement(name = "basicAuth"))
    @PostMapping(value = "/add-waste-item", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<WasteAddToWasteItemResponse>> addWasteItem(@RequestBody WasteAddToWasteItemRequest wasteItemRequest) {

        return commandExecutor.execute(AddWasteToWasteItemCommand.class, wasteItemRequest)
                .map(Response::ok)
                .subscribeOn(Schedulers.elastic());
    }

    @GetMapping(
            value = "/waste-item/{username}/{wasteItemId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Response<WasteGetToWasteItemResponse>> getbyWasteItemId(@PathVariable("username") String username,
                                                                        @PathVariable("wasteItemId") String wasteItemId) { // TODO : get waste by wasteitemid sama userid
        return commandExecutor.execute(GetWasteToWasteItemCommand.class, toGetWasteItemCommandRequest(username, wasteItemId))
                .map(Response::ok)
                .subscribeOn(Schedulers.elastic());
    }

    private WasteGetToWasteItemRequest toGetWasteItemCommandRequest(String username, String wasteItemId){
        WasteGetToWasteItemRequest commandReq = new WasteGetToWasteItemRequest();
        commandReq.setUsername(username);
        commandReq.setWasteItemId(wasteItemId);
        return commandReq;
    }

    @GetMapping(
            value = "/waste-item/{username}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Flux<Response<WasteGetToWasteItemResponse>> getListbyUsername(@PathVariable("username") String username) { // TODO : get waste by wasteitemid sama userid
        return commandExecutor.execute(GetWasteToWasteItemCommand.class, toGetWasteItemCommandRequest(username))
                .map(Response::ok)
                .subscribeOn(Schedulers.elastic());
    }

    private WasteGetToWasteItemRequest toGetWasteItemCommandRequest(String username){
        return wasteItemRepository.findById(username.getId())
                .map(this::toWebResponse);
    }
}
