package com.muates.postservice.clients.service.impl;

import com.muates.postservice.clients.MemberFeignClient;
import com.muates.postservice.clients.service.MemberClientService;
import com.muates.postservice.exception.FeignClientExceptionHandler;
import com.muates.postservice.clients.model.request.PostWithCommentInfoRequest;
import com.muates.postservice.clients.model.response.PostMemberInfoResponse;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.Supplier;

@Service
@RequiredArgsConstructor
public class MemberClientServiceImpl implements MemberClientService {

    private static final Logger LOGGER = LoggerFactory.getLogger(MemberClientServiceImpl.class);

    private final MemberFeignClient memberFeignClient;
    private final CircuitBreaker memberServiceCircuitBreaker;
    private final FeignClientExceptionHandler exceptionHandler;

    @Override
    public List<PostMemberInfoResponse> getMemberInfoForPosts(List<PostWithCommentInfoRequest> requests) {
        Supplier<List<PostMemberInfoResponse>> supplier = createSupplier(requests);
        Function<Throwable, List<PostMemberInfoResponse>> fallback = createFallback();
        return executeWithCircuitBreaker(supplier, fallback);
    }

    private Supplier<List<PostMemberInfoResponse>> createSupplier(List<PostWithCommentInfoRequest> requests) {
        return () -> {
            try {
                ResponseEntity<List<PostMemberInfoResponse>> responseEntity = memberFeignClient.getMemberInfoForPost(requests);
                if (responseEntity.getStatusCode().is2xxSuccessful()) {
                    return responseEntity.getBody();
                } else {
                    throw new RuntimeException("Unexpected response status: " + responseEntity.getStatusCode());
                }
            } catch (FeignException e) {
                exceptionHandler.handleFeignException(e);
                return Collections.emptyList();
            }
        };
    }

    private Function<Throwable, List<PostMemberInfoResponse>> createFallback() {
        return throwable -> {
            LOGGER.error("Fallback method called due to: {}", throwable.getMessage());
            return Collections.emptyList();
        };
    }

    private List<PostMemberInfoResponse> executeWithCircuitBreaker(
            Supplier<List<PostMemberInfoResponse>> supplier,
            Function<Throwable, List<PostMemberInfoResponse>> fallback) {
        Supplier<List<PostMemberInfoResponse>> decoratedSupplier = CircuitBreaker.decorateSupplier(memberServiceCircuitBreaker, supplier);
        try {
            return decoratedSupplier.get();
        } catch (Exception e) {
            return fallback.apply(e);
        }
    }
}
