package com.muates.postservice.converter;

import com.muates.postservice.model.dto.response.PostCommentLikeResponse;
import com.muates.postservice.model.entity.PostCommentLike;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PostCommentLikeConverter {

    public static List<PostCommentLikeResponse> convertPostCommentLikeToResponse(List<PostCommentLike> commentLikeList) {
        if (commentLikeList == null || commentLikeList.isEmpty()) {
            return Collections.emptyList();
        }

        return commentLikeList.stream()
                .map(cl -> PostCommentLikeResponse.builder()
                        .id(cl.getId())
                        .userId(cl.getUserId())
                        .isLike(cl.getIsLike())
                        .createdDate(cl.getCreatedDate())
                        .build())
                .collect(Collectors.toList());
    }
}
