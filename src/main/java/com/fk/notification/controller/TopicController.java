package com.fk.notification.controller;

import com.fk.notification.service.NovuClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/topic")
public class TopicController {

    private final NovuClientService novuService;

    public TopicController(NovuClientService novuService) {
        this.novuService = novuService;
    }

    @PostMapping
    public Mono<ResponseEntity<String>> createTopic(@RequestBody Map<String, Object> payload) {
        return novuService.createTopic(payload)
                .map(ResponseEntity::ok)
                .onErrorResume(ex -> Mono.just(ResponseEntity.internalServerError().body(ex.getMessage())));
    }
}
