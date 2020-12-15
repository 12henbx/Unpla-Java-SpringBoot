package com.unpla.controller;

import com.blibli.oss.command.CommandExecutor;
//import com.unpla.config.security.JWTUtil;
//import com.unpla.config.security.PBKDF2Encoder;
import com.unpla.model.controller.Response;
import com.unpla.model.controller.UserAddResponse;
import com.unpla.model.controller.UserLoginResponse;
import com.unpla.model.service.UserAddRequest;
import com.unpla.model.service.LoginUserRequest;
import com.unpla.service.command.AddUserToUserCommand;
import com.unpla.service.command.UserLoginToUserCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@RestController
public class UserController {

    @Autowired
    private CommandExecutor commandExecutor;

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
    public Mono<Response<UserLoginResponse>> login(@RequestBody LoginUserRequest loginRequest) {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(userDetails)));

        return commandExecutor.execute(UserLoginToUserCommand.class, loginRequest)
                .map(Response::ok)
                .subscribeOn(Schedulers.elastic());
    }
}
