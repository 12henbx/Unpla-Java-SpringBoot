package com.unpla.controller;

import com.blibli.oss.command.CommandExecutor;
import com.unpla.model.controller.Response;
import com.unpla.model.controller.WasteAddToWasteItemAndTransactionResponse;
import com.unpla.model.controller.WasteGetListResponse;
import com.unpla.model.controller.WasteGetToWasteItemResponse;
import com.unpla.model.service.WasteAddToWasteItemAndTransactionRequest;
import com.unpla.model.service.WasteGetListByUsernameRequest;
import com.unpla.model.service.WasteGetToWasteItemRequest;
import com.unpla.service.command.AddToWasteItemIAndTransactionCommand;
import com.unpla.service.command.GetWasteListByUsernameCommand;
import com.unpla.service.command.GetWasteToWasteItemCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
public class WasteItemController {

    @Autowired
    private CommandExecutor commandExecutor;

    @PostMapping(value = "/add-waste-item", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<WasteAddToWasteItemAndTransactionResponse>> addWasteItem(@RequestBody WasteAddToWasteItemAndTransactionRequest wasteItemRequest) {

        return commandExecutor.execute(AddToWasteItemIAndTransactionCommand.class, wasteItemRequest)
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
    public Mono<Response<WasteGetListResponse>> getListbyUsername(//@RequestBody WasteGetListByUsernameRequest req,
                                                                  @PathVariable("username") String username,
                                                                  @RequestParam(name = "page") int page,
                                                                  @RequestParam(name = "size") int size
                       ) { // TODO : get waste by wasteitemid sama userid. Get userID ato username? Trus ini mungkin IDnya unsuppoerted media type object to string
//        System.out.println(commandExecutor.execute(GetWasteListByUsernameCommand.class, req) + "    BLUUU   ");
        return commandExecutor.execute(GetWasteListByUsernameCommand.class, toGetListWasteItem(username, page, size))
                .map(response -> {
                    System.out.println(response.getListWasteItem() + "          BLA     ");
                    return Response.status(HttpStatus.OK, response);
                })
                .subscribeOn(Schedulers.elastic());
    }

    private WasteGetListByUsernameRequest toGetListWasteItem(String username, int page, int size){
        WasteGetListByUsernameRequest commandReq = new WasteGetListByUsernameRequest();
        commandReq.setUsername(username);
        commandReq.setPage(page);
        commandReq.setSize(size);
        return commandReq;
    }
}
