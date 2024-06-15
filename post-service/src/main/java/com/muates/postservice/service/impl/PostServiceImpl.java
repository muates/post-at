package com.muates.postservice.service.impl;

import com.muates.postservice.model.dto.request.PostCreateRequest;
import com.muates.postservice.model.entity.Post;
import com.muates.postservice.repository.PostRepository;
import com.muates.postservice.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;


    @Override
    public Post createPost(PostCreateRequest request) {
        return null;
    }
}
