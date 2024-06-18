package com.muates.postservice.service;

import com.muates.postservice.model.dto.request.CommentRequest;
import com.muates.postservice.model.dto.request.CommentUpdateRequest;

public interface CommentService {
    void commentToPost(CommentRequest request);
    void updateComment(Long commentId, CommentUpdateRequest request);
}
