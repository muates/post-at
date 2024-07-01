package com.muates.postservice.model.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentReactionRequest {
    private Long commentId;
    private Long userId;
    private Boolean isLike;
    private Long commentOwnerId;
    private Long postId;
}
