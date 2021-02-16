package com.unpla.controller;

import com.blibli.oss.command.CommandExecutor;
import com.unpla.entity.enums.SubWasteCategory;
import com.unpla.model.controller.*;
import com.unpla.model.service.RecyclerAddRequest;
import com.unpla.model.service.RecyclerGetListBySubWasteRequest;
import com.unpla.model.service.RecyclerGetListRequest;
import com.unpla.model.service.RecyclerGetNameRequest;
import com.unpla.service.command.AddRecyclerToRecyclerCommand;
import com.unpla.service.command.GetRecyclerListBySubWasteCommand;
import com.unpla.service.command.GetRecyclerListCommand;
import com.unpla.service.command.GetRecyclerNameCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping(value = "/api/recycler")
public class RecyclerController {

    @Autowired
    private CommandExecutor commandExecutor;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<RecyclerAddResponse>> addRecycler(@RequestBody RecyclerAddRequest recyclerAddRequest) {

        return commandExecutor.execute(AddRecyclerToRecyclerCommand.class, recyclerAddRequest)
                .map(Response::ok)
                .subscribeOn(Schedulers.elastic());
    }

    @GetMapping(
            value = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Response<RecyclerGetListResponse>> getAllList(RecyclerGetListRequest recyclerGetListRequest) {
        return commandExecutor.execute(GetRecyclerListCommand.class, recyclerGetListRequest)
                .map(response -> Response.status(HttpStatus.OK, response))
                .subscribeOn(Schedulers.elastic());
    }

    @GetMapping(
            value = "/{subWaste}/recyclers",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Response<RecyclerGetListBySubWasteResponse>> getListbySubWaste(@PathVariable("subWaste") SubWasteCategory subWaste) {
        return commandExecutor.execute(GetRecyclerListBySubWasteCommand.class, toGetListRecycler(subWaste))
                .map(response -> Response.status(HttpStatus.OK, response))
                .subscribeOn(Schedulers.elastic());
    }

    private RecyclerGetListBySubWasteRequest toGetListRecycler(SubWasteCategory subWaste){
        RecyclerGetListBySubWasteRequest commandReq = new RecyclerGetListBySubWasteRequest();
        commandReq.setSubWasteCategory(subWaste);
        return commandReq;
    }

    @GetMapping(
            value = "/get/name/{recyclerId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Response<RecyclerGetNameResponse>> getRecyclerName(@PathVariable("recyclerId") String recyclerId) {
        return commandExecutor.execute(GetRecyclerNameCommand.class, toRecyclerIdRequest(recyclerId))
                .map(response -> Response.status(HttpStatus.OK, response))
                .subscribeOn(Schedulers.elastic());
    }

    private RecyclerGetNameRequest toRecyclerIdRequest(String recyclerId){
        return RecyclerGetNameRequest.builder()
                .recyclerId(recyclerId)
                .build();
    }
}
