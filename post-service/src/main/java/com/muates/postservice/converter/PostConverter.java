package com.muates.postservice.converter;

import com.muates.postservice.model.dto.request.PostCreateRequest;
import com.muates.postservice.model.entity.Post;

public class PostConverter {

    public static Post convertPostCreateRequestToPost(PostCreateRequest request) {
        if (request == null) {
            return null;
        }

        return Post.builder()

                .build();
    }
}
