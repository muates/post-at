package com.muates.postservice.service;

import com.muates.postservice.model.dto.request.CommentRequest;
import com.muates.postservice.model.dto.request.CommentUpdateRequest;
import com.muates.postservice.model.dto.response.PostCommentResponse;
import com.muates.postservice.model.entity.PostComment;
import org.springframework.data.domain.Page;

public interface CommentService {
    void commentToPost(CommentRequest request);
    PostComment getCommentByCommentId(Long commentId);
    void updateComment(Long commentId, CommentUpdateRequest request);
    Page<PostCommentResponse> getComments(Long postId, int page, int size);
    int getCountByPostId(Long postId);
}
