package com.muates.postservice.controller;

import com.muates.postservice.converter.PostConverter;
import com.muates.postservice.model.dto.request.PostCreateRequest;
import com.muates.postservice.model.dto.response.PostResponse;
import com.muates.postservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping("/create")
    public ResponseEntity<PostResponse> createPost(@RequestBody PostCreateRequest request) {
        PostResponse response = PostConverter.convertPostToResponse(postService.createPost(request));
        return ResponseEntity.ok(response);
    }
}
