package com.muates.postservice.converter;

import com.muates.postservice.model.dto.response.PostLikeResponse;
import com.muates.postservice.model.entity.PostLike;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PostLikeConverter {

    public static List<PostLikeResponse> convertPostLikeToResponse(List<PostLike> postLikeList) {
        if (postLikeList == null || postLikeList.isEmpty()) {
            return Collections.emptyList();
        }

        return postLikeList.stream()
                .map(pl -> PostLikeResponse.builder()
                        .id(pl.getId())
                        .userId(pl.getUserId())
                        .isLike(pl.getIsLike())
                        .createdDate(pl.getCreatedDate())
                        .build())
                .collect(Collectors.toList());
    }
}
