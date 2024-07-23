package com.muates.memberservice.model.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class PostWithCommentInfoRequest {
    private Long postId;
    private List<Long> commentIds;
}
