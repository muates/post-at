package com.muates.postservice.controller;

import com.muates.postservice.converter.PostConverter;
import com.muates.postservice.model.dto.request.PostCreateRequest;
import com.muates.postservice.model.dto.response.PostResponse;
import com.muates.postservice.model.entity.Post;
import com.muates.postservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
    public ResponseEntity<List<PostResponse>> getAllPostByUserId(
            @PathVariable Long userId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        Page<Post> postPage = postService.getAllPostByUserId(userId, page, size);

        List<PostResponse> postList = postPage.stream()
                .map(PostConverter::convertPostToResponse)
                .collect(Collectors.toList());

        return ResponseEntity.ok(postList);
    }
}
