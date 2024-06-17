package com.muates.postservice.converter;

import com.muates.postservice.model.dto.request.PostCreateRequest;
import com.muates.postservice.model.dto.response.PostResponse;
import com.muates.postservice.model.entity.Post;

import java.util.Collections;

public class PostConverter {

    public static Post convertPostCreateRequestToPost(PostCreateRequest request) {
        if (request == null) {
            return null;
        }

        return Post.builder()
                .userId(request.getUserId())
                .content(request.getContent())
                .build();
    }

    public static PostResponse convertPostToResponse(Post post) {
        if (post == null) {
            return null;
        }

        return PostResponse.builder()
                .id(post.getId())
                .userId(post.getUserId())
                .content(post.getContent())
                .createdDate(post.getCreatedDate())
                .updatedDate(post.getUpdatedDate())
                .media(PostMediaConverter.convertPostMediaToResponse(post.getMedia()))
                .likes(Collections.emptyList())
                .comments(Collections.emptyList())
                .build();
    }
}
