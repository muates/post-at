package com.muates.postservice.controller;

import com.muates.postservice.model.dto.request.PostReactionRequest;
import com.muates.postservice.service.PostLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post-reaction")
@RequiredArgsConstructor
public class PostReactionController {

    private final PostLikeService postLikeService;

    @PostMapping("/react")
    public void react(@RequestBody PostReactionRequest request) {
        postLikeService.reactToPost(request);
    }
}
