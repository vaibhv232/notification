package com.fk.notification.controller;

import com.fk.notification.service.NovuClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/subscriber")
public class SubscriberController {

    private final NovuClientService novuService;

    public SubscriberController(NovuClientService novuService) {
        this.novuService = novuService;
    }

    @PostMapping
    public Mono<ResponseEntity<String>> createSubscriber(@RequestBody Map<String, Object> payload) {
        return novuService.createSubscriber(payload)
                .map(ResponseEntity::ok)
                .onErrorResume(ex -> Mono.just(ResponseEntity.internalServerError().body(ex.getMessage())));
    }

    @GetMapping
    public Mono<ResponseEntity<String>> getAllSubscribers() {
        return novuService.getAllSubscribers()
                .map(ResponseEntity::ok)
                .onErrorResume(ex -> Mono.just(ResponseEntity.internalServerError().body(ex.getMessage())));
    }
}
