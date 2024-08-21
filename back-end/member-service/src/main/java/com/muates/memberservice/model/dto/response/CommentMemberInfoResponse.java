package com.muates.memberservice.model.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class CommentMemberInfoResponse {
    private Long userId;
    private String username;
    private String profilePicture;
}
