package com.muates.apigateway.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {

    @Value("${identity-service.url}")
    private String identityServiceUrl;

    private final WebClient.Builder webClientBuilder;

    public JwtAuthenticationFilter(WebClient.Builder webClientBuilder) {
        super(Config.class);
        this.webClientBuilder = webClientBuilder;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String token = extractToken((ServerWebExchange) exchange.getRequest());
            if (token == null) {
                return unauthorized(exchange);
            }

            String requestUrl = extractRequestUrl(exchange.getRequest());

            return webClientBuilder.build()
                    .get()
                    .uri(uriBuilder -> uriBuilder
                            .path(identityServiceUrl + "/api/auth/validate")
                            .queryParam("url", requestUrl)
                            .build())
                    .header(HttpHeaders.AUTHORIZATION, "Bearer " + token)
                    .retrieve()
                    .onStatus(HttpStatus::isError, response -> handleErrorResponse(response, exchange))
                    .bodyToMono(Void.class)
                    .then(chain.filter(exchange));
        };
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
        return exchange.getResponse().setComplete();
    }

    private String extractRequestUrl(ServerHttpRequest request) {
        return request.getURI().getPath();
    }

    private Mono<? extends Throwable> handleErrorResponse(ClientResponse response, ServerWebExchange exchange) {
        HttpStatus httpStatus = response.statusCode();
        if (httpStatus == HttpStatus.UNAUTHORIZED) {
            exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
        } else {
            exchange.getResponse().setStatusCode(httpStatus);
        }
        return exchange.getResponse().setComplete().then(Mono.empty());
    }

    private String extractToken(ServerWebExchange exchange) {
        String bearerToken = exchange.getRequest().getHeaders().getFirst("Authorization");
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public static class Config {

    }
}
