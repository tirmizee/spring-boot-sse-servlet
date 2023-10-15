package com.tirmizee.controller;

import com.tirmizee.services.SseServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
public class SseController {

    @Autowired
    private SseServerService sseServerService;

    @GetMapping(path = "/sse")
    public SseEmitter handle() {
        SseEmitter emitter = new SseEmitter();
        sseServerService.addClient(emitter);
        emitter.onCompletion(() -> sseServerService.removeClient(emitter));
        emitter.onTimeout(() -> sseServerService.removeClient(emitter));
        return emitter;
    }

    @GetMapping("/trigger")
    public void trigger(@RequestParam String message) {
        System.out.println(message);
        sseServerService.broadcast(message);
    }

}
