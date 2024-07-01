package com.muates.postservice.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PostReactionRequest {
    private Long postId;
    private Long userId;
    private Boolean isLike;
    private Long postOwnerId;
}
