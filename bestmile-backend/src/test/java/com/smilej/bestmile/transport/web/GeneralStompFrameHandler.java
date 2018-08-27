package com.smilej.bestmile.transport.web;

import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.messaging.simp.stomp.StompFrameHandler;
import org.springframework.messaging.simp.stomp.StompHeaders;

import java.lang.reflect.Type;
import java.util.concurrent.CompletableFuture;

import static java.util.concurrent.TimeUnit.SECONDS;

@RequiredArgsConstructor
class GeneralStompFrameHandler<T> implements StompFrameHandler {

    private final CompletableFuture<T> completableFuture = new CompletableFuture<>();
    private final Class<T> payloadType;
    private int secondsToWait = 10;

    public GeneralStompFrameHandler(Class<T> payloadType, int secondsToWait) {
        this.payloadType = payloadType;
        this.secondsToWait = secondsToWait;
    }

    @Override
    public Type getPayloadType(StompHeaders stompHeaders) {
        return payloadType;
    }

    @Override
    public void handleFrame(StompHeaders stompHeaders, Object o) {
        completableFuture.complete((T) o);
    }

    @SneakyThrows
    public T getPayload() {
        return completableFuture.get(secondsToWait, SECONDS);
    }

}
