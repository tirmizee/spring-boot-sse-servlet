package com.tirmizee.services;

import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

@Service
public class SseServerService {

    private final CopyOnWriteArrayList<SseEmitter> emitters = new CopyOnWriteArrayList<>();

    public void addClient(SseEmitter clientEmitter) {
        emitters.add(clientEmitter);
    }

    public void removeClient(SseEmitter clientEmitter) {
        emitters.remove(clientEmitter);
    }

    public void broadcast(String message) {
        this.emitters.forEach(emitter -> {
            try {
                emitter.send(message);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }

}
