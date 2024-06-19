package com.muates.postservice.converter;

import com.muates.postservice.model.dto.response.PostCommentResponse;
import com.muates.postservice.model.entity.PostComment;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PostCommentConverter {

    public static List<PostCommentResponse> convertPostCommentToResponse(List<PostComment> postCommentList) {
        if (postCommentList == null || postCommentList.isEmpty()) {
            return Collections.emptyList();
        }

        return postCommentList.stream()
                .map(comment -> PostCommentResponse.builder()
                        .id(comment.getId())
                        .userId(comment.getUserId())
                        .content(comment.getContent())
                        .createdDate(comment.getCreatedDate())
                        .updatedDate(comment.getUpdatedDate())
                        .likes(PostCommentLikeConverter.convertPostCommentLikeToResponse(comment.getLikes()))
                        .build())
                .collect(Collectors.toList());
    }
}
