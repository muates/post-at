package com.muates.postservice.service;

import com.muates.postservice.model.dto.request.CommentReactionRequest;

public interface CommentLikeService {
    void reactToComment(CommentReactionRequest request);
}
