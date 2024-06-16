package com.muates.postservice.service;

import com.muates.postservice.model.dto.request.PostCreateRequest;
import com.muates.postservice.model.entity.Post;
import org.springframework.data.domain.Page;

public interface PostService {
    Post createPost(PostCreateRequest request);
    Page<Post> getAllPostByUserId(Long userId, int page, int size);
}
