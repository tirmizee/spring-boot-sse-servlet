package com.tirmizee.runner;

import com.tirmizee.services.SseClientService;
import com.tirmizee.services.SseServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class SseClientRunner implements CommandLineRunner {

    @Autowired
    private SseClientService sseClientService;

    @Override
    public void run(String... args) throws Exception {
        sseClientService.streamEvents().subscribe(
                sseClientService::handleMessage,
                sseClientService::handleError,
            () -> System.out.println("Stream Finished")
        );
    }

}
