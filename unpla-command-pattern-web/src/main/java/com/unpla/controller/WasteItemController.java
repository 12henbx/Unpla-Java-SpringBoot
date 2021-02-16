package com.unpla.controller;

import com.blibli.oss.command.CommandExecutor;
import com.unpla.model.controller.Response;
import com.unpla.model.controller.WasteAddToWasteItemAndTransactionResponse;
import com.unpla.model.controller.WasteGetListResponse;
import com.unpla.model.controller.WasteGetToWasteItemResponse;
import com.unpla.model.service.WasteAddToWasteItemAndTransactionRequest;
import com.unpla.model.service.WasteGetListByUserIdRequest;
import com.unpla.model.service.WasteGetToWasteItemRequest;
import com.unpla.service.command.AddToWasteItemAndTransactionCommand;
import com.unpla.service.command.GetWasteListByUserIdCommand;
import com.unpla.service.command.GetWasteToWasteItemCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping(value = "/api/waste-item")
public class WasteItemController {

    @Autowired
    private CommandExecutor commandExecutor;

    @PostMapping(value = "/add-waste-item", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<WasteAddToWasteItemAndTransactionResponse>> addWasteItem(@RequestBody WasteAddToWasteItemAndTransactionRequest wasteItemRequest) {

        return commandExecutor.execute(AddToWasteItemAndTransactionCommand.class, wasteItemRequest)
                .map(Response::ok)
                .subscribeOn(Schedulers.elastic());
    }

    @GetMapping(
            value = "/{username}/{wasteItemId}",
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

//    @GetMapping(
//            value = "/waste-item/{username}",
//            produces = MediaType.APPLICATION_JSON_VALUE
//    )
//    public Mono<Response<WasteGetListResponse>> getListbyUsername(//@RequestBody WasteGetListByUsernameRequest req,
//                                                                  @PathVariable("username") String username,
//                                                                  @RequestParam(name = "page") int page,
//                                                                  @RequestParam(name = "size") int size
//                       ) {
//
//        return commandExecutor.execute(GetWasteListByUsernameCommand.class, toGetListWasteItem(username, page, size))
//                .map(response -> {
//                    System.out.println(response.getListWasteItem() + "          BLA     ");
//                    return Response.status(HttpStatus.OK, response);
//                })
//                .subscribeOn(Schedulers.elastic());
//    }
//
//    private WasteGetListByUsernameRequest toGetListWasteItem(String username, int page, int size){
//        WasteGetListByUsernameRequest commandReq = new WasteGetListByUsernameRequest();
//        commandReq.setUsername(username);
//        commandReq.setPage(page);
//        commandReq.setSize(size);
//        return commandReq;
//    }

    @GetMapping(
            value = "/all/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Response<WasteGetListResponse>> getListbyUserId(@PathVariable("userId") String userId,
                                                                  @RequestParam(name = "page") int page,
                                                                  @RequestParam(name = "size") int size){

        return commandExecutor.execute(GetWasteListByUserIdCommand.class, toUserIdGetListWasteItem(userId, page, size))
                .map(response -> {
                    return Response.status(HttpStatus.OK, response);
                })
                .subscribeOn(Schedulers.elastic());
    }

    private WasteGetListByUserIdRequest toUserIdGetListWasteItem(String userId, int page, int size){
        WasteGetListByUserIdRequest commandReq = new WasteGetListByUserIdRequest();
        commandReq.setUserId(userId);
        commandReq.setPage(page);
        commandReq.setSize(size);
        return commandReq;
    }
}
