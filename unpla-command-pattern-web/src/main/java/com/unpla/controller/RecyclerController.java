package com.unpla.controller;

import com.blibli.oss.command.CommandExecutor;
import com.unpla.model.controller.RecyclerAddResponse;
import com.unpla.model.controller.Response;
import com.unpla.model.service.RecyclerAddRequest;
import com.unpla.service.command.AddRecyclerToRecyclerCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
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
}
