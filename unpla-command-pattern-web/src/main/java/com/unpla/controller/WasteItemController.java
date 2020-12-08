package com.unpla.controller;

import com.blibli.oss.command.CommandExecutor;
import com.unpla.model.controller.Response;
import com.unpla.model.controller.WasteAddToWasteItemResponse;
import com.unpla.model.controller.WasteGetListByUsernameResponse;
import com.unpla.model.controller.WasteGetToWasteItemResponse;
import com.unpla.model.service.WasteAddToWasteItemRequest;
import com.unpla.model.service.WasteGetListByUsernameRequest;
import com.unpla.model.service.WasteGetToWasteItemRequest;
import com.unpla.repository.WasteItemRepository;
import com.unpla.service.command.AddWasteToWasteItemCommand;
import com.unpla.service.command.GetWasteListByUsernameCommand;
import com.unpla.service.command.GetWasteToWasteItemCommand;
import com.unpla.support.PageSupport;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;
import sun.rmi.runtime.Log;

import java.util.List;

@RestController
public class WasteItemController {

    @Autowired
    private CommandExecutor commandExecutor;

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
                                                                        @PathVariable("wasteItemId") String wasteItemId) {
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
    public Mono<Response<WasteGetListByUsernameResponse>> getListbyUsername(@PathVariable("username") String userId,
                                                                            @RequestBody WasteGetListByUsernameRequest req,
                                                                            @RequestParam(name = "page") int page,
                                                                            @RequestParam(name = "size") int size
                       ) { // TODO : get waste by wasteitemid sama userid. Get userID ato username? Trus ini mungkin IDnya unsuppoerted media type object to string
//        return commandExecutor.execute(GetWasteListByUsernameCommand.class, req)
//        System.out.println(commandExecutor.execute(GetWasteListByUsernameCommand.class, req) + "    BLUUU   ");
        return commandExecutor.execute(GetWasteListByUsernameCommand.class, req)
                .map(response -> {
                    System.out.println(response.getListWasteItem() + "          BLA     ");
                    return Response.ok(response);
                })
                .subscribeOn(Schedulers.elastic());
    }
}
