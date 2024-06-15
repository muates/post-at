package com.muates.postservice.service;

import com.muates.postservice.model.dto.request.PostCreateRequest;
import com.muates.postservice.model.entity.Post;

public interface PostService {
    Post createPost(PostCreateRequest request);
}
