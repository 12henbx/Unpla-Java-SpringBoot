package com.unpla.controller;

import com.blibli.oss.common.response.ResponseHelper;
import com.unpla.config.security.JWTUtil;
import com.unpla.config.security.PBKDF2Encoder;
import com.unpla.model.controller.Response;
import com.unpla.service.ServiceExecutor;
import com.unpla.service.command.AddUserToUserCommand;
import com.unpla.service.command.impl.AddUserToUserCommandImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

public class UserController {
    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private PBKDF2Encoder passwordEncoder;

    @Autowired
    private ServiceExecutor serviceExecutor;

//    @PostMapping(
//            value = "/api/customers",
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            consumes = MediaType.APPLICATION_JSON_VALUE
//    )
//    public Mono<Response<CreateCustomerWebResponse>> create(@RequestBody CreateCustomerWebRequest request) {
//        return commandExecutor.execute(CreateCustomerCommand.class, toCreateCustomerCommandRequest(request))
//                .map(ResponseHelper::ok)
//                .subscribeOn(commandScheduler);
//    }

//    @RequestMapping(value = "/register", produces = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.POST)
//    public Mono<Response<Boolean>> register(@RequestBody RegisterRequest request) {
//        return commandExecutor.execute(RegisterCommand.class, registerRequestToInput(request))
//                .map(ResponseHelper::ok)
//                .subscribeOn(Schedulers.elastic());
//    }

//    private RegisterInput registerRequestToInput(RegisterRequest request) {
//        RegisterInput input = RegisterInput.builder().build();
//        BeanUtils.copyProperties(request, input);
//        return input;
//    }


//    @PostMapping("/login")
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

//    @PostMapping("/login")
//    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
//        User u = userService.findByUsername(authRequest.getUsername());
//        if (u != null) {
//            if (passwordEncoder.encode(authRequest.getPassword()).equals(u.getPassword())) {
//                return ResponseEntity.ok(new AuthResponse(jwtUtil.generateToken(u)));
//            } else {
//                return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//            }
//        } else {
//            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//    }
}
