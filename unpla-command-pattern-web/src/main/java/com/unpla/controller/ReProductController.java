package com.unpla.controller;

import com.blibli.oss.command.CommandExecutor;
import com.unpla.model.controller.RecycledProductAddResponse;
import com.unpla.model.controller.RecycledProductGetListResponse;
import com.unpla.model.controller.Response;
import com.unpla.model.service.RecycledProductAddRequest;
import com.unpla.model.service.RecycledProductGetListRequest;
import com.unpla.service.command.AddReProductToReProductCommand;
import com.unpla.service.command.GetReProductListCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
public class ReProductController {

    @Autowired
    private CommandExecutor commandExecutor;

    @PostMapping(value = "/recycled-product/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<RecycledProductAddResponse>> addRecycledProduct(@RequestBody RecycledProductAddRequest recycledProductAddRequest) {

        return commandExecutor.execute(AddReProductToReProductCommand.class, recycledProductAddRequest)
                .map(Response::ok)
                .subscribeOn(Schedulers.elastic());
    }

    @GetMapping(
            value = "/recycled-product/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Response<RecycledProductGetListResponse>> reProductGetList(@RequestBody RecycledProductGetListRequest recycledProductGetListRequest) {
        return commandExecutor.execute(GetReProductListCommand.class, recycledProductGetListRequest)
                .map(Response::ok)
                .subscribeOn(Schedulers.elastic());
    }
}
