package com.muates.postservice.controller;

import com.muates.postservice.model.dto.request.CommentRequest;
import com.muates.postservice.model.dto.request.CommentUpdateRequest;
import com.muates.postservice.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/post/post-comment")
@RequiredArgsConstructor
public class PostCommentController {

    private final CommentService commentService;

    @PostMapping("/comment")
    public void commentToPost(@RequestBody CommentRequest request) {
        commentService.commentToPost(request);
    }

    @PutMapping("/update/{commentId}")
    public void updateComment(
            @PathVariable Long commentId,
            @RequestBody CommentUpdateRequest request
            ) {
        commentService.updateComment(commentId, request);
    }
}
