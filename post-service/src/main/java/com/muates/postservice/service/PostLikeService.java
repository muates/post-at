package com.muates.postservice.service;

import com.muates.postservice.model.dto.request.PostReactionRequest;

public interface PostLikeService {
    void reactToPost(PostReactionRequest request);
}
