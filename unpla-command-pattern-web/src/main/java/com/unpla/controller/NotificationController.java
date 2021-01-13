package com.unpla.controller;

import com.blibli.oss.command.CommandExecutor;
import com.unpla.model.controller.NotificationGetListResponse;
import com.unpla.model.controller.Response;
import com.unpla.model.service.NotificationGetListRequest;
import com.unpla.service.command.GetNotifListByUserIdCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
public class NotificationController {

    @Autowired
    private CommandExecutor commandExecutor;

//    @PostMapping(value = "/recycler/add", produces = MediaType.APPLICATION_JSON_VALUE)
//    public Mono<Response<RecyclerAddResponse>> addRecycler(@RequestBody RecyclerAddRequest recyclerAddRequest) {
//
//        return commandExecutor.execute(AddRecyclerToRecyclerCommand.class, recyclerAddRequest)
//                .map(Response::ok)
//                .subscribeOn(Schedulers.elastic());
//    }

    @GetMapping(
            value = "/notification/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Response<NotificationGetListResponse>> getAllNotifListbyUserId(@PathVariable("User ID") String userId) {
        return commandExecutor.execute(GetNotifListByUserIdCommand.class, toGetListRecycler(userId))
                .map(response -> Response.status(HttpStatus.OK, response))
                .subscribeOn(Schedulers.elastic());
    }

    private NotificationGetListRequest toGetListRecycler(String userId){
        NotificationGetListRequest commandReq = new NotificationGetListRequest();
        commandReq.setUserId(userId);
        return commandReq;
    }

}
