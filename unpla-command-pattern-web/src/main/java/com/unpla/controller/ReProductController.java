package com.unpla.controller;

import com.blibli.oss.command.CommandExecutor;
import com.unpla.model.controller.RecycledProductAddResponse;
import com.unpla.model.controller.RecycledProductGetListResponse;
import com.unpla.model.controller.RecycledProductGetResponse;
import com.unpla.model.controller.Response;
import com.unpla.model.service.RProductGetListByRecyclerRequest;
import com.unpla.model.service.RecycledProductAddRequest;
import com.unpla.model.service.RecycledProductGetListRequest;
import com.unpla.model.service.RecycledProductGetRequest;
import com.unpla.service.command.AddReProductToReProductCommand;
import com.unpla.service.command.GetReProductCommand;
import com.unpla.service.command.GetReProductListByRecyclerCommand;
import com.unpla.service.command.GetReProductListCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping(value = "/api/recycled-product")
public class ReProductController {

    @Autowired
    private CommandExecutor commandExecutor;

    @PostMapping(value = "/add", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<Response<RecycledProductAddResponse>> addRecycledProduct(@RequestBody RecycledProductAddRequest recycledProductAddRequest) {

        return commandExecutor.execute(AddReProductToReProductCommand.class, recycledProductAddRequest)
                .map(Response::ok)
                .subscribeOn(Schedulers.elastic());
    }

    @GetMapping(
            value = "/all",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Response<RecycledProductGetListResponse>> reProductGetList(RecycledProductGetListRequest recycledProductGetListRequest) {
        return commandExecutor.execute(GetReProductListCommand.class, recycledProductGetListRequest)
                .map(Response::ok)
                .subscribeOn(Schedulers.elastic());
    }

    @GetMapping(
            value = "/get/{reProductId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Response<RecycledProductGetResponse>> reProductGet(@PathVariable("reProductId") String reProductId) {
        return commandExecutor.execute(GetReProductCommand.class, toRecycledProductReq(reProductId))
                .map(Response::ok)
                .subscribeOn(Schedulers.elastic());
    }

    private RecycledProductGetRequest toRecycledProductReq(String reProductId){
        return RecycledProductGetRequest.builder()
                .recycledProductId(reProductId)
                .build();
    }

    @GetMapping(
            value = "/all/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Response<RecycledProductGetListResponse>> recyclerProductGetList(@PathVariable("userId") String userId) {
        return commandExecutor.execute(GetReProductListByRecyclerCommand.class, toWebRequest(userId))
                .map(Response::ok)
                .subscribeOn(Schedulers.elastic());
    }

    private RProductGetListByRecyclerRequest toWebRequest(String userId){
        return RProductGetListByRecyclerRequest.builder()
                .userId(userId)
                .build();
    }
}
