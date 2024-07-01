package com.muates.postservice.converter;

import com.muates.postservice.model.dto.request.PostMediaCreateRequest;
import com.muates.postservice.model.dto.response.PostMediaResponse;
import com.muates.postservice.model.entity.PostMedia;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class PostMediaConverter {

    public static PostMedia convertPostMediaCreateRequestToMedia(PostMediaCreateRequest request) {
        if (request == null) {
            return null;
        }

        return PostMedia.builder()
                .mediaUrl(request.getMediaUrl())
                .mediaType(request.getMediaType())
                .build();
    }

    public static List<PostMediaResponse> convertPostMediaToResponse(List<PostMedia> media) {
        if (media == null || media.isEmpty()) {
            return Collections.emptyList();
        }

        return media.stream()
                .map(m -> PostMediaResponse.builder()
                        .id(m.getId())
                        .mediaUrl(m.getMediaUrl())
                        .mediaType(m.getMediaType())
                        .createdDate(m.getCreatedDate())
                        .build())
                .collect(Collectors.toList());
    }

}
