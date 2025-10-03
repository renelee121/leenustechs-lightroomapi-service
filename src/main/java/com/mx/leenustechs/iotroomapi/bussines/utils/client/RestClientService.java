package com.mx.leenustechs.iotroomapi.bussines.utils.client;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import jakarta.annotation.PostConstruct;
import reactor.core.publisher.Mono;

@Component
public class RestClientService {

    @Value("${sync.manager.url}")
    private String serviceUrl;

    private WebClient webClient;

    private final WebClient.Builder webClientBuilder;

    public RestClientService(WebClient.Builder webClientBuilder) {
        this.webClientBuilder = webClientBuilder;
    }

    @PostConstruct
    public void init() {
        this.webClient = webClientBuilder.baseUrl(serviceUrl).build();
    }

    public Mono<String> getDataFromService(String transactionId) {
        return webClient.get()
                .uri("/{transactionId}", transactionId)
                .retrieve()
                .onStatus(status -> !status.is2xxSuccessful(),
                        response -> response.bodyToMono(String.class)
                                .flatMap(body -> Mono.error(new RuntimeException(
                                        "Error en GET: Status " + response.statusCode() + " Body: " + body))))
                .bodyToMono(String.class);
    }
}
