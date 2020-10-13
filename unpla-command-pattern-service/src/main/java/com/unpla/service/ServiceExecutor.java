package com.unpla.service;

import com.blibli.oss.command.Command;
import com.unpla.model.service.ServiceRequest;
import reactor.core.publisher.Mono;

public interface ServiceExecutor {

    <T, R extends ServiceRequest> Mono<T> execute(Class<? extends Command<T, R>> commandClass, R request);
}
