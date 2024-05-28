package com.muates.apigateway.filter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.*;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter extends AbstractGatewayFilterFactory<JwtAuthenticationFilter.Config> {

    @Value("${identity-service.url}")
    private String identityServiceUrl;

    private final RestTemplate restTemplate;

    public JwtAuthenticationFilter(RestTemplate restTemplate) {
        super(Config.class);
        this.restTemplate = restTemplate;
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String token = extractToken(exchange.getRequest());
            if (token == null) {
                return unauthorized(exchange);
            }

            String requestUrl = extractRequestUrl(exchange.getRequest());
            String validateEndpoint = identityServiceUrl + "/api/auth/validate?url=" + requestUrl;

            HttpHeaders headers = new HttpHeaders();
            headers.set(HttpHeaders.AUTHORIZATION, "Bearer " + token);
            HttpEntity<Void> entity = new HttpEntity<>(headers);

            try {
                ResponseEntity<String> response = restTemplate.exchange(validateEndpoint, HttpMethod.GET, entity, String.class);
                if (response.getStatusCode().is2xxSuccessful()) {
                    return chain.filter(exchange);
                }

                return handleErrorResponse(exchange, response.getStatusCode(), response.getBody());
            } catch (HttpStatusCodeException e) {
                return handleErrorResponse(exchange, e.getStatusCode(), e.getResponseBodyAsString());
            } catch (RestClientException e) {
                return handleErrorResponse(exchange, HttpStatus.INTERNAL_SERVER_ERROR, null);
            }
        };
    }

    private Mono<Void> unauthorized(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
        return exchange.getResponse().setComplete();
    }

    private String extractRequestUrl(ServerHttpRequest request) {
        return request.getURI().getPath();
    }

    private Mono<Void> handleErrorResponse(ServerWebExchange exchange, HttpStatus status, String responseBody) {
        if (exchange.getResponse().isCommitted()) {
            return Mono.empty();
        }
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(status);
        if (responseBody != null) {
            response.getHeaders().setContentType(MediaType.TEXT_PLAIN);
            return response.writeWith(Mono.just(response.bufferFactory().wrap(responseBody.getBytes())));
        } else {
            return response.setComplete();
        }
    }

    private String extractToken(ServerHttpRequest request) {
        String bearerToken = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);
        if (bearerToken != null && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }

    public static class Config {

    }
}
