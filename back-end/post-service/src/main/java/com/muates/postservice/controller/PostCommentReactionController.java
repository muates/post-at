package com.muates.postservice.controller;

import com.muates.postservice.model.dto.request.CommentReactionRequest;
import com.muates.postservice.service.CommentLikeService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/post/comment-reaction")
@RequiredArgsConstructor
public class PostCommentReactionController {

    private final CommentLikeService commentLikeService;

    @PostMapping("/react")
    public void react(@RequestBody CommentReactionRequest request) {
        commentLikeService.reactToComment(request);
    }
}
