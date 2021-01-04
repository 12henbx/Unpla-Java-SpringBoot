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
public class RecyclerController {

    @Autowired
    private CommandExecutor commandExecutor;

    @PostMapping(value = "/recycler/submit", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<RecyclerAddResponse>> addRecycler(@RequestBody RecyclerAddRequest recyclerAddRequest) {

        return commandExecutor.execute(AddRecyclerToRecyclerCommand.class, recyclerAddRequest)
                .map(Response::ok)
                .subscribeOn(Schedulers.elastic());
    }

    @GetMapping(
            value = "/recycler/{subWaste}", //TODO: cek baris ini
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Response<RecyclerGetListBySubWasteResponse>> getListbySubWaste(@PathVariable("Sub Waste Category") SubWasteCategory subWaste,
                                                                               @RequestParam(name = "page") int page,
                                                                               @RequestParam(name = "size") int size
    ) {
        return commandExecutor.execute(GetRecyclerListBySubWasteCommand.class, toGetListRecycler(subWaste, page, size))
                .map(response -> {
                    System.out.println(response.getListWasteItem() + "          BLA     ");
                    return Response.status(HttpStatus.OK, response);
                })
                .subscribeOn(Schedulers.elastic());
    }

    private RecyclerGetListBySubWasteRequest toGetListRecycler(SubWasteCategory subWaste, int page, int size){
        RecyclerGetListBySubWasteRequest commandReq = new RecyclerGetListBySubWasteRequest();
        commandReq.setSubWasteCategory(subWaste);
        commandReq.setPage(page);
        commandReq.setSize(size);
        return commandReq;
    }
}
