package com.muates.postservice.service;

import com.muates.postservice.model.dto.request.CommentRequest;
import com.muates.postservice.model.dto.request.CommentUpdateRequest;
import com.muates.postservice.model.entity.PostComment;

public interface CommentService {
    void commentToPost(CommentRequest request);
    PostComment getCommentByCommentId(Long commentId);
    void updateComment(Long commentId, CommentUpdateRequest request);
}
