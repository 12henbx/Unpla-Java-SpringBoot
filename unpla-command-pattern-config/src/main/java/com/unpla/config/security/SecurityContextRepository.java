package com.unpla.config.security;

import com.sun.prism.shader.DrawEllipse_LinearGradient_REFLECT_AlphaTest_Loader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Objects;

@Component
public class SecurityContextRepository implements ServerSecurityContextRepository { // TODO: Ngurusin Auth di Cookies
    @Autowired
    private AuthenticationManager authenticationManager;

    @Override
    public Mono<Void> save(ServerWebExchange serverWebExchange, SecurityContext securityContext) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange serverWebExchange) {
        ServerHttpRequest request = serverWebExchange.getRequest();
//        String authHeader = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        String authHeader = null;
        if(request.getCookies().getFirst("cookieAuth") != null){
            authHeader = request.getCookies().getFirst("cookieAuth").toString();
        }

//        if (authHeader != null && authHeader.startsWith("bearer ")){
        if (authHeader != null && authHeader.startsWith("cookieAuth=")){
//            String authToken = authHeader.substring(7);
            String authToken = authHeader.substring(11);
            Authentication auth = new UsernamePasswordAuthenticationToken(authToken, authToken);
            return this.authenticationManager.authenticate(auth).map(SecurityContextImpl::new);
        } else {
            return Mono.empty();
        }
    }
}
