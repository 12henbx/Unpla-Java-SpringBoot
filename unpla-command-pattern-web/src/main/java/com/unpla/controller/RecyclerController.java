package com.unpla.controller;

import com.blibli.oss.command.CommandExecutor;
import com.unpla.entity.enums.SubWasteCategory;
import com.unpla.model.controller.RecyclerAddResponse;
import com.unpla.model.controller.RecyclerGetListBySubWasteResponse;
import com.unpla.model.controller.Response;
import com.unpla.model.service.RecyclerAddRequest;
import com.unpla.model.service.RecyclerGetListBySubWasteRequest;
import com.unpla.service.command.AddRecyclerToRecyclerCommand;
import com.unpla.service.command.GetRecyclerListBySubWasteCommand;
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
}
