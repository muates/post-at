package com.muates.postservice.controller;

import com.muates.postservice.converter.PostConverter;
import com.muates.postservice.model.dto.request.PostCreateRequest;
import com.muates.postservice.model.dto.request.PostUpdateRequest;
import com.muates.postservice.model.dto.response.PostResponse;
import com.muates.postservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/user/{userId}")
    public ResponseEntity<Page<PostResponse>> getAllPostByUserId(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<PostResponse> response = postService.getAllPostByUserId(userId, page, size)
                .map(PostConverter::convertPostToResponse);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{postId}")
    public ResponseEntity<PostResponse> updatePost(
            @PathVariable Long postId,
            @RequestBody PostUpdateRequest request
    ) {
        PostResponse response = PostConverter.convertPostToResponse(postService.updatePost(postId, request));
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/delete/{postId}")
    public void deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);
    }
}
