package com.muates.postservice.clients.service.impl;

import com.muates.postservice.clients.MemberFeignClient;
import com.muates.postservice.clients.model.request.CommentInfoRequest;
import com.muates.postservice.clients.model.response.CommentMemberInfoResponse;
import com.muates.postservice.clients.service.MemberClientService;
import com.muates.postservice.exception.FeignClientExceptionHandler;
import com.muates.postservice.clients.model.request.PostInfoRequest;
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
    public List<PostMemberInfoResponse> getMemberInfoForPosts(PostInfoRequest request) {
        return executeWithCircuitBreaker(
                () -> fetchMemberInfoForPosts(request),
                this::handleFallback
        );
    }

    @Override
    public List<CommentMemberInfoResponse> getMemberInfoForComments(CommentInfoRequest request) {
        return executeWithCircuitBreaker(
                () -> fetchMemberInfoForComments(request),
                this::handleFallback
        );
    }

    private List<PostMemberInfoResponse> fetchMemberInfoForPosts(PostInfoRequest request) {
        try {
            ResponseEntity<List<PostMemberInfoResponse>> responseEntity = memberFeignClient.getMemberInfoForPost(request);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            } else {
                throw new RuntimeException("Unexpected response status: " + responseEntity.getStatusCode());
            }
        } catch (FeignException e) {
            exceptionHandler.handleFeignException(e);
            return Collections.emptyList();
        }
    }

    private List<CommentMemberInfoResponse> fetchMemberInfoForComments(CommentInfoRequest request) {
        try {
            ResponseEntity<List<CommentMemberInfoResponse>> responseEntity = memberFeignClient.getMemberInfoForComment(request);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            } else {
                throw new RuntimeException("Unexpected response status: " + responseEntity.getStatusCode());
            }
        } catch (FeignException e) {
            exceptionHandler.handleFeignException(e);
            return Collections.emptyList();
        }
    }

    private <T> List<T> handleFallback(Throwable throwable) {
        LOGGER.error("Fallback method called due to: {}", throwable.getMessage());
        return Collections.emptyList();
    }

    private <T> T executeWithCircuitBreaker(Supplier<T> supplier, Function<Throwable, T> fallback) {
        Supplier<T> decoratedSupplier = CircuitBreaker.decorateSupplier(memberServiceCircuitBreaker, supplier);
        try {
            return decoratedSupplier.get();
        } catch (Exception e) {
            return fallback.apply(e);
        }
    }
}
