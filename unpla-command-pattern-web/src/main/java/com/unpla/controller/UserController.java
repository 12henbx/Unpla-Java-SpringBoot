package com.unpla.controller;

import com.blibli.oss.command.CommandExecutor;
//import com.unpla.config.security.JWTUtil;
//import com.unpla.config.security.PBKDF2Encoder;
import com.unpla.model.controller.Response;
import com.unpla.model.controller.UserAddResponse;
import com.unpla.model.controller.UserGetRecyclerIdResponse;
import com.unpla.model.controller.UserLoginResponse;
import com.unpla.model.service.UserAddRequest;
import com.unpla.model.service.LoginUserRequest;
import com.unpla.model.service.UserGetRecyclerIdRequest;
import com.unpla.service.command.AddUserToUserCommand;
import com.unpla.service.command.GetRecyclerIdByUserIdCommand;
import com.unpla.service.command.UserLoginToUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @Autowired
    private CommandExecutor commandExecutor;

    @GetMapping(
            value = "/get/recycler/{userId}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Mono<Response<UserGetRecyclerIdResponse>> getRecyclerId(@PathVariable("userId") String userId) {
        return commandExecutor.execute(GetRecyclerIdByUserIdCommand.class, toUserReq(userId))
                .map(Response::ok)
                .subscribeOn(Schedulers.elastic());
    }

    private UserGetRecyclerIdRequest toUserReq(String userId){
        return UserGetRecyclerIdRequest.builder()
                .userId(userId)
                .build();
    }

//    @PostMapping("/login") // dari website content list di kiri
//    public Mono<UserDetails> login(ServerWebExchange exchange) {
//
//        return ReactiveSecurityContextHolder.getContext()
//                .map(SecurityContext::getAuthentication)
//                .map(Authentication::getPrincipal)
//                .cast(MyUserDetails.class)
//                .doOnNext(userDetails -> {
//                    addTokenHeader(exchange.getResponse(), userDetails); // your job to code it the way you want
//                });
//    }

    @PostMapping(value = "/signup", produces = MediaType.APPLICATION_JSON_VALUE) // dari ard333-nya
    public Mono<Response<UserAddResponse>> signup(@RequestBody UserAddRequest userRequest) {

        return commandExecutor.execute(AddUserToUserCommand.class, userRequest)
                .map(Response::ok)
                .subscribeOn(Schedulers.elastic());
    }

    @PostMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<ResponseEntity<String>> login(@RequestBody LoginUserRequest loginRequest) {

//    public Mono<Response<UserLoginResponse>> login(@RequestBody LoginUserRequest loginRequest) {
//        return commandExecutor.execute(UserLoginToUserCommand.class, loginRequest)
////                .map((userAuth) -> {
////                    ResponseCookie cookie = ResponseCookie.from("cookieAuth", userAuth.getToken()).build();
////                    responseHeaders.set("Authorization", userAuth.getToken());
////                    return new ResponseEntity<String>(userAuth.getId(), responseHeaders, HttpStatus.OK);
////                })
////                .subscribeOn(Schedulers.elastic());

        return commandExecutor.execute(UserLoginToUserCommand.class, loginRequest)
                .map((userAuth) -> {
                    ResponseCookie cookie = ResponseCookie.from("cookieAuth", userAuth.getToken())
                            .secure(true)
                            .httpOnly(true)
                            .maxAge(3600)
                            .path("/")
                            .build(); // TODO: harusnya secure true

                    return ResponseEntity.ok()
                            .header(HttpHeaders.SET_COOKIE, cookie.toString())
                            .body(userAuth.getId());
                })
                .subscribeOn(Schedulers.elastic());

//        return commandExecutor.execute(UserLoginToUserCommand.class, loginRequest)
//                .map(Response::ok)
//                .subscribeOn(Schedulers.elastic());
    }
}
