package com.fk.notification.service;

import com.fk.notification.config.NovuConfig;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
public class NovuClientService {

    private final WebClient webClient;

    public NovuClientService(NovuConfig config) {
        this.webClient = WebClient.builder()
                .baseUrl(config.getApiUrl())
                .defaultHeader(HttpHeaders.AUTHORIZATION, "ApiKey " + config.getApiKey())
                .defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                .build();
    }

    public Mono<String> sendMessage(Object payload) {
        return webClient.post().uri("/events/trigger")
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> createSubscriber(Object payload) {
        return webClient.post().uri("/subscribers")
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(String.class);
    }

    public Mono<String> createTopic(Object payload) {
        return webClient.post().uri("/topics")
                .bodyValue(payload)
                .retrieve()
                .bodyToMono(String.class);
    }
    public Mono<String> getAllSubscribers() {
        return webClient.get().uri("/subscribers")
                .retrieve()
                .bodyToMono(String.class);
    }

}
